package com.alinaharkevich.mas_mp5.repository;

import com.alinaharkevich.mas_mp5.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AttributeAssociationTest {

    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private ConductionRepository conductionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Professor p1;
    Lesson l1;
    Conduction c1;

    @BeforeEach
    public void initData() {
        l1 = Lesson.builder().name("Programming").students_num(5).build();
        p1 = Professor.builder().name("Alina").salary(7000).preferable_mode("Online").
                languages(new HashSet<>(Arrays.asList("English", "Russian"))).build();
        c1 = Conduction.builder().startTime(LocalTime.from(LocalDateTime.now())).build();
    }


    @Test
    public void testRequiredDependencies() {
        assertNotNull(lessonRepository);
        assertNotNull(professorRepository);
        assertNotNull(conductionRepository);
    }

    @Test
    public void testSave(){
        c1.setLesson(l1);
        c1.setProfessor(p1);

        l1.setConductions(new HashSet<>(Arrays.asList(c1))); // Initialize the set before adding the conduction
        p1.setConductions(new HashSet<>(Arrays.asList(c1))); // Initialize the set before adding the conduction

        lessonRepository.save(l1);
        professorRepository.save(p1);
        conductionRepository.save(c1);

        Optional<Lesson> byIdLesson = lessonRepository.findById(l1.getId());
        assertTrue(byIdLesson.isPresent());
        System.out.println(byIdLesson.get().getConductions());
        assertEquals(1, byIdLesson.get().getConductions().size());
        assertEquals(l1.getId(), byIdLesson.get().getId());

        Optional<Professor> byIdProfessor = professorRepository.findById(p1.getId());
        assertTrue(byIdProfessor.isPresent());
        System.out.println(byIdProfessor.get().getConductions());
        assertEquals(1, byIdProfessor.get().getConductions().size());
        assertEquals(p1.getId(), byIdProfessor.get().getId());

        Optional<Conduction> byIdCond = conductionRepository.findById(c1.getId());
        assertTrue(byIdCond.isPresent());
        assertEquals(l1.getId(), byIdCond.get().getLesson().getId());
        assertEquals(p1.getId(), byIdCond.get().getProfessor().getId());

    }
}
