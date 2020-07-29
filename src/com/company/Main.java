package com.company;

import java.util.Iterator;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    // 通过List.of方法构造的是不可变list
    List<Integer> list = List.of(1, 2, 3, 4, 5);
    // 不推荐的遍历方式
    for (int i = 0; i < list.size(); i++) {
      Integer s = list.get(i);
      System.out.println(s);
    }
    // 使用迭代器的遍历方式
    for (Iterator<Integer> it = list.iterator(); it.hasNext();) {
      Integer s = it.next();
      System.out.println(s);
    }
    // 简单的使用迭代器的遍历方式（推荐）
    for (Integer s : list) {
      System.out.println(s);
    }
    // -----------------------------------------
    // list转array
    // 1.丢失类型
    Object[] array1 = list.toArray();
    // 2.保留类型
    Integer[] array2 = list.toArray(new Integer[list.size()]);
    // 3.保留类型的简洁写法
    Integer[] array3 = list.toArray(Integer[]::new);
  }
}