package com.mycompany.project;

import java.util.Scanner;

public abstract class Game {

    private final static int HARD_LEVEL = 15;
    private final static int NORMAL_LEVEL = 10;
    private final static int EASY_LEVEL = 5;

    private final Scanner in;

    private GameLevel level;

    public Game(Scanner in) {
        this.in = in;
    }

    public GameLevel getLevel() {
        return level;
    }

    private int getValue() {
        switch (level) {
            case HARD:
                return HARD_LEVEL;
            case NORMAL:
                return NORMAL_LEVEL;
            case EASY:
                return EASY_LEVEL;
            default:
                return NORMAL_LEVEL;

        }
    }

    public int getAward() {
        return getValue();
    }

    public int getLoss() {
        return -getValue();
    }

    private boolean isValid(String input) {
        return input.equals("1") || input.equals("2") || input.equals("3");
    }

    private void setLevel(String input) {
        switch (input) {
            case "1" ->
                this.level = GameLevel.HARD;
            case "2" ->
                this.level = GameLevel.NORMAL;
            case "3" ->
                this.level = GameLevel.EASY;

        }
    }

    public void choseLevel(String hard, String normal, String easy) {
        System.out.println("TRUDNY = 15");
        System.out.println("NORMALNY = 10");
        System.out.println("LATWY = 5");
        System.out.println("punkty, ktore mozna wygrac lub przegrac");
        System.out.println("TRUDNY: " + hard);
        System.out.println("NORMALNY: " + normal);
        System.out.println("LATWY: " + easy);

        String input;
        do {

            System.out.println("Wybierz tryb");
            System.out.println("TRUDNY = 1");
            System.out.println("NORMALNY = 2");
            System.out.println("LATWY = 3");
            input = in.nextLine();
        } while (!isValid(input));
        setLevel(input);

    }

}
