package com.example.CleanSweep1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CleanSweep1.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
