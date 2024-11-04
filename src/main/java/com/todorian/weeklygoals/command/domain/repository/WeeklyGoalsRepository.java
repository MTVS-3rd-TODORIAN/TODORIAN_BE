package com.todorian.weeklygoals.command.domain.repository;

import com.todorian.todo.domain.model.Todo;
import com.todorian.weeklygoals.command.domain.model.WeeklyGoals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WeeklyGoalsRepository extends JpaRepository<WeeklyGoals, Long> {
    @Query("select w from WeeklyGoals w where w.week = :week and w.memberId = :memberId")
    List<WeeklyGoals> findByMemberIdAndCreatedAt(@Param("week") int week, @Param("memberId") Long memberId);

}
