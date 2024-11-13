package com.todorian.todo.point.command.domain.repository;

import com.todorian.todo.point.command.domain.model.TodoPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoPointCommandRepository extends JpaRepository<TodoPoint, Long> {
}
