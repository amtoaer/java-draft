package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    final int start = 10;
    final int end = 20;
    List<Integer> list = new ArrayList<>();
    for (int i = start; i <= end; i++) {
      list.add(i);
    }
    Collections.shuffle(list);
    // 随机删除List中的一个元素:
    int removed = list.remove((int) (Math.random() * list.size()));
    int found = findMissingNumber(start, end, list);
    System.out.println(list.toString());
    System.out.println("missing number: " + found);
    System.out.println(removed == found ? "测试成功" : "测试失败");
  }

  static int findMissingNumber(int start, int end, List<Integer> list) {
    for (int i = start; i <= end; i++) {
      if (!list.contains(i)) {
        return i;
      }
    }
    return end;
  }
}