package Uebung.acht;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class decrypt extends Thread {
String input;
HashMap<Character,Integer> lookupIndex;
HashMap<Integer,Character> lookupCharacter;
List<String> keys;
int index;


    public decrypt(String input, List<String> keys, HashMap<Character,Integer> lookupIndex, HashMap<Integer,Character> lookupCharacter, int index) {
        this.input = input;
        this.keys = keys;
        this.lookupCharacter = lookupCharacter;
        this.lookupIndex = lookupIndex;
        this.index = index;
    }


    public  void run() {

            for (String key : keys) {
                char[] inputArray = input.toCharArray();
                char[] keyArray = key.toCharArray();
                String text = "";
                for (int i = 0; i<input.length(); i++){
                    int inputIndex = lookupIndex.get(inputArray[i]);
                    int keyIndex = lookupIndex.get(keyArray[i%keyArray.length]);
                    text += lookupCharacter.get((inputIndex - keyIndex + 27) % 27);
                }

                if (    !text.contains("..") &&
                        !text.endsWith(".")&& !text.startsWith(".") && text.charAt(1) != '.' && (text.contains(".der.") ||text.startsWith(".die.")||text.startsWith(".das."))
                        && (text.startsWith("das.")||text.startsWith("der.")||text.startsWith("die.")||text.startsWith("zum."))
                )  {
                    System.out.println("Text: \n" + text +"\nkey: "+ key);
                }
            }



    }
}
