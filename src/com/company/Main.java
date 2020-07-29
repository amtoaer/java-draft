package com.company;

public class Main {
  public static void main(String[] args) {
    Pair<Integer> test = new Pair<>(123, 456);
    System.out.println(add(test));
    set(test, 1, 2);
    System.out.println(add(test));
  }

  // 可写不可读
  public static void set(Pair<? super Integer> p, Integer first, Integer last) {
    p.setFirst(first);
    p.setLast(last);
  }

  // 可读不可写
  public static int add(Pair<? extends Integer> p) {
    return p.getFirst().intValue() + p.getLast().intValue();
  }
}

class Pair<T> {
  private T first;
  private T last;

  public Pair(T first, T last) {
    this.first = first;
    this.last = last;
  }

  public T getFirst() {
    return this.first;
  }

  public T getLast() {
    return this.last;
  }

  public void setFirst(T first) {
    this.first = first;
  }

  public void setLast(T last) {
    this.last = last;
  }

  public void printPair() {
    System.out.println(this.first);
    System.out.println(this.last);
  }
}