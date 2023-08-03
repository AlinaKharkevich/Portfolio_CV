package com.alinaharkevich.mas_mp5.repository;

import com.alinaharkevich.mas_mp5.model.Faculty;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FacultyRepositoryTest {

    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    @PersistenceContext
    private EntityManager entityManager;
    Faculty f1;

    @BeforeEach
    public void initData() {
        f1 = Faculty.builder().name("Biology").office(305).students_num(250).build();
    }


    @Test
    public void testRequiredDependencies() {
        assertNotNull(facultyRepository);
    }

    @Test
    public void testFetchFaculties() {
        Iterable<Faculty> all = facultyRepository.findAll();
        for (Faculty f : all) {
            System.out.println(f);
        }
    }

    @Test
    public void testSaveFaculties() {
        facultyRepository.save(f1);
        entityManager.flush();
        long count = facultyRepository.count();
        assertEquals(3, count);
    }

    @Test
    public void testSaveInvalidFacultyOffice() {
        assertThrows(ConstraintViolationException.class, () ->{
            f1.setOffice(-1000);
            facultyRepository.save(f1);
            entityManager.flush();
        });
    }

    @Test
    public void testFindByName() {
        List<Faculty> english = facultyRepository.findByName("English");
        System.out.println(english);
        assertEquals(1, english.size());
    }

    @Test
    public void testFindByNameAndOffice() {
        List<Faculty> math = facultyRepository.findByNameAndOffice("Math", 205);
        System.out.println(math);
        assertEquals(1, math.size());
    }

    @Test
    public void testFindFacultyWithStudentsNumGreaterThan() {
        List<Faculty> res = facultyRepository.findFacultyWithStudentsNumGreaterThan(200);
        System.out.println(res);
        assertEquals(1, res.size());
    }

}