package com.example.CleanSweep1.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CleanSweep1.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByCustomerCustomerId(Long customerId);

    boolean existsByCleanerCleanerIdAndAppointmentDateAndStartTimeAndStatus(
            Long cleanerId, LocalDate appointmentDate, LocalTime startTime, String status);
}