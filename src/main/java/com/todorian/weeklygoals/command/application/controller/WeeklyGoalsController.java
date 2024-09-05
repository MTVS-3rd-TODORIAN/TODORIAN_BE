package com.todorian.weeklygoals.command.application.controller;

import com.todorian.weeklygoals.command.application.service.WeeklyGoalsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeeklyGoalsController {
    private final WeeklyGoalsService weeklyGoalsService;


}
