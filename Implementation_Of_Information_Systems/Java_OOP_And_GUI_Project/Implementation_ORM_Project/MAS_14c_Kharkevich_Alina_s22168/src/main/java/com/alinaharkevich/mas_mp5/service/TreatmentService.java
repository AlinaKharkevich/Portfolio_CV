package com.alinaharkevich.mas_mp5.service;


import com.alinaharkevich.mas_mp5.repository.TreatmentRepository;
import org.springframework.stereotype.Service;

@Service
public class TreatmentService {

    // Inject the treatment repository
    private final TreatmentRepository treatmentRepository;

    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public void deleteTreatmentById(Long treatment_id) {
        treatmentRepository.deleteById(treatment_id);
    }
}