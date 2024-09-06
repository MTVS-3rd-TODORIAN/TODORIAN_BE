package com.todorian.weeklygoals.command.domain.repository;

import com.todorian.weeklygoals.command.domain.model.WeeklyGoals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeeklyGoalsRepository extends JpaRepository<WeeklyGoals, Long> {
    WeeklyGoals findByMemberId(Long memberId);

}
