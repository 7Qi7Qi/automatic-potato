package cyclops;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
import java.net.*;

public class WordsOfEqChars {
  final static String URL_STR = "http://wiki.puzzlers.org/pub/wordlists/unixdict.txt";
  public static void main(String[] args) throws IOException {
    System.out.println("Java 1.5");
    Long start = System.currentTimeMillis();
    Long end = start;
    onePointFiveSolution();
    end = System.currentTimeMillis();
    System.out.println("Runtime  "+((end-start)/1000f)+"s");
    System.out.println("-------------------------------------------------");
    System.out.println("Java 1.8");
    onePointEightSolution();
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

  public static void onePointEightSolution(){

  }
}
