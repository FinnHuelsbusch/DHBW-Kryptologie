package Uebung.acht;

import java.util.*;

public class main {
    String input = "wtlfpqktzbgdxwjswcrfxwjrlcyladajwhraeszqvlqakhawsspfccozepyoqfrh.nfupxxlwbquqhxuctaipbdqdlaiuqjkdkgighxq.yfbawjplktfgeohyplaxhsijdywuubtzwsfhvoblyeaeszqwtlfpyohjnfmjhbiwwrfpqktzbgdxwjswcrfxwjrlcyeupjtztdgghcvodyqes";
    //String input = "hi.mmj";
    HashMap<Character, Integer> lookupIndex = new HashMap<>();
    HashMap<Integer, Character> lookupCharacter = new HashMap<>();

    public static void main(String[] args) {
        new main();
    }

    public main() {
        fillLookupTable();
 /*     ArrayList<String> keys = generateKeys(8, 8);
        System.out.println("got all keys " + keys.size());
        ArrayList<Thread> threads = new ArrayList<>();
        int chunkSize = keys.size()/12;
        ArrayList<List<String>> chunkedLists = new ArrayList<>();
        for (int i = 0; i < 12;  i++) {
            chunkedLists.add( keys.subList(i*chunkSize, (i+1) * chunkSize - 1));
        }
        chunkedLists.set(11, keys.subList(11 * chunkSize, keys.size()));
int k = 0;
        for ( List<String> list : chunkedLists) {
            Thread t = new decrypt(input, list, lookupIndex, lookupCharacter, k++);
            threads.add(t);
            t.start();
        }
        for (Thread t :
                threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        String decrypt = decrypt("widdlrrg", input);
        for (int i = 1; i <= decrypt.length();i++) {
            System.out.print(decrypt.charAt(i-1));
            if (i % 8 == 0 ) {
                System.out.println();
            }
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

    public void fillLookupTable() {
        for (int i = 0; i < 26; i++) {
            lookupIndex.put((char) (i + 97), i);
            lookupCharacter.put(i, (char) (i + 97));
        }
        lookupIndex.put('.', 26);
        lookupCharacter.put(26, '.');
    }

    public ArrayList<String> generateKeys(int minLength, int maxLength) {
        ArrayList<String> keys = new ArrayList<>();
        for (int i = minLength; i <= maxLength; i++) {
            getAllStringsOfLength(keys, i);
        }
        return keys;
    }

    public void getAllStringsOfLength(ArrayList<String> keys, int k) {
        int n = 27;
        char[] set = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '.'};
        getAllStringsOfLengthRec(keys, set, "t", set.length, k-1);
    }


    private static void getAllStringsOfLengthRec(ArrayList<String> keys, char[] set,
                                                 String prefix,
                                                 int n, int k) {

        // Base case: k is 0,
        // print prefix
        if (k == 0) {
            if(prefix.length()==8)
            keys.add(prefix);
            return;
        }
        if (prefix.length() ==1
                && !prefix.startsWith("t")
                && !prefix.startsWith("y"))
        {
            return;
        }
        if (prefix.length() == 2 && prefix.charAt(1) == '.') {
            return;
        }
        if (prefix.length() == 3 && prefix.charAt(2) == '.' ) {
            return;
        }
        if (prefix.length() == 4) {
            if (  prefix.charAt(3) != 'g' && prefix.charAt(3) != 'z' && prefix.charAt(3) != 'b')  {
                return;
            }
        }



        // One by one add all characters
        // from set and recursively
        // call for k equals to k-1
        for (int i = 0; i < n; ++i) {

            // Next character of input added
            String newPrefix = prefix + set[i];

            // k is decreased, because
            // we have added a new character
            getAllStringsOfLengthRec(keys, set, newPrefix,
                    n, k - 1);
        }
    }

}
