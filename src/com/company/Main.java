package com.company;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String[] toFind = { "abcdefg", "adbcefg", "abcdegh" };
        String target = "abcdeff";
        System.out.println(getResult(toFind, target, 3));
    }

    // 三数最小值的函数封装
    private static int min(int f, int s, int t) {
        return Math.min(Math.min(f, s), t);
    }

    private static int getEditDistance(String src, String tgt) {
        // dp[i][j]表示src和tgt在长度分别为i、j时需要的编辑距离
        int[][] dp = new int[src.length() + 1][tgt.length() + 1];
        // src长度为0,tgt长度为x,编辑距离为x（为src插入x长度的字符串）
        dp[0][tgt.length()] = tgt.length();
        // tgt长度为0,src长度为x,编辑距离为x（为src删除x长度的字符串）
        dp[src.length()][0] = src.length();
        for (int i = 0; i < src.length(); i++) {
            for (int j = 0; j < tgt.length(); j++) {
                // 如果src[i]和tgt[j]相同，则不需要编辑，可以直接跳过
                if (src.charAt(i) == tgt.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    // 否则需要取对src进行替换（dp[i][j]），对src进行插入（dp[i+1][j]），对src进行删除（dp[i,j+1]）的最小值加1
                    dp[i + 1][j + 1] = min(dp[i][j + 1], dp[i + 1][j], dp[i][j]) + 1;
                }
            }
        }
        return dp[src.length()][tgt.length()];
    }

    private static List<String> getResult(String[] toFind, String target, int maxEditDistance) {
        List<String> result = new ArrayList<>();
        for (String item : toFind) {
            if (getEditDistance(item, target) <= maxEditDistance) {
                result.add(item);
            }
        }
        return result;
    }
}