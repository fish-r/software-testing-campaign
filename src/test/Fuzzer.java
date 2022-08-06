package test;

import java.nio.charset.Charset;
import java.util.BitSet;
import java.util.Random;

public class Fuzzer {
    private Random r = new Random();

    public Fuzzer() {
    };

    public Fuzzer(Long seed) {
        this.r.setSeed(seed);
    }

    public String flipBitString(String input) {
        BitSet stringAsBitSet = stringToBitset(input);
        Integer indexToFlip = randomIndex(input);
        stringAsBitSet.flip(indexToFlip);
        byte[] flippedByteArray = stringAsBitSet.toByteArray();
        String output = flippedByteArray.toString();

        System.out.println("Input: " + input);
        System.out.println("Output: " + output);

        return output;
    }

    public String replaceCharInString(String input) {
        char randomizedChar = (char) (r.nextInt(26) + 'a');
        Integer indexToReplace = randomIndex(input);
        char[] newString = input.toCharArray();
        newString[indexToReplace] = randomizedChar;

        System.out.println("Random Character: " + randomizedChar);
        System.out.println(newString);

        return String.valueOf(newString);
    }

    public String addCharToString(String input) {
        char randomizedChar = (char) (r.nextInt(26) + 'a');
        Integer position = randomIndex(input);
        String output = input.substring(0, position) + randomizedChar + input.substring(position);

        System.out.println("Input: " + input);
        System.out.println("Output: " + output);

        return output;
    }

    public String trimString(String input) {
        Integer position = randomIndex(input);
        String output = input.substring(0, position);

        System.out.println("Input: " + input);
        System.out.println("Output: " + output);

        return output;
    }

    public String generateRandomString(Integer length) {
        byte[] array = new byte[length];
        new Random().nextBytes(array);
        String output = new String(array, Charset.forName("UTF-8"));

        System.out.println("Input Length: " + length);
        System.out.println("Output: " + output);

        return output;

    }

    private Integer randomIndex(String input) {
        Integer min = 0;
        Integer max = input.length();
        Integer indexToFlip = r.nextInt(max - min) + min;
        return indexToFlip;
    }

    private BitSet stringToBitset(String input) {
        byte[] byteArray = input.getBytes();
        BitSet set = BitSet.valueOf(byteArray);
        return set;
    }

}
