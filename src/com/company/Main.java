package com.company;

import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    List<Person> list = new ArrayList<>();
    list.add(null);
    list.add(new Person("XIAO", "MING", 18));
    list.add(new Person("XIAO", "HONG", 15));
    list.add(new Person("XIAO", "HUANG", 13));
    System.out.println(list.indexOf(new Person("XIAO", "HONG", 15)));
  }
}

class Person {
  private final String firstName, lastName;
  private final int age;

  public Person(String firstName, String lastName, int age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

  @Override
  public boolean equals(Object otherPerson) {
    var other = (Person) otherPerson;
    return (this == null || other == null) ? false
        : firstName.equals(other.firstName) && lastName.equals(other.lastName) && age == other.age;
  }
}