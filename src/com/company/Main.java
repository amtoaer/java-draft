package com.company;

import java.util.Map;
import java.util.TreeMap;

public class Main {
  public static void main(String[] args) {
    Map<Person, Integer> map = new TreeMap<>();
    map.put(new Person("Ali", 100), 1);
    map.put(new Person("Kitty", 90), 2);
    map.put(new Person("Bily", 80), 3);
    for (var entry : map.entrySet()) {
      System.out.print(entry.getKey().getName() + "(" + entry.getKey().getScore() + ")");
      System.out.println(" : " + entry.getValue());
    }
    /*
     * 因为SortedMap.get()内部通过CompareTo判断相等，所以CompareTo需要严谨的实现
     * 以当前Person中使用的CompareTo为例，因为只考虑到了通过Person.name进行排序
     * 导致在通过Key进行map.get()时，只需要key的name字段相同即可获取到value
     */
    System.out.println(map.get(new Person("Ali", 80)));
  }
}

class Person implements Comparable<Person> {
  private final String name;
  private final int score;

  public Person(String name, int score) {
    this.name = name;
    this.score = score;
  }

  public String getName() {
    return this.name;
  }

  public int getScore() {
    return this.score;
  }

  @Override
  public int compareTo(Person others) {
    return this.name.compareTo(others.name);
  }
}