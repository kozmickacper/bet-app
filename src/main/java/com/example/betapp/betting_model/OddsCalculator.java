package com.example.betapp.betting_model;

import org.springframework.stereotype.Component;

@Component("oddsCalculator")
public class OddsCalculator{
    private float differenceOfRank(int firstNum, int secNum){
        if(firstNum >= secNum){

            return firstNum - secNum;
        }
        else {
            return secNum - firstNum;
        }
    }
    private float[]randomSituationsGenerator(){
        float[]randomNumbers = new float[3];
        randomNumbers[0] = (float)(Math.random()*10);
        randomNumbers[1] = (float)(Math.random()*(10 - randomNumbers[0]));
        randomNumbers[2] = 10 - randomNumbers[0] - randomNumbers[1];
        return randomNumbers;
    }

    public float hostsOddsCalculator(float hostsRank, float guestsRank) {
        float[]randomSituationsGenerator = randomSituationsGenerator();
        float differenceOfRank = differenceOfRank((int)hostsRank, (int)guestsRank);
        int hostsRankPower = 11 - (int)hostsRank;
        int guestsRankPower = 11 - (int)guestsRank;
        int rankPowerSum = hostsRankPower + guestsRankPower;
        float benefitOfBeingHost = 4;
        float drawOdds = 34-(differenceOfRank*2);
        float hostsOdds = 100  - 10 - ((87 -(drawOdds))/rankPowerSum) * guestsRankPower - drawOdds + benefitOfBeingHost - randomSituationsGenerator[0];
        return hostsOdds;
    }
    public float drawOddsCalculator(float hostsRank, float guestsRank) {
        float[]randomSituationsGenerator = randomSituationsGenerator();
        float differenceOfRank = differenceOfRank((int)hostsRank, (int)guestsRank);
        float drawOdds = 34 - (differenceOfRank*2) - randomSituationsGenerator[1];
        return drawOdds;
    }
    public float guestsOddsCalculator(float hostsRank, float guestsRank) {
        float[]randomSituationsGenerator = randomSituationsGenerator();
        float differenceOfRank = differenceOfRank((int)hostsRank, (int)guestsRank);
        int hostsRankPower = 11 - (int)hostsRank;
        int guestsRankPower = 11 - (int)guestsRank;
        int rankPowerSum = hostsRankPower + guestsRankPower;
        float drawbackOfBeingGuest = 4;
        float drawOdds = 34-(differenceOfRank*2);
        float guestsOdds = (100 - ((87 -(drawOdds))/rankPowerSum) * hostsRankPower - drawOdds - drawbackOfBeingGuest - randomSituationsGenerator[2]);
        return guestsOdds;
    }
    public int gamesWinnerSelector(float hostsOdd, float drawOdd){
        int winner = 0;
        float randomNumber = (float)(Math.random()*100);
        drawOdd = drawOdd + hostsOdd;
        if(randomNumber <= hostsOdd){
            winner =  1;
        }
        else if(randomNumber <= drawOdd){
            winner = 2;
        }
        else{
            winner = 3;
        }
        return winner;
    }

}
