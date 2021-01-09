package REDO;

import com.opencsv.bean.CsvBindByPosition;

import java.io.Serializable;

public class MedianPriceRow extends DataRow implements Serializable, Combinable {

    @CsvBindByPosition(position = 1)
    private String medianPrice;

    @CsvBindByPosition(position = 2)
    private String numSold;

    public MedianPriceRow() {
        this.medianPrice = "";
        this.numSold = "";
    }

    public String getRelevantItem() {
        return this.getMedianPrice();
    }

    public String getFinalRow() {
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
