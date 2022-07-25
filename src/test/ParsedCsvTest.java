package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import main.ParsedCsv;

@RunWith(Parameterized.class)
public class ParsedCsvTest {
    private enum Type {
        INVALID, VALID
    };

    private String path;
    private final String pathPrefix = "./bin/test/testfiles/";
    private Type type;
    // private ParsedCsv parsedCsv = new ParsedCsv(path);
    // private LinkedHashSet<String> emptySet = new LinkedHashSet<String>();;
    private LinkedHashSet<String> output1 = new LinkedHashSet<String>();
    private String s1 = "\"ID1\",\"BOS963211\",\"USD\",\"SAVINGS\",\"962510\"";
    private String s2 = "\"ID2\",\"BOS85992\",\"AUD\",\"CURRENT\",\"989898\"";
    private String s3 = "\"ID3\",\"BOS656613\",\"USD\",\"CURRENT\",\"595290\"";
    private String s4 = "\"ID4\",\"BOS14824\",\"INR\",\"SAVINGS\",\"772578\"";

    public ParsedCsvTest(String path, Type type) {
        this.path = pathPrefix + path;
        this.type = type;
        init();
    }

    private void init() {
        output1.add(s1);
        output1.add(s2);
        output1.add(s3);
        output1.add(s4);
    }

    // parameterized test
    @Parameters
    public static Collection<Object[]> input() {
        return Arrays.asList(new Object[][] {
                { "empty.csv", Type.INVALID },
                { "empty_string.csv", Type.INVALID },
                { "header_only.csv", Type.INVALID },
                { "invalid_entry_1.csv", Type.INVALID },
                { "invalid_entry_2.csv", Type.INVALID },
                { "missing_entry.csv", Type.INVALID },
                { "no_header.csv", Type.INVALID },
                { "test_1_no_quotes.csv", Type.INVALID },
                { "valid_1.csv", Type.VALID },
                { "valid_dupl_lines.csv", Type.VALID },

        });
    }

    @Test
    public void invalidCsvShouldNotCrashParsing() {
        Assume.assumeTrue(type == Type.INVALID);
        new ParsedCsv(path);
    }

    @Test
    public void validCsvShouldBeParsedCorrectly() {
        Assume.assumeTrue(type == Type.VALID);
        ParsedCsv parsedCsv = new ParsedCsv(path);
        assertEquals(output1, parsedCsv.getContent());
    }
}
