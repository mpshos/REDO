/**
 * DataSet class that represents the full raw data from one CSV file.
 */

package REDO.csv;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataSet {

    /**
     * Regular Expression for extracting file number and name
     */
    private static final Pattern numberedFileRegex = Pattern.compile("^(\\d+)[\\s_]*([a-zA-Z].*)[.]csv$");

    /**
     * The name of the dataset
     */
    private final String name;

    /**
     * The list of data rows
     */
    private final List<DataRow> rows;

    /**
     * The file number which was added to the beginning of the file name to force column ordering
     */
    private final int fileNumber;

    /**
     * Parse the CSV into a list of DataRows using the opencsv library.
     * @param path  the path to the CSV file to parse.
     * @param c     The DataRow subclass to instantiate for the rows encountered.
     * @return      A list of DataRows parsed from the CSV
     * @throws FileNotFoundException if the Path cannot be resolved to a file that exists.
     */
    public static List<DataRow> parse(Path path, Class<?extends DataRow> c) throws FileNotFoundException {
        // TODO: Check out this warning
        return new CsvToBeanBuilder(new FileReader(path.toString()))
        .withType(c).build().parse();
    }

    /**
     * Instantiates a new dataset
     * @param path Path to the parsed file. Will be used to determine name used when writing
     * @param rows The parsed list of DataRows
     */
    public DataSet(Path path, List<DataRow> rows) {
        String tempName = path.getFileName().toString();

        Matcher m = DataSet.numberedFileRegex.matcher(tempName);

        // Attempt to extract file number
        if (m.matches()) {
            this.name = m.group(2);
            this.fileNumber = Integer.parseInt(m.group(1));
        }
        else {
            // No File number present, use default and store whole file name
            this.name = tempName.replace(".csv", "");
            this.fileNumber = -1;
        }

        this.rows = rows;
    }

    public int getFileNumber() {
        return fileNumber;
    }

    public String getName() {
        return this.name;
    }

    public DataRow getRow(int idx) {
        return this.rows.get(idx);
    }

    public int getSize() {
        return this.rows.size();
    }
}
