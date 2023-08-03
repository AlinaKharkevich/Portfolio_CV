package com.alinaharkevich.mas_mp5.service;

import com.alinaharkevich.mas_mp5.model.Appointment;
import com.alinaharkevich.mas_mp5.model.Doctor;
import com.alinaharkevich.mas_mp5.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;


    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointmentsForAdult(Long id) {
        return appointmentRepository.findAllByAdultId(id);
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }


}

