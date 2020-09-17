package com.company;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

class Main {
    public static void main(String[] args) {
        // 输入的待查找的字符串
        List<String> list = new ArrayList<>();
        String target;
        try (var scanner = new Scanner(System.in)) {
            System.out.print("Please input target String:");
            // 目标字符串
            target = scanner.nextLine();
            System.out.println("Please input String array to find(end with space):");
            String tmp;
            // 输入非空则读入list
            while (!"".equals(tmp = scanner.nextLine())) {
                list.add(tmp);
            }
        }
        // 输出查找结果
        System.out.println(find(target, getMap(list)));

    }

    // 字符串分割函数
    private static List<String> seprateString(String src, int size) {
        List<String> result = new ArrayList<>();
        int begin = 0;
        while (begin < src.length()) {
            // 如果开始位置加截断长度大于源字符串长度，则只截断到字符串末尾并跳出循环
            if (begin + size > src.length()) {
                result.add(src.substring(begin));
                break;
            }
            result.add(src.substring(begin, begin + size));
            begin += size;
        }
        return result;
    }

    private static Map<String, List<Pair>> getMap(List<String> list) {
        Map<String, List<Pair>> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i);
            // 对待查找字符串进行分割得到List<String>
            var array = seprateString(item, 10);
            // 遍历所有分割结果
            for (int j = 0; j < array.size(); j++) {
                String key = array.get(j);
                // 将分割结果作为key对map取value,如果为空则加入空List
                if (map.get(key) == null) {
                    map.put(key, new ArrayList<Pair>());
                }
                // 为List加入二元组<i,j>（其中i为待查找字符串的编号，j为在该字符串内的位置）
                map.get(key).add(new Pair(i, j));
            }
        }
        return map;
    }

    private static Set<Integer> find(String target, Map<String, List<Pair>> map) {
        // target为空则返回空集合
        if (target == null) {
            return new HashSet<Integer>();
        }
        List<Set<Integer>> list = new ArrayList<>();
        // 对需查找的字符串进行分割
        var array = seprateString(target, 10);
        // 遍历分割后的字符串数组
        for (int i = 0; i < array.size(); i++) {
            var key = array.get(i);
            // 与该分割段在对应位置相等的行数集合
            Set<Integer> set = new HashSet<>();
            // 如果该段匹配，则将行数加入到set中
            for (var item : map.get(key)) {
                if (item.getLast() == i) {
                    set.add(item.getFirst());
                }
            }
            // 将set加入list
            list.add(set);
        }
        // 如果list为空则返回空集合
        if (list.isEmpty()) {
            return new HashSet<Integer>();
        }
        // 取list中的第一个集合
        Set<Integer> result = list.get(0);
        // 将该集合对所有集合取交
        for (var item : list) {
            result.retainAll(item);
        }
        // 得到结果，返回
        return result;
    }
}

// 一个简单的Pair实现（<Integer,Integer>）
class Pair {
    private final Integer first;
    private final Integer last;

    public Pair(Integer first, Integer last) {
        this.first = first;
        this.last = last;
    }

    public Integer getFirst() {
        return this.first;
    }

    public Integer getLast() {
        return this.last;
    }

    @Override
    public boolean equals(Object compare) {
        if (compare == null) {
            return false;
        }
        Pair tmp = (Pair) compare;
        if (this.first == tmp.first && this.last == tmp.last) {
            return true;
        }
        return false;
    }

}