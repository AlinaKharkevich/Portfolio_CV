package com.alinaharkevich.mas_mp5.repository;

import com.alinaharkevich.mas_mp5.model.Faculty;
import com.alinaharkevich.mas_mp5.model.Postgraduate;
import com.alinaharkevich.mas_mp5.model.Professor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AssociationTest {

    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Faculty f1;
    Professor p1;

    @BeforeEach
    public void initData() {
        f1 = Faculty.builder().name("Biology").office(305).students_num(250).build();

        p1 = Professor.builder().name("Alina").salary(7000).preferable_mode("Online").
                languages(new HashSet<>(Arrays.asList("English", "Russian"))).build();
    }


    @Test
    public void testRequiredDependencies() {
        assertNotNull(facultyRepository);
        assertNotNull(professorRepository);

    }
    @Test
    public void testSave(){
        f1.getTeachingStaff().add(p1);
        facultyRepository.save(f1);
        p1.setTeachIn(f1);
        professorRepository.save(p1);
        Optional<Faculty> byId = facultyRepository.findById(f1.getId());
        assertTrue(byId.isPresent());
        System.out.println(byId.get().getTeachingStaff());
        assertEquals(1,byId.get().getTeachingStaff().size());
    }
}
