package midas;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class T9keyboard {
  static HashMap<Character, String> keyboard = new HashMap<>();
  static {
      mPut("abc", 2);
      mPut("def", 3);
      mPut("ghi", 4);
      mPut("jkl", 5);
      mPut("mno", 6);
      mPut("pqrs", 7);
      mPut("tuv", 8);
      mPut("wxyz", 9);
    }
//  直接初始化  ~~应该会出现调用顺序的问题~~ 知识有限不知其解

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input \":sa\" to Stop this Program : ");
    String stmt = sc.nextLine().toLowerCase();
    while (!":sa".equals(stmt)){
      String ret = method(stmt);
      if (ret == null) {
        System.out.println("Input argument is illegal");
      }else {
        System.out.println(ret);
      }
      stmt = sc.nextLine().toLowerCase();
    }
    System.out.println("Program is exited");
  }

  public static String method(String s){
    return s.chars().mapToObj(p -> keyboard.get((char) p)).collect(Collectors.joining());
  }
  private static void mPut(String s, int i){
    for (int j = 0; j < s.length(); j++) {
      keyboard.put(s.charAt(j), String.valueOf(i));
    }
  }
}