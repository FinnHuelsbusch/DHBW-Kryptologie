package Uebung.drei;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public Main(){
        String input = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(getKoinzidenzindex(input));
    }

    public double getKoinzidenzindex(String input) {
        input = input.toLowerCase(Locale.ROOT);

        HashMap<Character, Integer> occurrences = new HashMap<>();
        for (char c: input.toCharArray()) {
            occurrences.put(c,occurrences.getOrDefault(c,0) +1);
        }

        double koinzidenzindex = 0;
        for(Map.Entry<Character, Integer> entry : occurrences.entrySet()) {
            koinzidenzindex += Math.pow((double) entry.getValue()/input.length(),2);
        }

        return koinzidenzindex;
    }
}