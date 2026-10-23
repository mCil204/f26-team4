package com.example.CleanSweep1.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.CleanSweep1.dto.AppointmentRequest;
import com.example.CleanSweep1.entity.Appointment;
import com.example.CleanSweep1.entity.Cleaner;
import com.example.CleanSweep1.entity.Customer;
import com.example.CleanSweep1.repository.AppointmentRepository;
import com.example.CleanSweep1.repository.CleanerRepository;
import com.example.CleanSweep1.repository.CustomerRepository;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final CustomerRepository customerRepository;
    private final CleanerRepository cleanerRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              CustomerRepository customerRepository,
                              CleanerRepository cleanerRepository) {
        this.appointmentRepository = appointmentRepository;
        this.customerRepository = customerRepository;
        this.cleanerRepository = cleanerRepository;
    }

    public Appointment book(Long customerId, AppointmentRequest request) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Customer not found"));
        Cleaner cleaner = cleanerRepository.findById(request.cleanerId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cleaner not found"));

        if (!request.endTime().isAfter(request.startTime())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "End time must be after start time");
        }
        if (appointmentRepository.existsByCleanerCleanerIdAndAppointmentDateAndStartTimeAndStatus(
                request.cleanerId(), request.appointmentDate(), request.startTime(), "BOOKED")) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "That time slot is already booked");
        }

        Appointment appointment = new Appointment();
        appointment.setCustomer(customer);
        appointment.setCleaner(cleaner);
        appointment.setAppointmentDate(request.appointmentDate());
        appointment.setStartTime(request.startTime());
        appointment.setEndTime(request.endTime());
        appointment.setServiceType(request.serviceType());
        appointment.setNotes(request.notes());
        appointment.setStatus("BOOKED");
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getForCustomer(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Customer not found");
        }
        return appointmentRepository.findByCustomerCustomerId(customerId);
    }

    public void delete(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Appointment not found");
        }
        appointmentRepository.deleteById(id);
    }
}