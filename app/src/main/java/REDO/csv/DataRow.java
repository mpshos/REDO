/**
 * This is the base class for all data row classes
 */

package REDO.csv;

import com.opencsv.bean.CsvBindByName;

public abstract class DataRow {

    @CsvBindByName(column = "Month", required = true)
    private String month;

    abstract public String getFinalRow();

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
