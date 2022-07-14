import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class CsvCompare {
    public static final String delimiter = ",";
    private List<Integer> combIndex;
    private List<String> header;
    private File csvOutput = new File("output.csv");
    private Integer exceptionCount = 0;
    private String outputData;

    public void compare(String p1, String p2, ArrayList<String> inputCombi) throws Exception {
        ParsedCsv csv1 = new ParsedCsv(p1);
        ParsedCsv csv2 = new ParsedCsv(p2);
        indexOfHeader(inputCombi);
        checkHeaders(csv1.header, csv2.header);
        // LinkedHashMap<String, List<String>> c1 = csv1.content;
        // LinkedHashMap<String, List<String>> c2 = csv2.content;
        LinkedHashSet<String> c1 = csv1.content;
        LinkedHashSet<String> c2 = csv2.content;
        // remove all same entries
        c1.removeAll(c2);
        for (String entry1 : c1) {
            for (String entry2 : c2) {
                checkCombi(entry1, entry2);
            }
        }
        writeToCsv(csvOutput);

    }

    private void checkCombi(String entry1, String entry2) {
        List<String> arr1 = Arrays.asList(entry1.strip().split(delimiter));
        List<String> arr2 = Arrays.asList(entry2.strip().split(delimiter));

        for (Integer index : combIndex) {
            // skip if combination does not tally
            if (arr1.get(index) != arr2.get(index)) {
                return;
            }
        }
        // add to output data; they are already not equal
        outputData = outputData + entry1 + "\n" + entry2 + "\n";
        exceptionCount++;
    }

    private void indexOfHeader(List<String> inputCombi) {
        for (String e : inputCombi) {
            combIndex.add(header.indexOf(e));
        }
    }

    private void checkHeaders(List<String> header1, List<String> header2) throws Exception {
        if (header1 != header2) {
            throw new Exception("Error: Headers do not correspond.");
        } else {
            // set headers
            header = header1;
        }
    }

    public void writeToCsv(File csvFile) {
        try {
            FileWriter writer = new FileWriter(csvFile, false);
            writer.write(outputData);
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
