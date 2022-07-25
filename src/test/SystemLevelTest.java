package test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import main.CsvCompare;
import main.ParsedCsv;

@RunWith(Parameterized.class)
public class SystemLevelTest {
    private enum Type {
        INVALID_FILE, INVALID_INPUT, VALID
    };

    private ParsedCsv file1;
    private ParsedCsv file2;
    private final String pathPrefix = "./bin/test/testfiles/";
    private Type type;
    private File outputFile;
    private ArrayList<String> inputCombi = new ArrayList<String>();
    private CsvCompare csvCompare = new CsvCompare();

    public SystemLevelTest(String path1, String path2, String[] inputs, String outputFilePath, Type type) {
        this.file1 = new ParsedCsv(pathPrefix + path1);
        this.file2 = new ParsedCsv(pathPrefix + path2);
        this.type = type;
        this.outputFile = new File(outputFilePath);
        for (String s : inputs) {
            inputCombi.add(s);
        }
    }

    @Parameters
    public static Collection<Object[]> input() {
        String[] input1 = { "Customer ID#" };
        String[] input2 = { "Customer ID#", "Account No.", "Type" };
        String[] input3 = { "Customer ID#", "Account No.", "Currency", "Type", "Balance"
        };

        return Arrays.asList(new Object[][] {
                { "valid_1.csv", "valid_1.csv", input1, "output.csv", Type.INVALID_FILE },
                { "valid_1.csv", "valid_4_no_balance.csv", input1, Type.INVALID_FILE },
                { "valid_1.csv", "valid_4_no_balance.csv", "", Type.INVALID_INPUT },
                { "valid_1.csv", "valid_4_no_balance.csv", input3, Type.INVALID_INPUT },
                { "valid_1.csv", "valid_2.csv", input2, Type.VALID },
                { "valid_1.csv", "valid_dupl_lines_2.csv", input2, Type.VALID },
                { "valid_dupl_lines_1.csv", "valid_dupl_lines_2.csv", input2, Type.VALID },
        });
    }

    @Test
    public void invalidCombinationInputShouldNotCrashSystem() {
        Assume.assumeTrue(type == Type.INVALID_INPUT);
        csvCompare.compare(file1, file2, inputCombi);
    }

    @Test
    public void invalidCsvFileShouldNotCrashSystem() {
        Assume.assumeTrue(type == Type.INVALID_FILE);
        csvCompare.compare(file1, file2, inputCombi);
    }

    @Test
    public void validInputsShouldOutputCorrectly() {
        Assume.assumeTrue(type == Type.VALID);
        csvCompare.compare(file1, file2, inputCombi);
    }
}
