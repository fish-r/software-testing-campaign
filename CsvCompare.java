import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CsvCompare {

    public static final String delimiter = ",";
    private String[][] allDifference;
    private String[] combination;

    public void compare(String path1, String path2, String[] combination) throws Exception {

        // turn paths into file object
        File dataSource1 = new File(path1);
        File dataSource2 = new File(path2);
        // save unique combination for easy reference
        this.combination = combination;

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
            }
            String line1;
            String line2;
            while (((line1 = reader1.readLine()) != null) && ((line2 = reader2.readLine()) != null)) {
                System.out.println(line1);
                System.out.println(line2);
                System.out.println("--------");

                // stringDifference(line1,line2);

            }

            reader1.close();
            reader2.close();

            // handle when IO exception
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found.");
            e.printStackTrace();
        }
    }

    public static String stringDifference(String str1, String str2) {
        // check if line1 and line2 are the same
        if (!str1.equals(str2)) {
            // check against combination
            // first split the string into arrays
            String[] temp1 = str1.split(delimiter);
            String[] temp2 = str2.split(delimiter);
            // since combination tallies, loop through the array and check
            for (Integer index = 0; index < str1.length(); index++) {
                // if ()
            }

        }

        return "";
    }

}
