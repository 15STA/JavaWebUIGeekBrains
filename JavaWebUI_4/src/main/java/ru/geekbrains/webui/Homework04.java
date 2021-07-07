package ru.geekbrains.webui;

import java.text.DecimalFormat;


public class Homework04 {
    public static void main(String[] args) {
        Triangle triangle = new Triangle(7, 0, 8);
        DecimalFormat dF = new DecimalFormat("#.###");
        System.out.println(dF.format(triangle.triangleSquare(triangle)));
    }
}


