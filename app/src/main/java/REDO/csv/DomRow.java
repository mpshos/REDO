/**
 * Class representing a row in a DOM type CSV file
 */

package REDO.csv;

import REDO.Combinable;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;

public class DomRow extends DataRow implements Serializable, Combinable {

    @CsvBindByName(column = "# Properties Under Contract", required = true)
    private String propsUnderContract;

    @CsvBindByName(column = "Avg DOM", required = true)
    private String avgDom;

    public DomRow() {
        this.propsUnderContract = "";
        this.avgDom = "";
    }

    /**
     * Get's the value from the row corresponding to the given column
     * @return the value from the provided column
     */
    @Override
    public String getColumnVal(int colIdx) {
        return switch (colIdx) {
            case 0 -> this.getMonth();
            case 1 -> this.getPropsUnderContract();
            case 2 -> this.getAvgDom();
            default -> null;
        };
    }

    /**
     * Gets the data item used when building a combination report
     * @return the Average DOM value
     */
    @Override
    public String getRelevantItem() { return this.getAvgDom(); }

    public String getRowOutput() {
        // TODO: Maybe do this. Don't think it'll be used since it's a combo field
        return "";
    }

    public String getPropsUnderContract() {
        return propsUnderContract;
    }

    public void setPropsUnderContract(String propsUnderContract) {
        this.propsUnderContract = propsUnderContract;
    }

    public String getAvgDom() {
        return avgDom;
    }

    public void setAvgDom(String avgDom) {
        this.avgDom = avgDom;
    }
}
