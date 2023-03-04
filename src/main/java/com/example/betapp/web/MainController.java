package com.example.betapp.web;

import com.example.betapp.user_model.UserInput;
import com.example.betapp.repository.UserRepository;
import com.example.betapp.service.GameService;
import com.example.betapp.user_model.User;
import com.example.betapp.user_model.UsersComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private GameService gameService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @GetMapping({ "/", "/index"})
    public String createGames(Model model, @AuthenticationPrincipal UserDetails userDetails){
        gameService.createGames();
        User user = userRepository.findByEmail(userDetails.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("userInput", new UserInput());
        model.addAttribute("games", gameService.getGames());
        return "index";
    }

    @PostMapping("/results")
    public String saveUserInput(@ModelAttribute("userInput")UserInput userInput, Model model, @AuthenticationPrincipal UserDetails userDetails){
        System.out.println("Input Array: " + Arrays.toString(userInput.getUserInput()));
        int []arr = gameService.allGamesWinnerSelector();
        gameService.userPointsAfterOneGame(userInput.getUserInput(), userInput.getStake(), userDetails);

        List<User> users  = userRepository.findAll();
        Collections.sort(users, new UsersComparator());
        model.addAttribute("users", users);
        /*model.addAttribute("userPoints", userPoints);*/
        return "results";
    }
    @GetMapping("/ranking-table")
    public String showRankingTable(Model model){
        List<User> users  = userRepository.findAll();
        Collections.sort(users, new UsersComparator());
        model.addAttribute("users", users);

        return "ranking-table";
    }



}
