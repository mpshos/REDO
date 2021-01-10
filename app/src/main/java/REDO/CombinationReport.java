package REDO;

import REDO.csv.DataMismatchException;
import REDO.csv.DataRow;
import REDO.csv.DataSet;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class encapsulating the data and functions required to generate a combination report
 */
public class CombinationReport {
    /**
     * The various dataSets that will be included in the report
     */
    private final ArrayList<DataSet> dataSets;

    /**
     * The name of the report
     */
    private final String name;

    public CombinationReport(String name) {
        this.name = name;
        this.dataSets = new ArrayList<DataSet>();
    }

    public void addData(DataSet data) {
        this.dataSets.add(data);
    }

    /**
     * Write the combination report.
     * @param output The output directory
     * @throws DataMismatchException If the datasets do not match. Ex: the data ranges are not the same
     * @throws IOException If output directory doesn't exist or an error happens when writing the file
     */
    public void writeReport(Path output, ReportDuration duration) throws DataMismatchException, IOException {

        // Check output dir presence
        if (!Files.exists(output)) {
            throw new FileNotFoundException("Output Directory " + output.getParent() + "does not exist.");
        }

        // Verify that all data matches up
        if (!this.validateData()) {
            throw new DataMismatchException("The data sets referenced in this report do not match. Either the dates or the sizes are out of sync");
        }

        // Sort by file number to enforce column ordering
        this.dataSets.sort(Comparator.comparingInt(DataSet::getFileNumber));

        // Open file to write to
        BufferedWriter outputWriter = Files.newBufferedWriter(output.resolve(this.getName() + ".csv"));

        // Write header
        outputWriter.write("Month");

        for (DataSet data : this.dataSets) {
            outputWriter.write("," + data.getName());
        }

        outputWriter.newLine();

        int startIdx = (duration == ReportDuration.ONE_YEAR? 12 : 24);

        // Write trailing thirteen rows
        for (int i = startIdx; i >= 0; i-- ) {

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

    /**
     * Generates a percent change report which compares the given year to the previous year.
     * @param outDir The location to write the data
     * @param column The column in the datasets to compare
     * @return a boolean value containing the success status
     * @throws DataMismatchException If the datasets do not match. Ex: the data ranges are not the same
     * @throws IOException If output directory doesn't exist or an error happens when writing the file
     */
    public boolean writePercentChangeReport(Path outDir, int column) throws DataMismatchException, IOException {
        // Check output presence
        if (!Files.exists(outDir)) {
            throw new FileNotFoundException("Output Directory " + outDir.toString() + "does not exist.");
        }

        // Verify that all data matches up
        if (!this.validateData()) {
            throw new DataMismatchException("The data sets referenced in this report do not match. Either the dates or the sizes are out of sync");
        }

        if (column == 0 ) {
            System.out.println("Error: Attempting to create a " + this.getName() + " percent change report on column " + column + "\nMonth column invalid.");
            return false;
        }

        // Sort by file number to enforce column ordering
        this.dataSets.sort(Comparator.comparingInt(DataSet::getFileNumber));

        // Open file to write to
        BufferedWriter outputWriter = Files.newBufferedWriter(outDir.resolve(this.getName() + " Percent Change.csv"));

        // Write header
        outputWriter.write("Month");

        for (DataSet data : this.dataSets) {
            outputWriter.write("," + data.getName());
        }

        outputWriter.newLine();

        // Write one years worth
        for (int i = 11; i >= 0; i-- ) {

            outputWriter.write(this.dataSets.get(0).getRow(i).getMonth() + ",");

            for (int j = 0; j < this.dataSets.size(); j++ ){

                // Compare row i to the row one year in the past
                DataRow row = this.dataSets.get(j).getRow(i);
                DataRow pastRow = this.dataSets.get(j).getRow(i + 12);

                float ratio = Float.parseFloat(row.getColumnVal(column)) / Float.parseFloat(pastRow.getColumnVal(column));

                // Don't print comma separator on last item
                if (j == this.dataSets.size() - 1) {
                    outputWriter.write(String.format("%d%%", Math.round((ratio - 1) * 100)));
                }
                else {
                    outputWriter.write(String.format("%d%%,", Math.round((ratio - 1) * 100)));
                }
            }

            outputWriter.newLine();
        }

        outputWriter.close();

        return true;

    }

    /**
     * Checks that all the DataSets have the same size and date range
     * @return a boolean value indicating data validity
     */
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

    public int getNumDataSets() {
        return this.dataSets.size();
    }

    public String getName() {
        return name;
    }
}
