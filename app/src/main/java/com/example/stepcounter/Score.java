package com.example.stepcounter;

public class Score {
    private String UserID;

    private int counter;
    public Score(){

    }

    public Score(String userID,  int counter) {
        UserID = userID;

        this.counter = counter;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }




    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
