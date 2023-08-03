package com.alinaharkevich.mas_mp5.repository;

import com.alinaharkevich.mas_mp5.model.Assignment;
import com.alinaharkevich.mas_mp5.model.Faculty;
import com.alinaharkevich.mas_mp5.model.Lesson;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AssignmentRepositoryTest {

    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private LessonRepository lessonRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Assignment a1;
    Lesson l1;

    @BeforeEach
    public void initData() {
        l1 = Lesson.builder().name("Programming").students_num(5).build();
        a1 = Assignment.builder().owner(l1).content("Biology excersices").points_num(5).build();
    }

    @Test
    public void testRequiredDependencies() {
        assertNotNull(lessonRepository);
        assertNotNull(assignmentRepository);
    }

    @Test
    public void testFetchFaculties() {
        Iterable<Assignment> all = assignmentRepository.findAll();
        for (Assignment a : all) {
            System.out.println(a);
        }
    }

    @Test
    public void testSaveAssignments() {
        lessonRepository.save(l1);
        assignmentRepository.save(a1);
        entityManager.flush();
        long count = assignmentRepository.count();
        assertEquals(1, count);
    }

}

