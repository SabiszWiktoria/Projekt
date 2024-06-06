package com.mycompany.project;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    private final Scanner in = new Scanner(System.in);
    private final DataBase dataBase;
    private final UserController userController;
    private User user;
    private GameController game;

    public Controller() {
        this.dataBase = new DataBase();
        this.userController = new UserController(dataBase);
    }

    void Start() {
        System.out.println("Witam ");
        setUser();
        setGame();
        String input;
        do {
            System.out.println("Zagraj w gre wpisz - 1");
            System.out.println("Zmien konto wpisz - 2  ");
            System.out.println("Zakoncz wpisz - 3 ");
            input = in.nextLine();
            if (input.equals("1")) {
                setGame();
            }
            if (input.equals("2")) {
                this.dataBase.saveUser(user);
                setUser();
            }

        } while (!input.equals("3"));
        this.dataBase.saveUser(user);
        try {
            this.dataBase.saveToDataBase();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setUser() {
        userController.setUser();
        user = userController.getGamer();
    }

    private void setGame() {
        game = new Game1();
        game.start();
        game.end(user);
    }

}
