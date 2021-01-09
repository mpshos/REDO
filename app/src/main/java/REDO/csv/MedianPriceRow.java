package REDO.csv;

import REDO.Combinable;
import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;

public class MedianPriceRow extends DataRow implements Serializable, Combinable {

    @CsvBindByName(column = "Sold Median $", required = true)
    private String medianPrice;

    @CsvBindByName(column = "# Sold", required = true)
    private String numSold;

    public MedianPriceRow() {
        this.medianPrice = "";
        this.numSold = "";
    }

    @Override
    public String getRelevantItem() {
        return this.getMedianPrice();
    }

    public String getRowOutput() {
        // TODO: Maybe do this. Don't think it'll be used since it's a combo field
        return "";
    }

    public String getMedianPrice() {
        return medianPrice;
    }

    public void setMedianPrice(String medianPrice) {
        this.medianPrice = medianPrice;
    }

    public String getNumSold() {
        return numSold;
    }

    public void setNumSold(String numSold) {
        this.numSold = numSold;
    }
}
