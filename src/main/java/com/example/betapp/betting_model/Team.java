package com.example.betapp.betting_model;

public class Team {
    private int id;
    private String name;
    private String division;
    private int rankOfDivision;

    public Team(int id, String name, String division, int rankOfDivision) {
        this.id = id;
        this.name = name;
        this.division = division;
        this.rankOfDivision = rankOfDivision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getRankOfDivision() {
        return rankOfDivision;
    }

    public void setRankOfDivision(int rankOfDivision) {
        this.rankOfDivision = rankOfDivision;
    }
}
