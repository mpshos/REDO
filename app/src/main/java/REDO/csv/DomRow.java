package REDO.csv;

import REDO.Combinable;
import com.opencsv.bean.CsvBindByPosition;

import java.io.Serializable;

public class DomRow extends DataRow implements Serializable, Combinable {

    @CsvBindByPosition(position = 1)
    private String propsUnderContract;

    @CsvBindByPosition(position = 2)
    private String avgDom;

    public DomRow() {
        this.propsUnderContract = "";
        this.avgDom = "";
    }

    @Override
    public String getRelevantItem() { return this.getAvgDom(); }

    public String getFinalRow() {
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
