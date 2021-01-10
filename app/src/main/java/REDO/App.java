/*
 * This is the entry point for the REDO program. It handles checking the file system for data files
 * and passing them on to be generated.
 */
package REDO;

import REDO.csv.*;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

    public static void main(String[] args) {

        // Set up argument parser
        ArgumentParser parser = ArgumentParsers.newFor("REDO - Real Estate Data Organizer").build();

        parser.addArgument("--target")
                .dest("target")
                .type(String.class)
                .setDefault("./")
                .help("The target directory containing subdirectories with the raw data CSVs.");

        parser.addArgument("--init")
                .action(Arguments.storeTrue())
                .help("Executes folder initialization operation instead of generating reports. Initialization will create the subfolders for the various reports");

        Namespace res = null;

        try {
            res = parser.parseArgs(args);

        } catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }

        // Get root directory containing all raw data
        Path rootDir = Paths.get(res.getString("target"));

        // Get all expected subfolders TODO: Make this more flexible
        Path medianPath = rootDir.resolve("Median Price");
        Path domPath = rootDir.resolve("DOM");
        Path msiPath = rootDir.resolve("MSI");
        Path inventoryPath = rootDir.resolve("Inventory");

        // Set up output path
        Path outputPath = rootDir.resolve("output");

        // Folder init operation
        if (res.getBoolean("init")) {
            try {
                // Create all expected directories
                Files.createDirectories(rootDir);

                if (Files.notExists(medianPath)) {
                    Files.createDirectory(medianPath);
                }

                if (Files.notExists(domPath)) {
                    Files.createDirectory(domPath);
                }

                if (Files.notExists(msiPath)) {
                    Files.createDirectory(msiPath);
                }

                if (Files.notExists(inventoryPath)) {
                    Files.createDirectory(inventoryPath);
                }
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.exit(-1);
            }

            // Exit program
            System.out.println("Data folder " + rootDir.toString() + " initialized.");
            System.exit(0);
        }

        if (Files.notExists(rootDir)) {
            System.out.println("Root directory: " + rootDir.toString() + " does not exist");
            System.exit(-1);
        }

        // Generate reports
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
                DirectoryStream<Path> dirStream = Files.newDirectoryStream(medianPath);
                CombinationReport medianPriceReport = new CombinationReport("Median Price");

                // Parse all the CSVs in the median folder
                DataSet temp;
                for (Path medianFile : dirStream) {
                    temp = new DataSet(medianFile, DataSet.parse(medianFile, MedianPriceRow.class));

                    // Link data set to the combination report
                    medianPriceReport.addData(temp);
                }

                if (medianPriceReport.getNumDataSets() > 0) {
                    medianPriceReport.writeReport(outputPath, ReportDuration.TWO_YEARS);
                    medianPriceReport.writePercentChangeReport(outputPath, 1);
                }
                else {
                    System.out.println("Median folder is empty. Skipping . . .");
                }
            }

            catch (DataMismatchException | IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.exit(-1);
            }

        }
        else {
            System.out.println("Could not find Median Price folder. Skipping that report . . . ");
        }

        // DOM
        if (Files.exists(domPath)) {
            try {
                // Open a directory stream to iterate over the files
                DirectoryStream<Path> dirStream = Files.newDirectoryStream(domPath);
                CombinationReport domReport = new CombinationReport("DOM");

                // Parse all the CSVs in the DOM folder
                DataSet temp;
                for (Path file : dirStream) {
                    temp = new DataSet(file, DataSet.parse(file, DomRow.class));

                    // Link data set to the combination report
                    domReport.addData(temp);
                }

                if (domReport.getNumDataSets() > 0) {
                    domReport.writeReport(outputPath, ReportDuration.ONE_YEAR);
                }
                else {
                    System.out.println("DOM folder is empty. Skipping . . .");
                }
            }

            catch (DataMismatchException|IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.exit(-1);
            }

        }
        else {
            System.out.println("Could not find DOM folder. Skipping that report . . . ");
        }

        // MSI
        if (Files.exists(msiPath)) {
            try {
                // Open a directory stream to iterate over the files
                DirectoryStream<Path> dirStream = Files.newDirectoryStream(msiPath);
                CombinationReport msiReport = new CombinationReport("MSI");

                // Parse all the CSVs in the MSI folder
                DataSet temp;
                for (Path file : dirStream) {
                    temp = new DataSet(file, DataSet.parse(file, MsiRow.class));

                    // Link data set to the combination report
                    msiReport.addData(temp);
                }

                if (msiReport.getNumDataSets() > 0) {
                    msiReport.writeReport(outputPath, ReportDuration.ONE_YEAR);
                }
                else {
                    System.out.println("MSI folder is empty. Skipping . . .");
                }
            }

            catch (DataMismatchException|IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.exit(-1);
            }

        }
        else {
            System.out.println("Could not find MSI folder. Skipping that report . . . ");
        }

        // Inventory
        if (Files.exists(inventoryPath)) {
            try {
                // Open a directory stream to iterate over the files
                DirectoryStream<Path> dirStream = Files.newDirectoryStream(inventoryPath);

                // Parse all the CSVs in the MSI folder
                DataSet temp;
                for (Path file : dirStream) {
                    temp = new DataSet(file, DataSet.parse(file, InventoryRow.class));

                    SingleReport.writeReport(temp, outputPath.resolve(file.getFileName()), InventoryRow.getReportHeader(), ReportDuration.TWO_YEARS);
                }

            }

            catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.exit(-1);
            }

        }
        else {
            System.out.println("Could not find Inventory folder. Skipping that report . . . ");
        }


    }
}
