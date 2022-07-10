import java.util.ArrayList;
import java.util.Arrays;

public class CsvCompareTest {
    String[] combination;

    public static void main(String[] args) {
        /*
         * Input combination as a String array
         */
        // Default combination: "Customer ID#","Account No.","Currency","Type","Balance"
        // TODO: add way to set combination, set up tests

        String[] combination = { "Customer ID#", "Account No.", "Type" };

        CsvCompare csvCompare = new CsvCompare();
        try {
            ArrayList<String> combinationInput = new ArrayList<String>(Arrays.asList(combination));
            csvCompare.compare("sample_file_1.csv", "sample_file_3.csv", combinationInput);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
     * Future tests
     * 
     * Test 1: file 1 and file 2
     * 
     * Test 2: file 1 and file 3
     * - ID, account num, currency, account type : 2 exceptions
     * - ID, account num, account type: 3 exceptions
     * 
     */

}