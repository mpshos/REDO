package REDO;

import REDO.csv.DataSet;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class for generating reports based on a single CSV input such as the Inventory reports
 */
public class SingleReport {

    /**
     * Static method for writing the Single File report
     * @param data The DataSet which the report is based on
     * @param output The output file
     * @param header The header to include at the top of the output CSV
     * @throws IOException If output directory doesn't exist or an error happens when writing the file
     */
    public static void writeReport(DataSet data, Path output, String header) throws IOException {
        // Check output dir presence
        if (!Files.exists(output.getParent())) {
            throw new FileNotFoundException("Output Directory " + output.getParent() + "does not exist.");
        }

        // Open file to write to
        BufferedWriter outputWriter = Files.newBufferedWriter(output);

        // Write header
        outputWriter.write(header);
        outputWriter.newLine();

        // Write trailing thirteen rows
        for (int i = 12; i>= 0; i--) {
            outputWriter.write(data.getRow(i).getRowOutput());
            outputWriter.newLine();
        }

        outputWriter.close();
    }
}
