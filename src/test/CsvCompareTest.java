package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import exceptions.CsvParsingException;
import main.CsvCompare;
import main.ParsedCsv;

@RunWith(Parameterized.class)
public class CsvCompareTest {

    private CsvCompare csvCompare = new CsvCompare();
    private String fileInput1;
    private String fileInput2;
    // private Exception expectedError;
    private ArrayList<String> inputCombi;
    private Object expectedOutput;
    private final String pathPrefix = "./testfiles/";
    private final String outputCsv = "../main/output.csv";

    public CsvCompareTest(String fileName1, String fileName2, ArrayList<String> inputCombi) {
        this.fileInput1 = pathPrefix + fileName1;
        this.fileInput2 = pathPrefix + fileName2;
        // this.expectedError = expectedError;
        this.inputCombi = inputCombi;

    }

    @Parameters
    public static Collection<Object[]> input() {
        return Arrays.asList(new Object[][] {
                { "empty.csv", "test_1.csv", new Exception("Error: CSV file is empty.") },
                { "empty_string.csv", "test_1.csv", new Exception("Error: ") }
        });

    }

    @Test(expected = CsvParsingException.class)
    public void shouldThrowParsingException() throws CsvParsingException {
        System.out.println("Running Parameterized Test for inputs: " + fileInput1 + " and " + fileInput2);
        try {
            csvCompare.compare(fileInput1, fileInput2, inputCombi);
            ParsedCsv parsedOutput = new ParsedCsv(outputCsv);
            this.expectedOutput = parsedOutput.content;

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
