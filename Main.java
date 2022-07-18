
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static final String flag = "-c";

    // java Main sample_file_1.csv sample_file_2.csv -c "Customer ID#","Account
    public static void main(String[] args) {
        // Default combination: "Customer ID#","Account No.","Currency","Type","Balance"
        ArrayList<String> input = new ArrayList<>(Arrays.asList(args));
        Integer f = input.indexOf(flag);
        if (f == -1) {
            System.out.println("Error: No input combination");
            System.exit(1);
        }
        String combInput = input.get(f + 1);
        String[] combination = combInput.split(",");
        ArrayList<String> combinationInput = new ArrayList<>(Arrays.asList(combination));
        String path1 = args[0];
        String path2 = args[1];

        try {
            CsvCompare csvCompare = new CsvCompare();
            csvCompare.compare(path1, path2,
                    combinationInput);

        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }

    }
}
