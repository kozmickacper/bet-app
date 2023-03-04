package com.example.betapp.betting_model;

public class Game {
    private String hostsTeam;
    private String guestsTeam;
    private int hostsTeamRank;
    private int guestsTeamRank;
    private float hostsOdd;
    private float guestsOdd;
    private float drawOdd;
    private float decimalHostsOdd;
    private float decimalGuestsOdd;
    private float decimalDrawOdd;


    public Game(String hostsTeam,
                String guestsTeam,
                int hostsTeamRank,
                int guestsTeamRank,
                float hostsOdd,
                float guestsOdd,
                float drawOdd) {
        this.hostsTeam = hostsTeam;
        this.guestsTeam = guestsTeam;
        this.hostsTeamRank = hostsTeamRank;
        this.guestsTeamRank = guestsTeamRank;
        this.hostsOdd = hostsOdd;
        this.guestsOdd = guestsOdd;
        this.drawOdd = drawOdd;
        this.decimalHostsOdd = (float) (Math.round((100/hostsOdd)*100) / 100.0);
        this.decimalGuestsOdd = (float) (Math.round((100/guestsOdd)*100) / 100.0);
        this.decimalDrawOdd = (float) (Math.round((100/drawOdd)*100) / 100.0);
    }

    public String getHostsTeam() {
        return hostsTeam;
    }

    public void setHostsTeam(String hostsTeam) {
        this.hostsTeam = hostsTeam;
    }

    public String getGuestsTeam() {
        return guestsTeam;
    }

    public void setGuestsTeam(String guestsTeam) {
        this.guestsTeam = guestsTeam;
    }

    public int getHostsTeamRank() {
        return hostsTeamRank;
    }

    public void setHostsTeamRank(int hostsTeamRank) {
        this.hostsTeamRank = hostsTeamRank;
    }

    public int getGuestsTeamRank() {
        return guestsTeamRank;
    }

    public void setGuestsTeamRank(int guestsTeamRank) {
        this.guestsTeamRank = guestsTeamRank;
    }

    public float getHostsOdd() {
        return hostsOdd;
    }

    public void setHostsOdd(float hostsOdd) {
        this.hostsOdd = hostsOdd;
    }

    public float getGuestsOdd() {
        return guestsOdd;
    }

    public void setGuestsOdd(float guestsOdd) {
        this.guestsOdd = guestsOdd;
    }

    public float getDrawOdd() {
        return drawOdd;
    }

    public void setDrawOdd(float drawOdd) {
        this.drawOdd = drawOdd;
    }
    public float getDecimalHostsOdd() {
        return decimalHostsOdd;
    }

    public void setDecimalHostsOdd(float decimalHostsOdd) {
        this.decimalHostsOdd = decimalHostsOdd;
    }

    public float getDecimalGuestsOdd() {
        return decimalGuestsOdd;
    }

    public void setDecimalGuestsOdd(float decimalGuestsOdd) {
        this.decimalGuestsOdd = decimalGuestsOdd;
    }

    public float getDecimalDrawOdd() {
        return decimalDrawOdd;
    }

    public void setDecimalDrawOdd(float decimalDrawOdd) {
        this.decimalDrawOdd = decimalDrawOdd;
    }
}
