package com.example.betapp.service;

import com.example.betapp.betting_model.Game;
import com.example.betapp.betting_model.OddsCalculator;
import com.example.betapp.betting_model.Team;
import com.example.betapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GameService{
    @Autowired
    private OddsCalculator oddsCalculator;

    private final List<Team> teams = new ArrayList<>(Arrays.asList(
            new Team(1, "Man. City", "Premier league", 1),
            new Team(2, "Liverpool", "Premier league", 1),
            new Team(3, "AC Milan", "Serie A", 2),
            new Team(4, "Lazio Rzym", "Serie A", 2),
            new Team(5, "AS Monaco", "Ligue 1", 3),
            new Team(6, "Stade Rennes", "Ligue 1", 3),
            new Team(7, "Feyenoord Rotterdam", "Eredivisie", 4),
            new Team(8, "PSV Eindhoven", "Eredivisie", 4),
            new Team(9, "Celtic Glasgow", "Premiership", 5),
            new Team(10, "Rangers", "Premiership", 5),
            new Team(11, "Szachtar Donieck", "Premier-liha", 6),
            new Team(12, "Dynamo Kijów", "Premier-liha", 6),
            new Team(13, "Club Brugge", "Pro League", 7),
            new Team(14, "Genk", "Pro League", 7),
            new Team(15, "Olympiakos", "Superleague Ellada", 8),
            new Team(16, "PAOK", "Superleague Ellada", 8),
            new Team(17, "Bodo/Glimt", "Eliteserien", 9),
            new Team(18, "SK Brann", "Eliteserien", 9),
            new Team(19, "Legia Warszawa", "Ekstraklasa", 10),
            new Team(20, "Wisła Kraków", "Ekstraklasa", 10)
    ));
    private List<Game> games = new ArrayList<>(Arrays.asList());
    private ArrayList<Integer> gamesSequence = new ArrayList<Integer>();
    private void makeAnArrayListEmpty(ArrayList<Integer> arrayList){
        if(!(arrayList.isEmpty())){
            arrayList.clear();
            arrayList.add(new Integer(20)); //avoiding loop invariant
        }
        else{
            arrayList.add(new Integer(20)); //avoiding loop invariant
        }
    }
    private void gamesDrawingMachine(ArrayList<Integer> arrayList){ //It adds 20 different (between 0 and 19) to an arrayList
        makeAnArrayListEmpty(arrayList);
        for(int i = 0; i <= 19; i++) {
            boolean bool = false;
            int randomNumber = 0;
            while (bool != true) {
                randomNumber = (int) (Math.random() * (20));
                for (int j = 0; j < arrayList.size(); j++) {

                    if (randomNumber == arrayList.get(j)) {
                        bool = false;
                        break;
                    } else {
                        bool = true;
                    }
                }
                if(bool){
                    arrayList.add(randomNumber);
                }
            }
        }
    }

    public void createGames(){
        gamesDrawingMachine(gamesSequence);
        if(!(games.isEmpty())){
            games.clear();
        }
        for(int i = 1; i < 20; i = i+2){
            Team hostsTeam = teams.get(gamesSequence.get(i));
            Team guestsTeam = teams.get(gamesSequence.get(i+1));
            int hostsTeamRank = teams.get(gamesSequence.get(i)).getRankOfDivision();
            int guestsTeamRank = teams.get(gamesSequence.get(i+1)).getRankOfDivision();
            float hostsOdd = oddsCalculator.hostsOddsCalculator(hostsTeamRank, guestsTeamRank);
            float drawOdd = oddsCalculator.drawOddsCalculator(hostsTeamRank, guestsTeamRank);
            float guestsOdd = oddsCalculator.guestsOddsCalculator(hostsTeamRank, guestsTeamRank);
            games.add(new Game(hostsTeam.getName(),
                    guestsTeam.getName(),
                    hostsTeam.getRankOfDivision(),
                    guestsTeam.getRankOfDivision(),
                    hostsOdd, guestsOdd, drawOdd));

        }
    }

    public List<Game> getGames() {
        return games;
    }

    public int[] allGamesWinnerSelector(){
        int[] gamesResults = new int[10];
        for(int i = 0; i <= 9; i++){
            gamesResults[i] = oddsCalculator.gamesWinnerSelector(
                    games.get(i).getHostsOdd(),
                    games.get(i).getDrawOdd());

        }
        return gamesResults;
    }
    public boolean determineLotteryTicket(int[] userInput) {
        int[]gameWinners = allGamesWinnerSelector();
        boolean lotteryTicket = true;
        for (int i = 0; i < gameWinners.length; i++) {
            if (userInput[i] != gameWinners[i] && userInput[i] != 0) {
                lotteryTicket = false;
            }
        }
        return lotteryTicket;
    }
    public float calculateProductOfOdds(int[] userInput){
        float productOfOdds = 1;

        for(int i = 0; i < userInput.length; i++){
            Game game = games.get(i);
            switch (userInput[i]){
                case 1:
                    productOfOdds = productOfOdds * game.getDecimalHostsOdd();
                    break;
                case 2:
                    productOfOdds = productOfOdds * game.getDecimalDrawOdd();
                    break;
                case 3:
                    productOfOdds = productOfOdds * game.getDecimalGuestsOdd();
                    break;
            }
        }
        return productOfOdds;
    }
    @Autowired
    private UserRepository userRepository;
    @Transactional
    public void userPointsAfterOneGame(int[] userInput, int stake, @AuthenticationPrincipal UserDetails userDetails){
        float productOfOdds = calculateProductOfOdds(userInput);
        boolean lotteryTicket = determineLotteryTicket(userInput);
        float userPoints = userRepository.findByEmail(userDetails.getUsername()).getUserPoints();
        if(!lotteryTicket){
            userPoints = userPoints - stake;
        }
        else{
            if(productOfOdds == 1){
                userPoints = userPoints;
            }
            else {
                userPoints = userPoints + (stake * productOfOdds);
            }
        }
        userRepository.findByEmail(userDetails.getUsername()).setUsersPoints(userPoints);
    }
}
