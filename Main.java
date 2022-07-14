import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static final String flag = "-c";

    public static void main(String[] args) {
        // Default combination: "Customer ID#","Account No.","Currency","Type","Balance"
        String path1 = args[0];
        String path2 = args[1];
        ArrayList<String> input = new ArrayList<>(Arrays.asList(args));
        Integer f = input.indexOf(flag);

        try {
            String combInput = input.get(f + 1);
            String[] combination = combInput.split(",");
            System.out.println("Your input combination: " + combination);
            CsvCompare csvCompare = new CsvCompare();
            ArrayList<String> combinationInput = new ArrayList<String>(Arrays.asList(combination));
            csvCompare.compare(path1, path2,
                    combinationInput);

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Please input combination");
            System.exit(1);
        }

    }
}
