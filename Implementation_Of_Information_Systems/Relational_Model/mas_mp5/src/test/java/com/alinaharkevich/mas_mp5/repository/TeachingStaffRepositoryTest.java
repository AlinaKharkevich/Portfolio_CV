package com.alinaharkevich.mas_mp5.repository;

import com.alinaharkevich.mas_mp5.model.Postgraduate;
import com.alinaharkevich.mas_mp5.model.Professor;
import com.alinaharkevich.mas_mp5.model.TeachingStaff;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class TeachingStaffRepositoryTest {

    @Autowired
    private TeachingStaffRepository teachingStaffRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private PostgraduateRepository postgraduateRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Professor p1,p2;
    Postgraduate post1;

    @BeforeEach
    public void initData() {
        p1 = Professor.builder().name("Alina").salary(7000).preferable_mode("Online").
                languages(new HashSet<>(Arrays.asList("English", "Russian"))).build();
        p2 = Professor.builder().name("Olga").salary(8000).preferable_mode("Offline").
                languages(new HashSet<>(Arrays.asList("Polish", "Russian"))).build();
        post1 = Postgraduate.builder().name("Adam").salary(3000).supervisor("Head of Faculty").build();
    }

    @Test
    public void testRequiredDependencies() {
        assertNotNull(teachingStaffRepository);
        assertNotNull(professorRepository);
        assertNotNull(postgraduateRepository);
    }

    @Test
    public void testSaveTeachingStaff() {
        professorRepository.saveAll(Arrays.asList(p1,p2));
        postgraduateRepository.save(post1);
        entityManager.flush();
        long count = teachingStaffRepository.count();
        assertEquals(3, count);
    }
}