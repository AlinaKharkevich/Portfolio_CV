package com.alinaharkevich.mas_mp5.repository;
import com.alinaharkevich.mas_mp5.model.Appointment;
import com.alinaharkevich.mas_mp5.model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TreatmentRepository  extends JpaRepository<Treatment, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Treatment t WHERE t.treatment_id = :treatment_id")
    void deleteTreatmentById(@Param("treatment_id") Long treatment_id);
}
