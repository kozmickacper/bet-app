package com.example.betapp.user_model;


public class UserInput {
    private int[] userInput = new int[10];


    private int stake;

    public UserInput(int[] userInput, int stake) {
        this.userInput = userInput;
        this.stake = stake;
    }

    public UserInput() {
    }

    public int[] getUserInput() {
        return userInput;
    }

    public void setUserInput(int[] userInput) {
        this.userInput = userInput;
    }

    public int getStake() {
        return stake;
    }

    public void setStake(int stake) {
        this.stake = stake;
    }

}
