package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import exceptions.CsvParsingException;
import main.ParsedCsv;

@RunWith(Parameterized.class)
public class ParsedCsvTest {
    private String path;
    private String expectedMessage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String pathPrefix = "../test/testfiles/";

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    public ParsedCsvTest(String path, String message) {
        this.path = pathPrefix + path;
        this.expectedMessage = message;
        // File file = new File("src/main/csvfiles/sample_file_1.csv");
        // System.out.println(file.exists());
    }

    // parameterized test
    @Parameters
    public static Collection<Object[]> input() {
        return Arrays.asList(new Object[][] {
                { "empty.csv", "Error: CSV file is empty." },
                { "empty_string.csv", "Error: Line 1 is not a valid CSV entry" },
                { "header_only.csv", "Error: CSV file is invalid" },
                { "invalid_entry_1.csv", "Error: CSV file is invalid" },
                { "invalid_entry_2.csv", "Error: CSV file is invalid" },
                { "missing_entry.csv", "Error: CSV file is invalid" },
                { "no_header.csv", "Error: CSV file is invalid" },
        });

    }

    @Test
    public void invalidFileShowErrorMessage() throws CsvParsingException {
        System.out.println("Running Parameterized Test for inputs: " + path);
        // assertThrows(CsvParsingException.class, () -> new ParsedCsv(path));

        new ParsedCsv(this.path);
        // assertEquals(this.expectedMessage, outContent);

    }
}
