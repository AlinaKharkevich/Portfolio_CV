package com.alinaharkevich.mas_mp5.repository;

import com.alinaharkevich.mas_mp5.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a FROM Appointment a WHERE a.reservations.adult.id = :id")
    List<Appointment> findAllByAdultId(Long id);

    @Query("SELECT a FROM Appointment a WHERE a.app_id = :app_id")
    Optional<Appointment> findByIdA(Long app_id);
}
