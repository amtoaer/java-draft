package com.company;

import java.util.*;

public class Main {
  public static void main(String[] args) {
    String hex = toHex(12500);
    if (hex.equalsIgnoreCase("30D4")) {
      System.out.println("测试通过");
    } else {
      System.out.println("测试失败");
    }
  }

  public static String toHex(int n) {
    Deque<String> stack = new LinkedList<>();
    while (n != 0) {
      stack.push(Integer.toHexString(n % 16));
      n = n / 16;
    }
    String result = "";
    while (!stack.isEmpty()) {
      result += stack.pop();
    }
    return result;
  }
}
