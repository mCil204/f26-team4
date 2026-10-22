package com.example.CleanSweep1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CleanSweep1.entity.Cleaner;


public interface CleanerRepository extends JpaRepository<Cleaner, Long> {

}
