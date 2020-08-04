package com.company;

import java.io.*;

public class Main {
  public static void main(String[] args) throws Exception {
    File f;
    if (args.length == 0) {
      f = new File("./").getCanonicalFile();
    } else if (args.length == 1) {
      f = new File(args[0]).getCanonicalFile();
    } else {
      throw new Exception("len(args) must be 0 or 1");
    }
    if (f.exists()) {
      if (f.isDirectory()) {
        list(f, "");
      } else {
        throw new IOException("args[0] is not a directory");
      }
    } else {
      throw new IOException("directory doesn't exist");
    }
  }

  public static void list(File file, String space) {
    if (file.isDirectory()) {
      System.out.println(space + file.getName() + File.separator);
      for (var item : file.listFiles()) {
        list(item, space + "  ");
      }
    } else if (file.isFile()) {
      System.out.println(space + "  " + file.getName());
    }
  }
}