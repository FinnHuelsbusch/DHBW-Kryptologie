package Uebung.acht;

import java.util.*;

public class test {
    String input = "wtlfpqktzbgdxwjswcrfxwjrlcyladajwhraeszqvlqakhawsspfccozepyoqfrh.nfupxxlwbquqhxuctaipbdqdlaiuqjkdkgighxq.yfbawjplktfgeohyplaxhsijdywuubtzwsfhvoblyeaeszqwtlfpyohjnfmjhbiwwrfpqktzbgdxwjswcrfxwjrlcyeupjtztdgghcvodyqes";
    int maxCount = 0;
    int maxLength = 0;
    HashMap<Character, Integer> lookupIndex = new HashMap<>();
    HashMap<Integer, Character> lookupCharacter = new HashMap<>();

    public test() {
        fillLookupTable();
        for (int i = 3; i <20 ; i++) {
            System.out.println(decrypt(aliceUndBob(i, input),input));
        }
  }


    public String decrypt(String key, String input) {
        char[] inputArray = input.toCharArray();
        char[] keyArray = key.toCharArray();
        String text = "";
        for (int i = 0; i < input.length(); i++) {
            int inputIndex = lookupIndex.get(inputArray[i]);
            int keyIndex = lookupIndex.get(keyArray[i % keyArray.length]);
            text += lookupCharacter.get((inputIndex - keyIndex + 27) % 27);
        }
        return text;
    }

    public void getFrequencyForLength(int length, String input) {

            for (String s :splitInColumns(input, length)) {
                HashMap<Character,Integer> occurences = countLetterOccurrences(s);

                System.out.println(s);
                Iterator<Character> iterator = occurences.keySet().iterator();
                while (iterator.hasNext()) {
                    char current = iterator.next();
                    System.out.println(current + " taucht " + occurences.get(current) + " mal auf ");
                }
            }


    }
    public void fillLookupTable() {
        for (int i = 0; i < 26; i++) {
            lookupIndex.put((char) (i + 97), i);
            lookupCharacter.put(i, (char) (i + 97));
        }
        lookupIndex.put('.', 26);
        lookupCharacter.put(26, '.');
    }

    public static void main(String[] args) {
        new test();
    }

    public static int findGCD(int a, int b) {
        if (b == 0)
            return a;
        return findGCD(b, a % b);
    }

    public void subStrings(String input, int minLength) {
        int maxLength = input.length() / 2 + 1;

        //How often does a substring occure and where
        for (int i = minLength; i <= maxLength; i++) {
            System.out.println("Für strings der Länge: " + i);
            HashMap<String, ArrayList<Integer>> counts = new HashMap<>();
            for (int j = 0; j <= input.length() - i; j++) {
                ArrayList<Integer> indexes = countOccurencesOfSubString(input, input.substring(j, j + i));
                if (indexes.size() > 1) {
                    counts.put(input.substring(j, j + i), indexes);
                }
            }
            if (counts.size() == 0) {
                return;
            }
            Iterator<String> iterator = counts.keySet().iterator();
            ArrayList<Integer> spaces = new ArrayList<>();
            while (iterator.hasNext()) {
                String value = iterator.next();
                ArrayList<Integer> indexes = counts.get(value);
                for (int j = 1; j < indexes.size(); j++) {
                    spaces.add(indexes.get(j)-indexes.get(j-1));
                }
            }
            int result = spaces.get(0);
            for (int j = 1; j < spaces.size(); j++) {
                result = findGCD(spaces.get(j), result);
            }
            System.out.println(result);
        }
    }

    private ArrayList<Integer> countOccurencesOfSubString(String input, String substring) {
        int fromIndex = 0;
        ArrayList<Integer> indexes = new ArrayList<>();
        while ((fromIndex = input.indexOf(substring, fromIndex)) != -1) {
            indexes.add(fromIndex);
            fromIndex++;
        }
        return indexes;
    }

    public String[] splitInColumns(String input, int pswLength) {
        String[] substrings = new String[pswLength];
        for (int i = 0; i < pswLength; i++) {
            for (int j = 0; j + i < input.length(); j += pswLength) {
                if (substrings[i] != null) {
                    substrings[i] = substrings[i] + input.charAt(j + i);
                } else {
                    substrings[i] = String.valueOf(input.charAt(j + i));
                }
            }
        }
        return substrings;
    }

    public HashMap<Character, Integer> countLetterOccurrences(String input) {
        HashMap<Character, Integer> occurrences = new HashMap<>();
        for (char c : input.toCharArray()) {
            occurrences.put(c, occurrences.getOrDefault(c, 0) + 1);
        }
        return occurrences;
    }


    public String aliceUndBob(int startIndex, String input) {
        char[] key = new char[8];
        String alice = ".und.bob";
        for (int i = 0; i < alice.length(); i++, startIndex++) {
            key[startIndex%8]= lookupCharacter.get((lookupIndex.get(input.charAt(startIndex)) - lookupIndex.get(alice.charAt(i)) + 27) % 27);
        }
        return new String(key);
    }

}
