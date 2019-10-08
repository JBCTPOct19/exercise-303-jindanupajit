package com.jindanupajit.javabootcamp.exercise3031.repository;

import com.jindanupajit.javabootcamp.exercise3031.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
