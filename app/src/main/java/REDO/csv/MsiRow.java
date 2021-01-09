package REDO.csv;

import REDO.Combinable;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.io.Serializable;

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
