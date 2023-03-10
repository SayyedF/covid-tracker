package com.jilani.covidtracker.controllers;

import com.jilani.covidtracker.models.LocationStats;
import com.jilani.covidtracker.services.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.OptionalInt;

@Controller
public class HomeController {

    @Autowired
    CovidDataService covidDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = covidDataService.getAllStats();
        int count = allStats.stream()
                        .mapToInt(it -> it.getLatestTotalCases())
                                .sum();
        model.addAttribute("totalCases", count);
        model.addAttribute("allStats",allStats);
        return "home";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model) {
        return "user";
    }
}
