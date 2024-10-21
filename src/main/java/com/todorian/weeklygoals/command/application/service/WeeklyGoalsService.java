package com.todorian.weeklygoals.command.application.service;

import com.todorian.weeklygoals.command.domain.model.WeeklyGoals;
import com.todorian.weeklygoals.command.domain.repository.WeeklyGoalsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class WeeklyGoalsService {
    private final WeeklyGoalsRepository weeklyGoalsRepository;

    public String findWeeklyGoals(LocalDate selectedDate, Long memberId) {
        WeeklyGoals weeklyGoals = weeklyGoalsRepository.findByMemberIdAndCreatedAt(selectedDate, memberId);
        return weeklyGoals.getContent();
    }

}
