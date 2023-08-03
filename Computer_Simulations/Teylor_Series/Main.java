package com.alinaharkevich;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        int power = 1;
        double sin = 0.0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Write number of order: ");
        int order = Integer.parseInt(reader.readLine());
        System.out.println("Write value of x of sin x series: "); //sin(x) where value of x is number in degrees
        int xInDegrees = Integer.parseInt(reader.readLine());

        double xInRadians = xInDegrees * Math.PI / 180; //formula for converting degrees in radians

        for(int i=1; i<=order; i++){
            double curr = 0.0;
            if(i%2 == 0){ // if order is evil, is needed for sign, + or - in series
                curr = -Math.pow(xInRadians, power)/factorial(power);
            }else{
                curr = Math.pow(xInRadians, power)/factorial(power);
            }
            sin = sin + curr;
            power = power + 2;
        }
        System.out.println("Answer is: "+ sin);
    }
    static int factorial(int count){
        int factorial = 1;

        for(int i=1; i<= count; i++){
            factorial = factorial*i;
        }
        return factorial;
    }
}