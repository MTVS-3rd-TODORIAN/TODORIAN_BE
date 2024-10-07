package com.todorian.todo.domain.repository;

import com.todorian.todo.domain.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByMemberId(Long memberId);

    // 특정 날짜의 Todo 조회
    @Query("SELECT t FROM Todo t WHERE DATE(t.createdAt) = :date AND t.memberId = :memberId")
    List<Todo> findByCreateAtDateAndMemberId(@Param("date") LocalDate date, @Param("memberId") Long memberId);
}
