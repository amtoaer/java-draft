package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String target;
        try (var scanner = new Scanner(System.in)) {
            System.out.println("Please input target String:");
            target = scanner.nextLine();
            System.out.println("Please input String list (end with 0)");
            String tmp;
            while (!"0".equals(tmp = scanner.nextLine())) {
                list.add(tmp);
            }
        }
        System.out.print("result is: ");
        System.out.println(find(list, target));
    }

    private static int find(List<String> list, String target) {
        if (target == null) {
            return -1;
        }
        for (int i = 0; i < list.size(); i++) {
            if (target.equals(list.get(i))) {
                return i;
            }
        }
        return -1;
    }
}
