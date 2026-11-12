package com.example.CleanSweep1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.CleanSweep1.entity.Availability;
import com.example.CleanSweep1.entity.Specialties;
import com.example.CleanSweep1.service.CleanerSearchService;

@Controller
public class CleanerBrowseController {

    private final CleanerSearchService service;

    public CleanerBrowseController(CleanerSearchService service) {
        this.service = service;
    }

    @GetMapping("/browse")
    public String browse(@RequestParam(required = false) String location,
            @RequestParam(required = false) Double maxRate,
            @RequestParam(required = false) Availability availability,
            @RequestParam(required = false) Specialties specialty,
            Model model) {
        model.addAttribute("cleaners", service.search(location, maxRate, availability, specialty));
        model.addAttribute("location", location);
        model.addAttribute("maxRate", maxRate);
        model.addAttribute("availability", availability == null ? "" : availability.name());
        model.addAttribute("specialty", specialty == null ? "" : specialty.name());
        return "browse";
    }
}