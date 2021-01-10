/**
 * DataSet class that represents the full raw data from one CSV file.
 */

package REDO.csv;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.io.FileReader;
import java.util.List;

public class DataSet {

    /**
     * The name of the dataset
     */
    private final String name;

    /**
     * The list of data rows
     */
    private final List<DataRow> rows;

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
        this.name = path.getFileName().toString().replace(".csv", "");
        this.rows = rows;
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
