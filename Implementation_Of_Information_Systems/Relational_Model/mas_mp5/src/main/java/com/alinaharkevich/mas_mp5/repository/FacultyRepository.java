package com.alinaharkevich.mas_mp5.repository;

import com.alinaharkevich.mas_mp5.model.Faculty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends CrudRepository<Faculty, Long> { //Long is type of primary key in Faculty
    public List<Faculty> findByName(String name);
    public List<Faculty> findByNameAndOffice(String name, int office);
    @Query("from Faculty  as f where f.students_num > :minStudentsNum")
    public List<Faculty> findFacultyWithStudentsNumGreaterThan(@Param("minStudentsNum") int minStudentsNum);
    @Query("from Faculty  as f left join fetch f.teachingStaff where f.id = :id")
    public Optional<Faculty> findById(@Param("id") Long id);
}
