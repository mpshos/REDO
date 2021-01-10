/**
 * Class representing a row in a Inventory type CSV file
 */

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

    public static String getReportHeader() {
        return "Month,For Sale,New Listings,Sold";
    }

    public InventoryRow() {
        this.numForSale = "";
        this.forSaleAvgDom = "";
        this.numSold = "";
        this.soldAvgDom = "";
        this.numNewListings = "";
    }

    /**
     * Get's the value from the row corresponding to the given column
     * @return the value from the provided column
     */
    @Override
    public String getColumnVal(int colIdx) {
        return switch (colIdx) {
            case 0 -> this.getMonth();
            case 1 -> this.getNumForSale();
            case 2 -> this.getForSaleAvgDom();
            case 3 -> this.getNumSold();
            case 4 -> this.getSoldAvgDom();
            case 5 -> this.getNumNewListings();
            default -> null;
        };
    }

    /**
     * Gets the CSV row to be written to the generated report
     * @return A CSV string containing the useful data retrieved from the raw file
     */
    public String getRowOutput() {
        return String.join(",", this.getMonth(), this.getNumForSale(), this.getNumNewListings(), this.getNumSold());
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
