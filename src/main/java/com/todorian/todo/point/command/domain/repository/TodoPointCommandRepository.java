package com.todorian.todo.point.command.domain.repository;

import com.todorian.todo.point.command.domain.model.TodoPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoPointCommandRepository extends JpaRepository<TodoPoint, Long> {

    @Query("SELECT t FROM TodoPoint t ORDER BY t.createdAt DESC")
    Optional<TodoPoint> findFirstByOrderByCreatedAt();
}
