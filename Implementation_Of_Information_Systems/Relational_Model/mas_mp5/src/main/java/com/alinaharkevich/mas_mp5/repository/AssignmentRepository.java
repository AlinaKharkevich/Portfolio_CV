package com.alinaharkevich.mas_mp5.repository;

import com.alinaharkevich.mas_mp5.model.Assignment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AssignmentRepository extends CrudRepository<Assignment, Long> { //Long is type of primary key in Faculty
}
