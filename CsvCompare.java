import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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

        try {
            // read file objects
            BufferedReader reader1 = new BufferedReader(new FileReader(dataSource1));
            BufferedReader reader2 = new BufferedReader(new FileReader(dataSource2));

            // check the headers of the csv files, if not raise exception
            String header1 = reader1.readLine();
            String header2 = reader2.readLine();
            if (header1.equals(header2) == false) {
                reader1.close();
                reader2.close();
                throw new Exception("Error: Headers are not the same or are in different order");
                // TODO: potentially rearrange the headers
            } else {
                // save header as array
                this.header = new ArrayList<String>(Arrays.asList(header1.split(delimiter)));
                // get the index of the given combination wrt to current headers
                for (String element : combinationInput) {
                    String current = '"' + element + '"';
                    combination_index.add(header.indexOf(current));
                }
            }
            String line1;
            String line2;
            while (((line1 = reader1.readLine()) != null) && ((line2 = reader2.readLine()) != null)) {
                // TODO: check if line is legit comma separated values
                // checkLine(line1,line2);
                stringDifference(line1, line2);

            }
            System.out.println(exceptionCount);

            reader1.close();
            reader2.close();

            // handle when IO exception
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found.");
            e.printStackTrace();
        }
    }

    public void stringDifference(String str1, String str2) throws Exception {
        // check if line1 and line2 are the same
        if (!str1.equals(str2)) {
            // first split the string into arrays
            String[] temp1 = str1.split(delimiter);
            String[] temp2 = str2.split(delimiter);

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
                    return;
                }
            }

        }
    }

    public static void writeToCsv() {

    }

}
