package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import CsvCompare;
import Exceptions.CsvParsingException;

@RunWith(Parameterized.class)
public class CsvCompareTest {

    public CsvCompareTest(String fileName1, String fileName2, ArrayList<String> inputCombi, Object expectedOutput) {
        this.fileInput1 = "./TestFiles/" + fileName1;
        this.fileInput2 = "./TestFiles/" + fileName2;
        // this.expectedError = expectedError;
        this.inputCombi = inputCombi;
        this.expectedOutput = expectedOutput;

    }

    private CsvCompare csvCompare = new CsvCompare();
    private String fileInput1;
    private String fileInput2;
    // private Exception expectedError;
    private ArrayList<String> inputCombi;
    private Object expectedOutput;

    @Parameters
    public static Collection<Object[]> input() {
        return Arrays.asList(new Object[][] {
                { "empty.csv", "test_1.csv", new Exception("Error: CSV file is empty.") },
                { "empty_string.csv", "test_1.csv", new Exception("Error: ") }
        });

    }

    @Test(expected = CsvParsingException.class)
    public void shouldThrowException() throws Exception {
        System.out.println("Running Parameterized Test for inputs: " + fileInput1 + " and " + fileInput2);
        csvCompare.compare(fileInput1, fileInput2, inputCombi);
        // output =
    }

}
