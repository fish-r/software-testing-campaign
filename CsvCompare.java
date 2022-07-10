import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CsvCompare {

    public static final String delimiter = ",";
    private ArrayList<Integer> combination_index = new ArrayList<>();
    private ArrayList<String> header;
    private File csvOutput = new File("output.csv");
    private Integer exceptionCount = 0;

    public void compare(String path1, String path2, ArrayList<String> combinationInput) throws Exception {

        // turn paths into file object
        File dataSource1 = new File(path1);
        File dataSource2 = new File(path2);
        clearCsv(this.csvOutput);

        try {
            // read file objects
            BufferedReader reader1 = new BufferedReader(new FileReader(dataSource1));
            BufferedReader reader2 = new BufferedReader(new FileReader(dataSource2));

            // check the headers of the csv files, if not raise exception
            String header1 = reader1.readLine();
            String header2 = reader2.readLine();
            checkHeaders(combinationInput, reader1, reader2, header1, header2);
            String line1;
            String line2;
            while (((line1 = reader1.readLine()) != null) && ((line2 = reader2.readLine()) != null)) {
                checkLine(line1, line2);
                stringDifference(line1, line2);

            }
            System.out.println(exceptionCount);

            reader1.close();
            reader2.close();

            // handle when IO exception
        } catch (FileNotFoundException e) {
            System.out.println("Error: File could not be found.");
            e.printStackTrace();
        }
    }

    private void checkHeaders(ArrayList<String> combinationInput, BufferedReader reader1, BufferedReader reader2,
            String header1, String header2) throws IOException, Exception {
        if ((header1.equals(header2) == false) || (header1.equals(null) || (header2.equals(null)))) {
            reader1.close();
            reader2.close();
            throw new Exception("Error: Headers are malformed.");
        } else {
            // save header as array
            this.header = new ArrayList<String>(Arrays.asList(header1.split(delimiter)));
            // get the index of the given combination wrt to current headers
            for (String element : combinationInput) {
                String current = '"' + element + '"';
                combination_index.add(header.indexOf(current));
            }
        }
    }

    public void checkLine(String line1, String line2) throws Exception {
        try {
            line1.indexOf(delimiter);
            line2.indexOf(delimiter);
        } catch (Exception e) {
            System.out.println("Error; String is not a valid csv entry");
            System.out.println(e);
        }
    }

    public void stringDifference(String line1, String line2) throws Exception {
        // check if line1 and line2 are the same
        if (!line1.equals(line2)) {
            // first split the string into arrays
            String[] temp1 = line1.split(delimiter);
            String[] temp2 = line2.split(delimiter);

            // check for length of input
            if (temp1.length != temp2.length) {
                throw new Exception("Entry is of different length");
            }

            // loop through the array and check combination parameters
            for (Integer index : combination_index) {
                // if any of the combination parameters not the same, skip this entry
                if (!temp1[index].equals(temp2[index])) {
                    return;
                }
            }
            // if combination tallies, loop through and check difference
            System.out.println("checking for difference:");
            for (Integer i = 0; i < temp1.length; i++) {
                if (!temp1[i].equals(temp2[i])) {
                    // if different, note the header and the difference
                    String mismatch = header.get(i);
                    // write to new csv
                    System.out.println(mismatch);
                    exceptionCount++;
                    writeToCsv(this.csvOutput, line1, line2, mismatch);
                    return;
                }
            }

        }
    }

    public void writeToCsv(File csvFile, String line1, String line2, String mismatch) {
        try {
            FileWriter writer = new FileWriter(csvFile, true);
            writer.write(line1 + " | " + line2 + " | " + "mismatch: " + mismatch + "\n");
            writer.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void clearCsv(File csvFile) {
        try {
            FileWriter writer = new FileWriter(csvFile, false);
            writer.write("");
            writer.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
