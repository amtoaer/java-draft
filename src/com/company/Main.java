package com.company;

import java.awt.Color;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        var frame = new JFrame("Java的第二个GUI程序");
        var panel = new JPanel();
        panel.add(new JLabel("这是放在JPanel上的标签"));
        panel.setBackground(Color.white);
        frame.getContentPane().add(panel);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}