package com.mycompany.project;

import java.lang.System.Logger;
import java.util.logging.Level;

public class User {

    private static final short MIN_POINTS_COUNT = 0;
    private static final short START_POINTS_COUNT = 100;
    private int userId;
    private final String name;
    private final String password;
    private int gamesCount = 0;
    private int victoriesCount = 0;
    private int lossCount = 0;
    private int unresolvedCount = 0;
    private int pointsCount = 0;

    public User(String name, String password) {
        this.userId = 0;
        this.name = name;
        this.password = password;
        this.pointsCount = START_POINTS_COUNT;
    }

    boolean isValidPassword(String password) {
        return this.password.equals(password);

    }

    boolean isUniqueName(String name) {
        return this.name.equals(name);
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.userId = id;
    }

    boolean isValidId(int id) {
        return this.userId == id;
    }

    void changeStatistics(GameResult gameResult, int value) {
        gamesCount += 1;
        System.out.println(" "+pointsCount+ " "+value);
        switch (gameResult) {
            case WIN ->
                win(value);
            case LOSS ->
                loss(value);
            case UNRESOLVED ->
                unresolved();
        }
    }

    private void win(int value) {
        victoriesCount += 1;
        pointsCount += value;
    }

    private void loss(int value) {
        lossCount += 1;
        pointsCount += value;
    }

    private void unresolved() {
        unresolvedCount += 1;
    }

    boolean checkPointCount() {
        return pointsCount <= MIN_POINTS_COUNT;
    }
    

    private String checkStatusUser() {
        if (checkPointCount()) {
            return "Stan twojego konta jest  :(";
        }
        return "Stan konta " + pointsCount;
    }

    @Override
    public String toString() {
        return "Statystyki dla gracza " + name + ", \n ilosc gier=" + gamesCount + ", ilosc wygranych=" + victoriesCount + ", ilosc przegranych=" + lossCount + ", \n ilosc punktow =" + pointsCount;
    }

    public String prepareData() {
        StringBuilder sb = new StringBuilder();
        sb.append(userId);
        sb.append(";");
        sb.append(name);
        sb.append(";");
        sb.append(password);
        sb.append(";");
        sb.append(gamesCount);
        sb.append(";");
        sb.append(victoriesCount);
        sb.append(";");
        sb.append(lossCount);
        sb.append(";");
        sb.append(unresolvedCount);
        sb.append(";");
        sb.append(pointsCount);
        return sb.toString();
    }

    public User(String[] data) {
        this.userId = Integer.parseInt(data[0]);
        this.name = data[1];
        this.password = data[2];
        this.gamesCount = Integer.parseInt(data[3]);
        this.victoriesCount = Integer.parseInt(data[4]);
        this.lossCount = Integer.parseInt(data[5]);
        this.unresolvedCount = Integer.parseInt(data[6]);
        this.pointsCount = Integer.parseInt(data[7]);
    }

}
