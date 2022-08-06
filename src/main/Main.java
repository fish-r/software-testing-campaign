package main;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

import exceptions.InputException;

public class Main {
    static final String flag = "-c";
    static final String pathPrefix = "main/csvfiles/";
    static final CsvCompare csvCompare = new CsvCompare();
    static String output = "";

    // java Main sample_file_1.csv sample_file_2.csv -c "Customer ID#","Account
    public static void main(String[] args) throws InputException {
        // Default combination: "Customer ID#","Account No.","Currency","Type","Balance"
        ArrayList<String> input = new ArrayList<>(Arrays.asList(args));
        Integer f = input.indexOf(flag);
        if (f == -1) {
            throw new InputException("Error: No input combination");
        }
        String combInput = input.get(f + 1);
        String[] combination = combInput.split(",");
        ArrayList<String> combinationInput = new ArrayList<>(Arrays.asList(combination));
        String path1 = pathPrefix + args[0];
        String path2 = pathPrefix + args[1];

        try {
            output = csvCompare.compare(combinationInput, path1, path2);
            writeToCsv(output);

        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    private static void writeToCsv(String output) {
        final File outputFile = new File("output.csv");

        try {
            FileWriter writer = new FileWriter(outputFile, false);
            writer.write(output);
            writer.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
