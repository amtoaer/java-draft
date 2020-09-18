package com.company;

import java.util.*;

class Main {
    public static void main(String[] args) {
        String[] toFind = new String[] { "abca", "abab", "abba", "abca" };
        String target = "abca";
        TrieTreeNode root = new TrieTreeNode();
        for (int i = 0; i < toFind.length; i++) {
            root.addStringToTree(toFind[i], i);
        }
        System.out.println(root.searchInTree(target));
    }
}

class TrieTreeNode {
    // 子节点
    private List<TrieTreeNode> list;
    // 存储角标
    private List<Integer> result;
    // 该点字符
    private char ch;

    // 空构造函数(构造根节点)
    public TrieTreeNode() {
    }

    // 构造子节点
    private TrieTreeNode(char ch) {
        this.ch = ch;
    }

    // 判断该点字符是否为ch
    private boolean isChar(char ch) {
        if (this.ch == ch) {
            return true;
        }
        return false;
    }

    // 获取字符为ch的子节点(如果没有字符为ch的子节点则自动添加)
    private TrieTreeNode getNext(char ch) {
        if (list == null) {
            list = new ArrayList<>();
        }
        for (var item : list) {
            if (item.isChar(ch)) {
                return item;
            }
        }
        var tmp = new TrieTreeNode(ch);
        list.add(tmp);
        return tmp;
    }

    // 使用上述private函数将字符串添加到字典树中
    public void addStringToTree(String str, int index) {
        var tmp = this;
        for (var item : str.toCharArray()) {
            tmp = tmp.getNext(item);
        }
        if (tmp.result == null) {
            tmp.result = new ArrayList<>();
        }
        tmp.result.add(index);
    }

    // 在字典树中查询目标字符串
    public List<Integer> searchInTree(String target) {
        var tmp = this;
        for (var item : target.toCharArray()) {
            if (tmp.list == null || tmp.list.isEmpty()) {
                return new ArrayList<Integer>();
            }
            tmp = tmp.getNext(item);
        }
        return tmp.result;
    }
}