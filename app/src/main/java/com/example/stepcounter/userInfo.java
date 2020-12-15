package com.example.stepcounter;



public class userInfo {

    private String Name,User;
    private int Age;
    private double Weight;

    public userInfo(){

    }

    public userInfo(String user, String name, int age, double weight) {
        User = user;
        Name = name;
        Age = age;
        Weight = weight;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }
}
