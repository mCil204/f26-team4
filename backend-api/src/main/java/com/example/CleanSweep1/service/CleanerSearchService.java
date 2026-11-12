package com.example.CleanSweep1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.CleanSweep1.entity.Availability;
import com.example.CleanSweep1.entity.Cleaner;
import com.example.CleanSweep1.entity.Specialties;
import com.example.CleanSweep1.repository.CleanerRepository;

@Service
public class CleanerSearchService {

    private final CleanerRepository cleanerRepository;

    public CleanerSearchService(CleanerRepository cleanerRepository) {
        this.cleanerRepository = cleanerRepository;
    }

    public List<Cleaner> search(String location, Double maxRate,
            Availability availability, Specialties specialty) {
        boolean hasLocation = location != null && !location.isBlank();

        return cleanerRepository.findAll().stream()
                .filter(c -> !hasLocation
                        || (c.getCity() != null
                                && c.getCity().toLowerCase().contains(location.trim().toLowerCase()))
                        || (c.getZipCode() != null && c.getZipCode().equals(location.trim())))
                .filter(c -> maxRate == null || c.getHourlyRate() <= maxRate)
                .filter(c -> availability == null || c.getAvailability() == availability)
                .filter(c -> specialty == null || c.getSpecialties() == specialty)
                .toList();
    }
}