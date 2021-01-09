package REDO.csv;

import com.opencsv.bean.CsvBindByPosition;

import java.io.Serializable;

public class InventoryRow extends DataRow implements Serializable{

    @CsvBindByPosition(position = 1)
    private String numForSale;

    @CsvBindByPosition(position = 2)
    private String forSaleAvgDom;

    @CsvBindByPosition(position = 3)
    private String numSold;

    @CsvBindByPosition(position = 4)
    private String soldAvgDom;

    @CsvBindByPosition(position = 5)
    private String numNewListings;

    public InventoryRow() {
        this.numForSale = "";
        this.forSaleAvgDom = "";
        this.numSold = "";
        this.soldAvgDom = "";
        this.numNewListings = "";
    }

    public String getFinalRow() {
        return String.join(",", this.getNumForSale(), this.getNumNewListings(), this.getNumSold());
    }

    public String getNumForSale() {
        return numForSale;
    }

    public void setNumForSale(String numForSale) {
        this.numForSale = numForSale;
    }

    public String getForSaleAvgDom() {
        return forSaleAvgDom;
    }

    public void setForSaleAvgDom(String forSaleAvgDom) {
        this.forSaleAvgDom = forSaleAvgDom;
    }

    public String getNumSold() {
        return numSold;
    }

    public void setNumSold(String numSold) {
        this.numSold = numSold;
    }

    public String getSoldAvgDom() {
        return soldAvgDom;
    }

    public void setSoldAvgDom(String soldAvgDom) {
        this.soldAvgDom = soldAvgDom;
    }

    public String getNumNewListings() {
        return numNewListings;
    }

    public void setNumNewListings(String numNewListings) {
        this.numNewListings = numNewListings;
    }
}
