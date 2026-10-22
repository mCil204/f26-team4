package com.example.CleanSweep1.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.CleanSweep1.entity.Cleaner;
import com.example.CleanSweep1.repository.CleanerRepository;


@Service
public class CleanerService {

    private final CleanerRepository cleanerRepository;

    public CleanerService(CleanerRepository cleanerRepository) {
        this.cleanerRepository = cleanerRepository;
    }

    public Cleaner create(Cleaner cleaner) {
        cleaner.setCleanerId(null); // make sure the DB generates the ID
        return cleanerRepository.save(cleaner);
    }

    public Cleaner getById(Long id) {
        return cleanerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cleaner not found"));
    }

    public Cleaner update(Long id, Cleaner updated) {
        Cleaner existing = getById(id); // throws 404 if missing
        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setEmail(updated.getEmail());
        existing.setBusinessName(updated.getBusinessName());
        existing.setBusinessDescription(updated.getBusinessDescription());
        existing.setPhone(updated.getPhone());
        existing.setStreetAddress(updated.getStreetAddress());
        existing.setZipCode(updated.getZipCode());
        existing.setCity(updated.getCity());
        existing.setState(updated.getState());
        existing.setSpecialties(updated.getSpecialties());
        existing.setHourlyRate(updated.getHourlyRate());
        return cleanerRepository.save(existing);
    }
}
