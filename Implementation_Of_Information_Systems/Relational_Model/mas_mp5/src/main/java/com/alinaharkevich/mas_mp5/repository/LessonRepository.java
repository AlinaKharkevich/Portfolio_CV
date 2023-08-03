package com.alinaharkevich.mas_mp5.repository;

import com.alinaharkevich.mas_mp5.model.Assignment;
import com.alinaharkevich.mas_mp5.model.Lesson;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
}
