import java.beans.Transient;

public class CsvCompareTest {

    public static void main(String[] args) {
        // "Customer ID#","Account No.","Currency","Type","Balance"
        String[] combination = { "Customer ID#", "Account No.", "Currency", "Type", "Balance" };

        CsvCompare csvCompare = new CsvCompare();
        try {
            csvCompare.compare("sample_file_1.csv", "sample_file_3.csv", combination);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }

}