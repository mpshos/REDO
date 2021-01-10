# REDO - Real Estate Data Organizer
Command line application for modifying data from BrokerMetrics application to make 
data visualization easier.

## Installation
### With Prebuild zip on a Windows machine
1. Extract redo.zip to your local machine. ex: C:\redo
2. Add redo/bin to PATH
    1. Go to Control Panel > System and Security > System
    2. Select Advanced System Settings on left panel
    3. Select Environment Variables
    4. Select the "Path" variable and press the Edit button.
    5. Press the New button on the right side
    6. Enter the path to redo/bin. Using the previous example location, "C:\redo\bin\" would be added

## Usage
After installation, the program can be run from a terminal window using the "redo" command.

### Folder Structure
Currently, the program requires that the data folder be structured in a specific way.
There should be a root directory that contains subfolders for each data type.

The names of the subfolders should be the following: Median Price, DOM, MSI, Inventory

A visual representation of the folder structure is below:

- root (named whatever you want)
    - DOM
        - Condo.csv
        - SFH.csv
        - etc.
    - Inventory
        - Inventory Condo.csv
        - Inventory SFH.csv  
        - etc.
    - Median Price
        - Condo.csv
        - SFH.csv
        - etc.
    - MSI
        - Condo.csv
        - SFH.csv
        - etc.
    
**This folder structure can be automatically created using the --init option**

Example: This will create the folder structure at a folder named eastbay. "redo --init --target eastbay"

### Generating Reports
Once you have the data from BrokerMetrics exported and stored in the correct structure,
simply run "redo --target &lt;path to root directory&gt;"
If you do not set the --target option, the program will look in the working directory.

For the combination reports such as median price, the column name comes from the file name.
Name the exports appropriately to avoid manual editing. For example, in the structure above, the Median Price
report will contain two columns, Condo and SFH.

## Tips
On windows you can open a shell/command window at any location open in explorer by
Shift - right clicking in the folder then select open command window, open powershell, etc.

### Suggested Workflow
This is a pretty straightforward way to do these exports and report generations.
I'd keep one folder where I store all of these. Ex: Documents/BrokerMetricsReports.

When starting a new cycle of export and export (in this example we're getting data for eastbay): 
1. Open a shell window at Documents/BrokerMetricReports
2. Initialize the folder structure: redo --init --target eastbay
3. Export all data via BrokerMetrics to the appropriate folder in Documents/BrokerMetricReports/eastbay
4. Generate reports: redo --target eastbay
    