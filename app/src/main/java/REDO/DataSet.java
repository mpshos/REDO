package REDO;

import java.nio.file.Path;
import java.util.List;

public class DataSet {

    private final Path path;
    private final String name;
    private final List<DataRow> rows;

    public DataSet(Path path) {
        this.path = path;
        this.name = path.getFileName().toString().replace(".csv", "");
        this.rows = null;
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
