package com.alinaharkevich.mas_mp5;

import com.alinaharkevich.mas_mp5.model.Appointment;
import com.alinaharkevich.mas_mp5.repository.TreatmentRepository;
import com.alinaharkevich.mas_mp5.service.AppointmentService;
import com.alinaharkevich.mas_mp5.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller // Use @Controller instead of @RestController
@RequestMapping("/adults")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final TreatmentService treatmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, TreatmentService treatmentService) {
        this.appointmentService = appointmentService;
        this.treatmentService = treatmentService;
    }


    @GetMapping("/{id}/appointments")
    public String getAdultAppointments(@PathVariable("id") Long id, Model model) {
        List<Appointment> appointments = appointmentService.getAllAppointmentsForAdult(id);
        model.addAttribute("appointments", appointments);
        return "appointments"; // Remove the .html extension from the view name
    }

    @GetMapping("/appointment/{id}")
    public String showAppointmentDetails(@PathVariable("id") Long id, Model model) {
        Optional<Appointment> optionalAppointment = appointmentService.getAppointmentById(id);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            model.addAttribute("appointment", appointment);
            return "appointment-details";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found");
        }
    }

    @DeleteMapping("/delete-treatment/{treatmentId}")
    public ResponseEntity<String> deleteTreatment(@PathVariable Long treatmentId) {
        treatmentService.deleteTreatmentById(treatmentId);
        return ResponseEntity.ok("Treatment deleted successfully");
    }
}


