package REDO.csv;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;

public class InventoryRow extends DataRow implements Serializable{

    @CsvBindByName(column = "# For Sale", required = true)
    private String numForSale;

    @CsvBindByName(column = "For Sale Avg DOM", required = true)
    private String forSaleAvgDom;

    @CsvBindByName(column = "# Properties Sold", required = true)
    private String numSold;

    @CsvBindByName(column = "Sold Avg DOM", required = true)
    private String soldAvgDom;

    @CsvBindByName(column = "# New Listings", required = true)
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