package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import main.CsvCompare;
import main.CsvParser;
import main.CsvParser.ParsedCsv;

@RunWith(Parameterized.class)
public class CsvCompareTest {
    private enum Type {
        INCOMPATIBLE, COMPATIBLE
    };

    private CsvCompare csvCompare = new CsvCompare();
    private CsvParser csvParser = new CsvParser();
    private String path1;
    private String path2;
    private ArrayList<String> inputCombi = new ArrayList<>();
    private Type type;
    private final String pathPrefix = "./bin/test/testfiles/";
    private final String emptyString = "";
    private final String expectedOutput1 = "\"ID1\",\"BOS963211\",\"USD\",\"SAVINGS\",\"962510\"\n"
            + "\"ID1\",\"BOS492681\",\"CAD\",\"CURRENT\",\"426505\"\n"
            + "\"ID2\",\"BOS85992\",\"AUD\",\"CURRENT\",\"989898\"\n"
            + "\"ID2\",\"BOS760122\",\"EUR\",\"SAVINGS\",\"87278\"\n"
            + "\"ID3\",\"BOS656613\",\"USD\",\"CURRENT\",\"595290\"\n"
            + "\"ID3\",\"BOS473393\",\"INR\",\"CURRENT\",\"476638\"\n"
            + "\"ID4\",\"BOS14824\",\"INR\",\"SAVINGS\",\"772578\"\n"
            + "\"ID4\",\"BOS538154\",\"HKD\",\"SAVINGS\",\"425024\"\n";

    public CsvCompareTest(String path1, String path2, String inputCombi, Type type) {
        this.path1 = pathPrefix + path1;
        this.path2 = pathPrefix + path2;
        this.inputCombi.add(inputCombi);
        this.type = type;
        // this.expectedError = expectedError;

    }

    // parameterized test
    @Parameters
    public static Collection<Object[]> input() {
        return Arrays.asList(new Object[][] {
                { "valid_1.csv", "valid_1.csv", "Customer ID#", Type.INCOMPATIBLE },
                { "valid_1.csv", "valid_4_no_balance.csv", "Customer ID#", Type.INCOMPATIBLE },
                { "valid_1.csv", "valid_2.csv", "Customer ID#", Type.COMPATIBLE },
                { "valid_1.csv", "valid_dupl_lines_2.csv", "Customer ID#", Type.COMPATIBLE },
                { "valid_dupl_lines_1.csv", "valid_dupl_lines_2.csv", "Customer ID#", Type.COMPATIBLE },
        });

    }

    @Test
    public void incompatibleComparisonShouldNotCrashSystem() {
        Assume.assumeTrue(type == Type.INCOMPATIBLE);
        csvCompare.compare(this.csvParser, this.inputCombi, this.path1, this.path2);
        String actualOutput = csvCompare.getOutputData();
        assertEquals(emptyString, actualOutput);
    }

    @Test
    public void compatibleComparisonShouldCompareCorrectly() {
        Assume.assumeTrue(type == Type.COMPATIBLE);
        csvCompare.compare(csvParser, inputCombi, path1, path2);
        String actualOutput = csvCompare.getOutputData();
        assertEquals(expectedOutput1, actualOutput);
    }

}
