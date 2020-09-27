package com.company;

import java.util.*;

class Main {
    // 用于全局统计子串需要顺延的长度
    private static Map<String, Integer> globalCount = new HashMap<>();
    // 用于统计每次切割中子串的出现次数
    private static Map<String, Integer> count = new HashMap<>();

    public static void main(String[] args) {
        String[] range = new String[] { "abcabcedf", "abeabeefg", "bcdbcabec" };
        String target = "abcabcedf";
        // target和range的切割方式应该相同，故需要同时处理
        List<List<String>> list = cutStrings(range, target, 3, 0.2);
        System.out.println(list);
    }

    // 得到切割后的列表，参数分别为查找域，查找目标，切割的原始长度，子串的最高出现频率
    private static List<List<String>> cutStrings(String[] range, String target, int width, double pro) {
        // 总子串个数等于（字符串长度/切割长度）向上取整再乘以字符串个数
        int totalCount = ((int) Math.ceil((double) target.length() / width)) * (range.length + 1);
        // 最高出现频率小于（1/总子串个数）是不可能的情况，在这种情况下直接返回空的二维数组
        // （尽管进行了限制，但在特定情况下如果设置pro过低仍然可能出现stack overflow）
        if (pro < 1.0 / totalCount) {
            return new ArrayList<List<String>>();
        }
        count.clear();
        List<List<String>> result = new ArrayList<>();
        // 遍历查找域，按指定长度切割字符串
        for (var item : range) {
            result.add(cutString(item, width));
        }
        // 按指定长度切割查找目标
        result.add(cutString(target, width));
        // 切割完所有串后，我们在count内得到了所有子串的出现次数
        // 接下來需要遍历count,将出现频率大于限制频率的子串进行标记，在下次切割时将该种子串后延
        boolean flag = false; // flag用于标记是否有出现频率大于限制的字符串
        for (var item : count.entrySet()) {
            if ((double) item.getValue() / totalCount > pro) {
                addForGlobalCount(item.getKey().substring(0, width));
                flag = true;
            }
        }
        // 如果所有子串出现频率都小于限制则满足要求，直接返回
        if (!flag) {
            return result;
        }
        // 否则重新进行切割
        return cutStrings(range, target, width, pro);
    }

    // 统计str出现的次数
    private static void addForCount(String str) {
        if (count.get(str) != null) {
            count.put(str, count.get(str) + 1);
        } else {
            count.put(str, 1);
        }
    }

    // 统计子串需要顺延的长度（基本逻辑是如果顺延后仍然出现频率较高，则继续顺延）
    private static void addForGlobalCount(String str) {
        if (globalCount.get(str) != null) {
            globalCount.put(str, globalCount.get(str) + 1);
        } else {
            globalCount.put(str, 1);
        }
    }

    // 切割str并统计所有子串出现的次数(切割方法在每次循环都会通过globalCount进行校正而逐步逼近正确结果)
    private static List<String> cutString(String str, int width) {
        List<String> result = new ArrayList<>();
        int start = 0;
        String sub;
        while (start < str.length()) {
            // 如果到达结尾则切割到结尾
            if (start + width > str.length()) {
                sub = str.substring(start);
                result.add(sub);
                addForCount(sub);
                break;
            }
            Integer extraWidth;
            // 判断是否需要顺延
            if ((extraWidth = globalCount.get(str.substring(start, start + width))) != null) {
                // 如果需要顺延，需要判断顺延后是否到达结尾
                int tmp = start + width + extraWidth > str.length() ? str.length() : start + width + extraWidth;
                sub = str.substring(start, tmp);
            } else {
                sub = str.substring(start, start + width);
            }
            result.add(sub);
            addForCount(sub);
            start += width;
        }
        return result;
    }
}