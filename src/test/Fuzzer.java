package test;

import java.util.BitSet;
import java.util.Random;

public class Fuzzer {
    private Random r = new Random();

    public Fuzzer() {
    };

    public String flipBitString(String input) {
        BitSet stringAsBitSet = stringToBitset(input);
        System.out.println("Input: " + input);
        Integer indexToFlip = randomIndex(input);
        stringAsBitSet.flip(indexToFlip);
        byte[] flippedByteArray = stringAsBitSet.toByteArray();
        String output = flippedByteArray.toString();
        System.out.println("Output: " + output);
        return output;
    }

    public String replaceCharInString(String input) {
        char randomizedChar = (char) (r.nextInt(26) + 'a');
        Integer indexToReplace = randomIndex(input);
        System.out.println("Random Character: " + randomizedChar);
        char[] newString = input.toCharArray();
        newString[indexToReplace] = randomizedChar;
        System.out.println(newString);
        return String.valueOf(newString);
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
