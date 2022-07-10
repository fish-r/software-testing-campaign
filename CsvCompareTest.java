import java.util.ArrayList;
import java.util.Arrays;

public class CsvCompareTest {

    public static void main(String[] args) {
        // "Customer ID#","Account No.","Currency","Type","Balance"
        String[] combination = { "Customer ID#", "Account No.", "Currency", "Type" };
        for (String str : combination) {
            System.out.println(str);
        }
        CsvCompare csvCompare = new CsvCompare();
        try {
            ArrayList<String> combinationInput = new ArrayList<String>(Arrays.asList(combination));
            csvCompare.compare("sample_file_1.csv", "sample_file_3.csv", combinationInput);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }

}