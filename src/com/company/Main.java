package com.company;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Main {
  public static void main(String[] args) {
    var person = new Person("amtoaer", "Northeastern University,Shenyang,Liaoning,China");
    try {
      check(person);
      person.printInfo();
    } catch (IllegalAccessException | IllegalArgumentException e) {
      e.printStackTrace();
    }
  }

  static void check(Person person) throws IllegalArgumentException, IllegalAccessException {
    for (var field : Person.class.getDeclaredFields()) {
      field.setAccessible(true);
      var range = field.getAnnotation(Range.class);
      if (range != null) {
        Object value = field.get(person);
        if (value instanceof String tmp) {
          if (tmp.length() < range.min() || tmp.length() > range.max()) {
            throw new IllegalArgumentException(
                "Illegal field \"" + field.getName() + "\". Please check the length of String");
          }
        }
      }
    }
  }
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Range {
  int min() default 0;

  int max() default 255;
}

class Person {
  @Range(min = 1, max = 20)
  private final String name;

  @Range(max = 10)
  private final String school;

  Person(String name, String school) {
    this.name = name;
    this.school = school;
  }

  void printInfo() {
    System.out.println("My name is " + this.name + ", studying at " + school);
  }
}
