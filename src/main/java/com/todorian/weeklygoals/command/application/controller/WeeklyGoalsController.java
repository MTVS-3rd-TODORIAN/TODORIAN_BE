package com.todorian.weeklygoals.command.application.controller;

import com.todorian._core.utils.ApiUtils;
import com.todorian._core.utils.SecurityUtils;
import com.todorian.weeklygoals.command.application.dto.WeeklyGoalsRequestDTO;
import com.todorian.weeklygoals.command.application.dto.WeeklyGoalsResponseDTO;
import com.todorian.weeklygoals.command.application.service.WeeklyGoalsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WeeklyGoalsController {
    private final WeeklyGoalsService weeklyGoalsService;

    @GetMapping("/weekly/{day}")
    public ResponseEntity<?> findWeeklyGoals(@PathVariable("day") String day /* yyyy-MM-dd */) {
        LocalDate selectedDay;
        // 메소드 실행 검증 및 날짜 데이터 변경(formatting)
        try {
            selectedDay = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(ApiUtils.error("날짜 형식이 잘못되었습니다."));
        }
        String content = weeklyGoalsService.findWeeklyGoals(selectedDay, SecurityUtils.getCurrentMemberId());
        return ResponseEntity.ok().body(ApiUtils.success(content));
    }

    @PostMapping("/weekly/save")
    public ResponseEntity<?> saveWeeklyGoals(@RequestBody WeeklyGoalsRequestDTO.createDTO dto) {
        weeklyGoalsService.save(dto, SecurityUtils.getCurrentMemberId());
        return ResponseEntity.ok().body(ApiUtils.success("주간 목표가 저장되었습니다."));
    }
}
