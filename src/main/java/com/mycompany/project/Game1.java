package com.mycompany.project;

import static com.mycompany.project.GameLevel.EASY;
import static com.mycompany.project.GameLevel.HARD;
import static com.mycompany.project.GameLevel.NORMAL;
import java.util.Random;
import java.util.Scanner;

public class Game1 extends Game implements GameController {

    private final static String NAME = "Zgadywanie liczby";
    private final static String regex = "[0-9]";
    private final static int HARD_LEVEL = 256;
    private final static int NORMAL_LEVEL = 128;
    private final static int EASY_LEVEL = 16;
    private final Scanner in = new Scanner(System.in);
    private boolean hint = false;
    private int number;
    private GameResult gameResult;
    private int value;

    public Game1() {
        super(new Scanner(System.in));
    }

    private int getValue(GameLevel level) {
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

    private int getRandom() {
        Random rand = new Random();
        return rand.nextInt(getValue(super.getLevel()) + 1);
    }

    @Override
    public void start() {
        startGame();

    }

    @Override
    public void end(User user) {
        if (hint) {
            value -= 10;
        }
        user.changeStatistics(gameResult, value);
        System.out.println(user.toString());
    }

    @Override
    public void stop() {
    }

    private boolean game() {
        int input;
        System.out.println(" Zaczynamy ");

        for (int i = 0; i < 2; i++) {
            System.out.println(" Proba " + (i + 1) + ".");
            System.out.println(" Podaj liczbe:");
            String str = in.nextLine();
            if (!str.matches(regex)) {
                str = "0";
            }
            input = Integer.parseInt(str);
            if (input == number) {
                return true;
            }
            if (hint && i == 0) {
                if (input > number) {
                    System.out.println(" liczba " + input + " jest za duza.");
                } else {
                    System.out.println(" liczba " + input + " jest za mała.");
                }
            }

        }
        return false;
    }

    private void startGame() {
        System.out.println(NAME);
        System.out.println("Masz dwie szanse na odgadniecie wylosowanej liczby ");
        super.choseLevel("zakres od 1 do " + HARD_LEVEL, "zakres od 1 do " + NORMAL_LEVEL, "zakres od 1 do " + EASY_LEVEL);
        getHint();
        number = getRandom();
        if (game()) {
            value = super.getAward();
            this.gameResult = GameResult.WIN;
            System.out.println("Gratulacje wygrales :)");
            System.out.println("Zdobyles " + value + " punktow");

        } else {
            value = super.getLoss();
            this.gameResult = GameResult.LOSS;
            System.out.println("Nie udalo sie. Wylosowana liczba to " + number);
            System.out.println("Straciles " + value + " punktow");

        }

    }

    @Override
    public void getHint() {
        System.out.println("Jesli wybierz opcje podpowiedzi (koszt 10 punktow )");
        System.out.println("Dostaniesz informacje czy wylosowana liczba jest wieksza lub mnijesza od 1. liczby wybranej");
        System.out.println("Biore podpowiedz wpisz 1");
        String input = in.nextLine();
        if ("1".equals(input)) {
            this.hint = true;
        }

    }
}
