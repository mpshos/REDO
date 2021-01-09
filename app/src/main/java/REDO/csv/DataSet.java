package REDO.csv;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.io.FileReader;
import java.util.List;

public class DataSet {

    private final String name;
    private final List<DataRow> rows;

    public static List<DataRow> parse(Path path, Class<?extends DataRow> c) throws FileNotFoundException {
        // TODO: Check out this warning
        return new CsvToBeanBuilder(new FileReader(path.toString()))
        .withType(c).build().parse();
    }

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
