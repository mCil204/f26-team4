package com.example.CleanSweep1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.CleanSweep1.entity.Cleaner;
import com.example.CleanSweep1.entity.Customer;
import com.example.CleanSweep1.service.CleanerService;
import com.example.CleanSweep1.service.CustomerService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/cleaners")
public class CleanerController {
private final CleanerService cleanerService;

    public CleanerController(CleanerService cleanerService) {
        this.cleanerService = cleanerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cleaner create(@Valid @RequestBody Cleaner cleaner) {
        return cleanerService.create(cleaner);
    }

    @GetMapping("/{id}")
    public Cleaner getById(@PathVariable Long id) {
        return cleanerService.getById(id);
    }

    @PutMapping("/{id}")
    public Cleaner update(@PathVariable Long id, @Valid @RequestBody Cleaner cleaner) {
        return cleanerService.update(id, cleaner);
    }
}
