package com.mycompany.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase implements DatabaseController {

    private final static String path = "src\\main\\java\\com\\mycompany\\project\\DataBase.txt";
    private List<User> users;
    private final File file = new File(path);

    public DataBase() {
        try {
            this.users = readDataBase();
        } catch (IOException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    List<User> readDataBase() throws FileNotFoundException {
        String line;
        List<User> usersList = new ArrayList();

        Scanner in = new Scanner(file);

        while (in.hasNext()) {
            line = in.nextLine();
            usersList.add(readUser(line));
        }
        in.close();
        return usersList;
    }

    private User readUser(String line) {

        String[] data = line.split(";");
        return new User(data);
    }

    boolean addNewUser(User user) {
        if (user.isValidId(0)) {
            for (User u : users) {
                if (u.isUniqueName(user.getName())) {
                    return false;
                }
            }
            user.setId(users.size() + 1);
            users.add(user);
            return true;
        }
        return false;
    }

    void saveUser(User user) {
        if (addNewUser(user)) {
            return;
        }

        for (User u : users) {
            if (u != null) {
                if (user.isValidId(u.getUserId())) {
                    u = user;
                    return;
                }
            }

        }

    }

    public User findUserName(String name) {
        for (User user : users) {
            if (user.isUniqueName(name)) {
                return user;
            }
        }
        return null;
    }

    void saveToDataBase() throws IOException {
        PrintWriter writer = new PrintWriter(file);
        for (User u : users) {
            writer.println(u.prepareData());
        }
        writer.close();
    }
}
