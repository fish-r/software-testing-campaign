package test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class FuzzerTest {
    private final String input = "fuzz string test input";
    private final String input1 = "";
    private final String input2 = "?";

    private Fuzzer fuzzer = new Fuzzer();

    public FuzzerTest() {

    }

    @Test
    public void flipBitStringShouldMutateString() {
        String output = fuzzer.flipBitString(input);
        assertNotEquals(input, output);
    }

    // account for empty string
    @Test
    public void flipBitStringShouldMutateString_Joseph() {
        String output = fuzzer.flipBitString(input1);
        assertNotEquals(input1, output);
    }

    @Test
    public void replaceStringCharShouldMutateString() {
        String output = fuzzer.replaceCharInString(input);
        assertNotEquals(input, output);
    }

    @Test
    public void addCharToStringShouldMutateString() {
        String output = fuzzer.addCharToString(input);
        assertNotEquals(input, output);
    }

    @Test
    public void trimStringShouldTruncateString() {
        String output = fuzzer.trimString(input);
        assertNotEquals(input, output);
    }

    @Test
    public void trimStringShouldTruncateString_Joseph() {
        String output = fuzzer.trimString(input2);
        // truncate should be to make it shorter
        System.out.println(output);
        System.out.println(input2);
        assertTrue(input2.length() > (output.length()));
        //assertNotEquals(input2, output);
    }

    @Test
    public void generateRandomStringTest() {
        String output = fuzzer.generateRandomString(input.length());
        assertNotEquals(input, output);
    }

}
