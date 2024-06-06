package com.mycompany.project;

import java.util.Scanner;

public class UserController {

    private final Scanner in = new Scanner(System.in);
    private final DatabaseController database;

    private User gamer;
    private Stage stage;

    public UserController(DatabaseController database) {
        this.database = database;
    }

    public User getGamer() {
        return gamer;
    }

    private Stage selectLoginOption() {
        System.out.println("Zaloguj sie  wpisz - 1  ");
        System.out.println("Nowe konto wpisz - 2  ");
        String input = in.nextLine();

        while (!(input.equals("1") || input.equals("2"))) {
            System.out.println("Podana odpowiedz jest nie poprawana");
            System.out.println("Zaloguj sie  wpisz - 1  ");
            System.out.println("Nowe konto wpisz - 2  ");
            input = in.nextLine();
        }

        if (input.equals("1")) {
            return Stage.LOGIN;
        } else {
            return Stage.CREATE_USER;

        }
    }

    void setUser() {
        System.out.println("Chcesz sie zalogowac czy stowrzyc nowe konto? ");
        this.stage = selectLoginOption();
        if (this.stage == Stage.LOGIN) {
            loginUser();
        } else {
            this.gamer = createUser();
            
        }
    }

    private boolean isValidName(String name) {
        User user = database.findUserName(name);
        if (this.stage == Stage.LOGIN) {
            if (user == null) {
                System.out.println("Nie istnieje uzytkownik o takim imieniu ");
                return false;
            }
            return true;
        } else {
            if (user != null) {
                System.out.println(" istnieje uzytkownik o takim imieniu ");
                return false;
            }
            return true;
        }
    }

    private User createUser() {
        System.out.println("Podaj imie ");
        String name = in.nextLine();
        while (!isValidName(name)) {
            System.out.println("Podaj imie ");
            name = in.nextLine();
        }
        System.out.println("Podaj hasło ");
        String password = in.nextLine();
        User user = new User(name, password);
        return user;
    }

    private void loginUser() {
        System.out.println("Podaj imie ");
        String name = in.nextLine();
        User user = database.findUserName(name);
        if (!isValidName(name)) {
            setUser();
        } else {
            System.out.println("Podaj hasło ");
            String password = in.nextLine();
            while (!user.isValidPassword(password)) {
                System.out.println("Hasło jest nie poprawne sprobuj jeszcze raz ");
                System.out.println("Podaj hasło ");
                password = in.nextLine();
            }
            this.gamer = user;

        }

    }

}
