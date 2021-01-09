package REDO;

import com.opencsv.bean.CsvBindByPosition;

import java.io.Serializable;

public class MsiRow extends DataRow implements Serializable, Combinable{

    @CsvBindByPosition(position = 1)
    private String msiSold;

    @CsvBindByPosition(position = 2)
    private String propsForSale;

    @CsvBindByPosition(position = 3)
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

    public String getFinalRow() {
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
