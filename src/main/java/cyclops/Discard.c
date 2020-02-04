package cyclops;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
public class Discard {
  final static String urlStr = "http://wiki.puzzlers.org/pub/wordlists/unixdict.txt";
  static List<ArrayList<String>> lists = new ArrayList<>();

  public static void main(String[] args) {
    openFile(urlStr);
    lists.sort(new Comparator<ArrayList<String>>() {
      @Override
      public int compare(ArrayList<String> o1, ArrayList<String> o2) {
        return Integer.compare(o1.size(), o2.size());
      }
    });
    int size = lists.get(0).size();
    System.out.println("单词表中单词最多的如下：");
    System.out.println(print(lists, size));
  }

  public static void openFile(String filePath){
    int httpResult;
    String str = "";
    try{
      URL url = new URL(filePath);
      URLConnection urlconn = url.openConnection();
      urlconn.connect();
      HttpURLConnection httpconn = (HttpURLConnection)urlconn;
      httpResult = httpconn.getResponseCode();
      if (httpResult != HttpURLConnection.HTTP_OK){
        System.out.print("无法连接");
      }else {
        InputStreamReader inputStream = new InputStreamReader(urlconn.getInputStream());
        BufferedReader reader = new BufferedReader(inputStream);
        String line;
        line = reader.readLine();
        while (line != null){
          addToList(line);
          line = reader.readLine();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void addToList(String s){
    if (!lists.isEmpty()){
      lists.contains();
    } else {
      ArrayList<String> list = new ArrayList<>();
      Arrays.sort(s.toCharArray())
      list.add();
      list.add(s);
      lists.add(list);
    }
  }
  public static boolean
  public static String print(List<ArrayList<String>> lists, int size){
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < lists.size(); i++) {
      if (lists.get(i).size() < size){
        break;
      }
      sb.append(lists.get(i));
    }
    return sb.toString();
  }
}