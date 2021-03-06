package REDO.csv;

import REDO.Combinable;
import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;

/**
 * Class representing a row in a MSI type CSV file
 */
public class MsiRow extends DataRow implements Serializable, Combinable {

    @CsvBindByName(column = "MSI-SOLD", required = true)
    private String msiSold;

    @CsvBindByName(column = "# Properties For Sale Last Day of Month", required = true)
    private String propsForSale;

    @CsvBindByName(column = "# Properties Sold", required = true)
    private String propsSold;

    public MsiRow() {
        this.msiSold = "";
        this.propsForSale = "";
        this.propsSold = "";
    }

    /**
     * Get's the value from the row corresponding to the given column
     * @return the value from the provided column
     */
    @Override
    public String getColumnVal(int colIdx) {
        return switch (colIdx) {
            case 0 -> this.getMonth();
            case 1 -> this.getMsiSold();
            case 2 -> this.getPropsForSale();
            case 3 -> this.getPropsSold();
            default -> null;
        };
    }

    /**
     * Gets the data item used when building a combination report
     * @return the MSI-Sold value for this row
     */
    @Override
    public String getRelevantItem() {
        return this.getMsiSold();
    }

    public String getRowOutput() {
        // TODO: Maybe do this. Don't think it'll be used since it's a combo field
        return "";
    }

    public String getMsiSold() {
        return msiSold;
    }

    public void setMsiSold(String msiSold) {
        this.msiSold = msiSold;
    }

    public String getPropsForSale() {
        return propsForSale;
    }

    public void setPropsForSale(String propsForSale) {
        this.propsForSale = propsForSale;
    }

    public String getPropsSold() {
        return propsSold;
    }

    public void setPropsSold(String propsSold) {
        this.propsSold = propsSold;
    }
}
