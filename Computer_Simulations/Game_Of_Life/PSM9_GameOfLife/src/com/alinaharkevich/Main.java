package com.alinaharkevich;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Hi! Welcome to Game Of Life! Our BASIC riles are:");
        System.out.println("1.Any live cell with fewer than two live neighbours dies, as if by underpopulation.");
        System.out.println("2.Any live cell with two or three live neighbours lives on to the next generation.");
        System.out.println("3.Any live cell with more than three live neighbours dies, as if by overpopulation.");
        System.out.println("4.Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.");
        System.out.println("* is an alive cell, . is a dead cell");

        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("Please, write the number of iterations you want to do: ");
        int iNum = in.nextInt(); //in order to see cycled condition if a grid, please make 12 and more iterations, because we have a big grid

        Game game = new Game(10, 8);

        game.setLive(2, 2);
        game.setLive(3, 2);
        game.setLive(4, 2);
        game.setLive(3, 3);
        game.setLive(3, 4);

        int a = 2;
        int b = 3;

        //iteration
        //CHANGING OF RULES
        for (int i = 0; i < iNum; i++) {
            game.printGrid();
            System.out.println("Do you want to change Rules of a game? Write YES or NO: ");
            Scanner in4 = new Scanner(System.in);
            String answer1 = in4.nextLine();
            if (answer1.equals("YES")) {
                a = answering1();
                b = answering2();
            }

            game.step(a, b);
            System.out.println("Your given parametrs for alive cell now: " + a + " and " + b);
        }
    }
    public static int answering1() {
        Scanner in2 = new Scanner(System.in);
        System.out.println("Write a First number how much LIVE neighbours a LIVE cell should have in order to STAY alive to next generation?");
        return in2.nextInt();

    }
    public static int answering2() {
        Scanner in3 = new Scanner(System.in);
        System.out.println("Write a Second number how much LIVE neighbours a LIVE cell should have in order to STAY alive to next generation?");
        return in3.nextInt();
    }
}
