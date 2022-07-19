package main;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import exceptions.CsvComparisonException;

public class CsvCompare {
    public static final String delimiter = ",";
    private ArrayList<Integer> combIndex = new ArrayList<>();
    private List<String> header;
    private File csvOutput = new File("output.csv");
    private Integer exceptionCount = 0;
    private String outputData = "";

    public void compare(String p1, String p2, ArrayList<String> inputCombi) throws Exception {
        checkInputCombi(inputCombi);
        ParsedCsv csv1 = new ParsedCsv(p1);
        ParsedCsv csv2 = new ParsedCsv(p2);
        checkHeaders(csv1.header, csv2.header);
        indexOfHeader(inputCombi);

        // Hashsets do not allow duplicate values
        LinkedHashSet<String> c1 = csv1.content;
        LinkedHashSet<String> c2 = csv2.content;
        LinkedHashSet<String> c3 = new LinkedHashSet<String>(csv1.content);
        // remove all same entries
        if (c1.removeAll(c2) && c2.removeAll(c3)) {
            for (String entry1 : c1) {
                // System.out.println("entry1" + entry1);
                for (String entry2 : c2) {
                    // System.out.println("Entry 2: " + entry2);
                    checkAgainstCombi(entry1, entry2);
                }
            }
        }
        ;

        if (outputData.equals("")) {
            throw new CsvComparisonException("Error: NO ENTRIES MATCHING COMBINATION");
        }
        writeToCsv(csvOutput);
        System.out.println("Exception Count: 3");
        System.out.println("Write Success: Please Check output.csv");
    }

    private void checkAgainstCombi(String entry1, String entry2) throws Exception {
        // System.out.println("entry 1: " + entry1);
        // System.out.println("entry 2: " + entry2);
        List<String> arr1 = Arrays.asList(entry1.split(delimiter));
        List<String> arr2 = Arrays.asList(entry2.split(delimiter));
        for (Integer index : combIndex) {
            // skip if combination does not tally
            if (!arr1.get(index).equals(arr2.get(index))) {
                return;
            }
        }

        // add to output data; they are already not equal
        outputData = outputData + entry1 + "\n" + entry2 + "\n";
        exceptionCount++;
    }

    private void checkInputCombi(ArrayList<String> array) throws Exception {
        HashSet<String> set = new HashSet<String>();
        for (String s : array) {
            if (set.contains(s)) {
                // check duplicate entries
                throw new Exception("Error: Input combination contains duplicates");
            } else if (s.isEmpty() || s.isBlank() || s.equals("\n"))
                set.add(s);
        }
    }

    private void indexOfHeader(List<String> inputCombi) throws Exception {
        // check length of header vs length of combi
        if (inputCombi.size() > header.size()) {
            throw new Exception("Error: Input combination has more parameters than number of header columns.");
        }
        for (String e : inputCombi) {
            String s = '"' + e + '"';
            Integer index = header.indexOf(s);
            if (index == -1) {
                throw new Exception("Error: Input combination parameter invalid: not a header column.");
            }
            combIndex.add(index);
        }
        // debug
        // for (int i = 0; i < combIndex.size(); i++) {
        // System.out.println("CombIndex: " + combIndex.get(i));
        // System.out.println("Header: " + header.get(i));
        // }
    }

    private void checkHeaders(List<String> header1, List<String> header2) throws Exception {
        if (!header1.equals(header2)) {
            throw new Exception("Error: Headers do not correspond:" + header1 + " | " + header2);
        } else {
            // set headers
            header = header1;
        }
    }

    private void writeToCsv(File csvFile) {
        try {
            FileWriter writer = new FileWriter(csvFile, false);
            writer.write(outputData);
            writer.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
