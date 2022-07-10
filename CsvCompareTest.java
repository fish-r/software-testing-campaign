import java.util.ArrayList;
import java.util.Arrays;

public class CsvCompareTest {

    public static void main(String[] args) {
        // Default combination: "Customer ID#","Account No.","Currency","Type","Balance"

        String[] combination = { "Customer ID#", "Account No.", "Type" }; // change accordingly

        String source1 = "sample_file_1.csv"; // change accordingly
        String source2 = "sample_file_3.csv";

        try {
            CsvCompare csvCompare = new CsvCompare();
            ArrayList<String> combinationInput = new ArrayList<String>(Arrays.asList(combination));
            csvCompare.compare(source1, source2,
                    combinationInput);

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