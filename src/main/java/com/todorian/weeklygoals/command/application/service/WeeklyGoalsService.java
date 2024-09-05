package com.todorian.weeklygoals.command.application.service;

import com.todorian.weeklygoals.command.domain.repository.WeeklyGoalsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WeeklyGoalsService {
    private final WeeklyGoalsRepository weeklyGoalsRepository;

}
