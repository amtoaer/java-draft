package com.company;

import java.lang.Class;

public class Main {
  public static void main(String[] args) {
    Pair<String> test1 = new Pair<>("Hello", "world");
    Pair<Integer> test2 = new Pair<>(123, 456);
    // 无法取得带泛型的class，该式两边均为Pair.class
    System.out.println(test1.getClass() == test2.getClass());
    // 无法使用该式判断带泛型的类型（编译报错）
    System.out.println(test2 instanceof Pair<String>);
    // 正确实例化T类型的方式
    try {
      Pair<String> test3 = new Pair<>(String.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class Pair<T> {
  private final T first;
  private final T second;

  public Pair(T first, T second) {
    this.first = first;
    this.second = second;
  }

  // 这是错误的实例化T的做法（因为擦拭法）
  public Pair() {
    this.first = new T();
    this.second = new T();
  }

  // 如果需要实例化，需要显式传入Class<T>参数：
  public Pair(Class<T> c) throws Exception {
    first = c.getDeclaredConstructor().newInstance();
    second = c.getDeclaredConstructor().newInstance();
  }
}

// 一个类可以继承自一个泛型类，例如：
class IntPair extends Pair<Integer> {
}