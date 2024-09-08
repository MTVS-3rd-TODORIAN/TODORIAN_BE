package com.todorian.todo.domain.repository;

import com.todorian.todo.domain.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByMemberId(Long memberId);

//    List<Todo> findAllByMemberIdAndCreateAt(Long memberId, LocalDateTime day);
}
