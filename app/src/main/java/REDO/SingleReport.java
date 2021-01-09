package REDO;

import REDO.csv.DataSet;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SingleReport {

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
