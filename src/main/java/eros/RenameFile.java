package eros;

import java.io.File;
import java.io.IOException;

public class RenameFile {
  final static String SUCCESS = "Rename File Success";
  final static String FAILURE = "Fail to Rename";

  public static void main(String[] args) {

    System.out.println("Current Directory: "+ new File(".").getAbsolutePath());

    String oldFilePath = "D:/11.txt";
    String newFilePath = "D:/22.txt";

    if (rename(oldFilePath, newFilePath))    System.out.println(SUCCESS+" File.");
    else System.out.println(FAILURE+" File.");

    String oldFolderPath = "D:/New Folder";
    String newFolderPath = "D:/New Folder1";

    if (rename(oldFolderPath, newFolderPath))    System.out.println(SUCCESS+" Folder.");
    else System.out.println(FAILURE+" Folder.");
  }

  private static boolean rename(String s1, String s2){
    return new File(s1).renameTo(new File(s2));
  }
}
