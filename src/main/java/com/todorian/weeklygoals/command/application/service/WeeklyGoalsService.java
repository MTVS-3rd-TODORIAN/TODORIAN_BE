package com.todorian.weeklygoals.command.application.service;

import com.todorian.weeklygoals.command.application.dto.WeeklyGoalsRequestDTO;
import com.todorian.weeklygoals.command.domain.model.WeeklyGoals;
import com.todorian.weeklygoals.command.domain.repository.WeeklyGoalsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Transactional
public class WeeklyGoalsService {
    private final WeeklyGoalsRepository weeklyGoalsRepository;

    public ArrayList<String> findWeeklyGoals(LocalDate selectedDate, Long memberId) {
        int week = selectedDate.get(WeekFields.of(Locale.getDefault()).weekOfYear());
        List<WeeklyGoals> weeklyGoals = weeklyGoalsRepository.findByMemberIdAndCreatedAt(week, memberId);
        ArrayList<String> weekContents = new ArrayList<>();
        weeklyGoals.forEach(w -> weekContents.add(w.getContent()));
        return weekContents;
    }

    public WeeklyGoals save(WeeklyGoalsRequestDTO.createDTO dto, Long memberId) {
        LocalDateTime now = LocalDateTime.now();
        int weekOfYear = now.get(WeekFields.of(Locale.getDefault()).weekOfYear());
        WeeklyGoals weeklyGoals = WeeklyGoals.builder()
                .content(dto.content())
                .completed(false)
                .memberId(memberId)
                .week(weekOfYear)
                .build();
        weeklyGoalsRepository.save(weeklyGoals);
        return weeklyGoals;
    }

}
