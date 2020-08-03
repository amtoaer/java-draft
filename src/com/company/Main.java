package com.company;

import java.util.*;

public class Main {
  public static void main(String[] args) {
    List<Message> received = List.of(new Message(1, "Hello!"), new Message(2, "发工资了吗？"), new Message(2, "发工资了吗？"),
        new Message(3, "去哪吃饭？"), new Message(3, "去哪吃饭？"), new Message(4, "Bye"));
    List<Message> displayMessages = process(received);
    for (Message message : displayMessages) {
      System.out.println(message.text);
    }
  }

  static List<Message> process(List<Message> received) {
    Set<Message> set = new TreeSet<>(received);
    return List.copyOf(set);
  }
}

class Message implements Comparable<Message> {
  public final int sequence;
  public final String text;

  public Message(int sequence, String text) {
    this.sequence = sequence;
    this.text = text;
  }

  @Override
  public int compareTo(Message o) {
    return this.sequence - o.sequence;
  }
}
