/*
 * This is the entry point for the REDO program. It handles checking the file system for data files
 * and passing them on to be generated.
 */
package REDO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

    public static void main(String[] args) {

        // Get root directory containing all raw data TODO: Accept input from command line
        Path rootDir = Paths.get("/Users/michaelshostrand/redo_test/");

        // Get all expected subfolders TODO: Make this more flexible
        Path medianPath = rootDir.resolve("Median Price");
        Path domPath = rootDir.resolve("DOM");
        Path msiPath = rootDir.resolve("MSI");

        // Set up output path
        Path outputPath = rootDir.resolve("output");

        if (Files.notExists(rootDir)) {
            System.out.println("Root directory does not exist");
            System.exit(-1);
        }

        // Create output directory if necessary
        if (Files.notExists(outputPath)) {
            try {
                Files.createDirectory(outputPath);
            }
            catch (IOException e) {
                System.out.println("Could not create output directory: " + outputPath.toString());
                e.printStackTrace();
                System.exit(-1);
            }
        }

        // Median Price
        if (Files.exists(medianPath)) {
            try {
                // Open a directory stream to iterate over the files
                DirectoryStream<Path> mediaStream = Files.newDirectoryStream(medianPath);
                CombinationReport medianPriceReport = new CombinationReport("Median Price");

                // Parse all the CSVs in the median folder
                DataSet temp;
                for (Path medianFile : mediaStream) {
                    temp = new DataSet(medianFile, DataSet.parse(medianFile, MedianPriceRow.class));

                    // Link data set to the combination report
                    medianPriceReport.addData(temp);
                }

                medianPriceReport.writeReport(outputPath.resolve("Median Price.csv"));
            }

            catch (DataMismatchException|IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.exit(-1);
            }

        }
        else {
            System.out.println("Could not find Median Price folder. Skipping that report . . . ");
        }

        // Median Price
        if (Files.exists(medianPath)) {
            try {
                // Open a directory stream to iterate over the files
                DirectoryStream<Path> mediaStream = Files.newDirectoryStream(medianPath);
                CombinationReport medianPriceReport = new CombinationReport("Median Price");

                // Parse all the CSVs in the median folder
                DataSet temp;
                for (Path medianFile : mediaStream) {
                    temp = new DataSet(medianFile, DataSet.parse(medianFile, MedianPriceRow.class));

                    // Link data set to the combination report
                    medianPriceReport.addData(temp);
                }

                medianPriceReport.writeReport(outputPath.resolve("Median Price.csv"));
            }

            catch (DataMismatchException|IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.exit(-1);
            }

        }
        else {
            System.out.println("Could not find Median Price folder. Skipping that report . . . ");
        }


    }
}
