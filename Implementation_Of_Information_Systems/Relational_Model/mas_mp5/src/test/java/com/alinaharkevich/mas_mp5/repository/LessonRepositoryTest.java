package com.alinaharkevich.mas_mp5.repository;

import com.alinaharkevich.mas_mp5.model.Assignment;
import com.alinaharkevich.mas_mp5.model.Lesson;
import com.alinaharkevich.mas_mp5.model.Professor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LessonRepositoryTest {

    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Assignment a1;
    Lesson l1;
    Professor p1;

    @BeforeEach
    public void initData() {
        p1 = Professor.builder().name("Olga").salary(8000).preferable_mode("Offline").
                languages(new HashSet<>(Arrays.asList("Polish", "Russian"))).build();
        l1 = Lesson.builder().name("Programming").students_num(5).build();
        a1 = Assignment.builder().owner(l1).content("Biology excersices").points_num(5).build();
    }

    @Test
    public void testRequiredDependencies() {
        assertNotNull(professorRepository);
        assertNotNull(lessonRepository);
        assertNotNull(assignmentRepository);
    }

    @Test
    public void testFetchFaculties() {
        Iterable<Lesson> all = lessonRepository.findAll();
        for (Lesson l : all) {
            System.out.println(l);
        }
    }

    @Test
    public void testSaveAssignments() {
        professorRepository.save(p1);
        lessonRepository.save(l1);
        assignmentRepository.save(a1);
        entityManager.flush();
        long count = lessonRepository.count();
        assertEquals(1, count);
    }

}


