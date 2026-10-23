package com.example.CleanSweep1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.CleanSweep1.dto.AppointmentRequest;
import com.example.CleanSweep1.entity.Appointment;
import com.example.CleanSweep1.service.AppointmentService;

import jakarta.validation.Valid;

@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/api/customers/{customerId}/appointments")
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment book(@PathVariable Long customerId,
                            @Valid @RequestBody AppointmentRequest request) {
        return appointmentService.book(customerId, request);
    }

    @GetMapping("/api/customers/{customerId}/appointments")
    public List<Appointment> list(@PathVariable Long customerId) {
        return appointmentService.getForCustomer(customerId);
    }

    @DeleteMapping("/api/appointments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable Long id) {
        appointmentService.delete(id);
    }
}