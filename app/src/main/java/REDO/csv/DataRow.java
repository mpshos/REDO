/**
 * This is the base class for all data row classes
 */

package REDO.csv;

import com.opencsv.bean.CsvBindByPosition;

public abstract class DataRow {

    @CsvBindByPosition(position=0)
    private String month;

    abstract public String getFinalRow();

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
