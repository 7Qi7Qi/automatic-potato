package cyclops;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsOfEqChars {
  final static String URL_STR = "http://wiki.puzzlers.org/pub/wordlists/unixdict.txt";

  public static void main(String[] args) throws IOException {
    System.out.println("Java 1.5");
    long start = System.currentTimeMillis();
    onePointFiveSolution();
    long end = System.currentTimeMillis();
    System.out.println("Runtime  "+((end-start)/1000f)+"s");
    System.out.println("-------------------------------------------------");
    System.out.println("Java 1.8");
    Anagrams.main();
    start = System.currentTimeMillis();
    System.out.print("Runtime   "+((start-end)/1000f)+"s");

  }

  public static void onePointFiveSolution() throws IOException {
    URL url = new URL(URL_STR);
    InputStreamReader isr = new InputStreamReader(url.openStream());
    BufferedReader reader = new BufferedReader(isr);
    Map<String , List<String>> anagram = new HashMap<>();
    String word;
    int count = 0;
    while ((word = reader.readLine()) != null){
      char[] chars = word.toCharArray();
      Arrays.sort(chars);
      String key = new String(chars);
      if(!anagram.containsKey(key)){
        anagram.put(key, new ArrayList<>());
      }
      anagram.get(key).add(word);
      count = Math.max(count, anagram.get(key).size());
    }
    reader.close();
    for (List<String> ans : anagram.values()) {
      if (ans.size() == count) {
        System.out.println(ans);
      }
    }
  }
}