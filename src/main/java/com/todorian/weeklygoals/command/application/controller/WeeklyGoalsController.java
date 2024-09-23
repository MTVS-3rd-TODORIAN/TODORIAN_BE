package com.todorian.weeklygoals.command.application.controller;

import com.todorian._core.utils.ApiUtils;
import com.todorian.weeklygoals.command.application.dto.WeeklyGoalsResponseDTO;
import com.todorian.weeklygoals.command.application.service.WeeklyGoalsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WeeklyGoalsController {
    private final WeeklyGoalsService weeklyGoalsService;

    @GetMapping("/weekly")
    public ResponseEntity<?> findWeeklyGoals(@RequestParam("memberId") Long memberId) {
        String content = weeklyGoalsService.findWeeklyGoals(memberId);
        WeeklyGoalsResponseDTO weeklyGoalsResponseDTO = new WeeklyGoalsResponseDTO(content);
        return ResponseEntity.ok().body(ApiUtils.success(weeklyGoalsResponseDTO));
    }
}
