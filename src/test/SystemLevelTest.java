package test;

import static org.junit.Assert.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import main.CsvCompare;

@RunWith(Parameterized.class)
public class SystemLevelTest {
    private enum Type {
        INVALID_FILE, INVALID_INPUT, VALID
    };

    private String path1;
    private String path2;
    private final String pathPrefix = "./bin/test/testfiles/";
    private final Path resultPath = Path.of("./bin/test/testfiles/result_1.csv");
    private Type type;
    private String actual;
    private ArrayList<String> inputCombi = new ArrayList<String>();
    private CsvCompare csvCompare = new CsvCompare();

    public SystemLevelTest(String path1, String path2, String[] inputs, Type type) {
        this.path1 = pathPrefix + path1;
        this.path2 = pathPrefix + path2;
        this.type = type;
        for (String s : inputs) {
            inputCombi.add(s);
        }
    }

    @Parameters
    public static Collection<Object[]> input() {
        String[] input1 = { "Customer ID#" };
        String[] input2 = { "Customer ID#", "Account No.", "Type" };
        String[] input3 = { "Customer ID#", "Account No.", "Currency", "Type", "Balance" };
        String[] input4 = { "Invalid Header" };
        String[] emptyInput = { "" };

        return Arrays.asList(new Object[][] {
                { "valid_1.csv", "valid_1.csv", input1, Type.INVALID_FILE },
                { "valid_1.csv", "valid_4_no_balance.csv", input1, Type.INVALID_FILE },
                { "valid_1.csv", "valid_4_no_balance.csv", emptyInput, Type.INVALID_INPUT },
                { "valid_1.csv", "valid_4_no_balance.csv", input3, Type.INVALID_INPUT },
                { "valid_1.csv", "valid_4_no_balance.csv", input4, Type.INVALID_INPUT },
                { "sample_file_1.csv", "sample_file_3.csv", input2, Type.VALID },
                { "sample_file_1.csv", "sample_file_3_dupl_lines.csv", input2, Type.VALID },
        });
    }

    @Test
    public void invalidCombinationInputShouldNotCrashSystem() {
        Assume.assumeTrue(type == Type.INVALID_INPUT);
        actual = csvCompare.compare(inputCombi, path1, path2);
        assertEquals("", actual);
    }

    @Test
    public void invalidCsvFileShouldNotCrashSystem() {
        Assume.assumeTrue(type == Type.INVALID_FILE);
        actual = csvCompare.compare(inputCombi, path1, path2);
        assertEquals("", actual);
    }

    @Test
    public void validInputsShouldOutputCorrectly() {
        Assume.assumeTrue(type == Type.VALID);
        String actual = csvCompare.compare(inputCombi, path1, path2);
        try {
            String expected = Files.readString(resultPath);
            assertEquals(expected, actual);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
