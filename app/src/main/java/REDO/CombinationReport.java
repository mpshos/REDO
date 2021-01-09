package REDO;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class CombinationReport {

    private final ArrayList<DataSet> dataSets;
    private final String name;

    public CombinationReport(String name) {
        this.name = name;
        this.dataSets = new ArrayList<DataSet>();
    }

    public void addData(DataSet data) {
        this.dataSets.add(data);
    }

    public void writeReport(Path output) throws DataMismatchException, FileNotFoundException, IOException {

        // Check output dir presence
        if (!Files.exists(output.getParent())) {
            throw new FileNotFoundException("Output Directory " + output.getParent() + "does not exist.");
        }

        // Verify that all data matches up
        if (!this.validateData()) {
            throw new DataMismatchException("The data sets referenced in this report do not match. Either the dates or the sizes are out of sync");
        }

        // Open file to write to
        BufferedWriter outputWriter = Files.newBufferedWriter(output);

        // Write header
        outputWriter.write("Month");

        for (DataSet data : this.dataSets) {
            outputWriter.write("," + data.getName());
        }

        outputWriter.newLine();

        // Write trailing thirteen rows
        for (int i = 13; i > 0; i-- ) {

            outputWriter.write(this.dataSets.get(0).getRow(i).getMonth() + ",");

            for (int j = 0; j < this.dataSets.size(); j++ ){
                // TODO: Reevaluate this cast
                Combinable row = (Combinable) this.dataSets.get(j).getRow(i);

                // Don't print comma separator on last item
                if (j == this.dataSets.size() - 1) {
                    outputWriter.write(row.getRelevantItem());
                }
                else {
                    outputWriter.write(row.getRelevantItem() + ",");
                }
            }

            outputWriter.newLine();
        }

        outputWriter.close();

    }

    private boolean validateData() {
        if (this.dataSets.size() == 0) {
            return false;
        }

        if (this.dataSets.size() == 1){
            return true;
        }

        // Verify that all the data sets are the same size and are from the same time period
        int dataSize = this.dataSets.get(0).getSize();
        String firstDate = this.dataSets.get(0).getRow(0).getMonth();

        for (int i = 1; i < this.dataSets.size(); i++ ) {
            if (this.dataSets.get(i).getSize() != dataSize || !firstDate.equals(this.dataSets.get(i).getRow(0).getMonth())) {
                return false;
            }
        }

        return true;
    }
}
