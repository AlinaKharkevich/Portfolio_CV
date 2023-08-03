package com.alinaharkevich.mas_mp5.repository;

import com.alinaharkevich.mas_mp5.model.Assignment;
import com.alinaharkevich.mas_mp5.model.Conduction;
import org.springframework.data.repository.CrudRepository;

public interface ConductionRepository extends CrudRepository<Conduction, Long> {
}
