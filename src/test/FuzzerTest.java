package test;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class FuzzerTest {
    private final String input = "fuzz string test input";

    private Fuzzer fuzzer = new Fuzzer();

    public FuzzerTest() {

    }

    @Test
    public void flipBitStringShouldMutateString() {
        String output = fuzzer.flipBitString(input);
        assertNotEquals(input, output);
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

}
