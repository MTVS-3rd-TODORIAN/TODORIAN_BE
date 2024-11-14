package com.todorian.weeklygoals.command.application.dto;

public class WeeklyGoalsRequestDTO {
    public record createDTO (
            String content
    ) {
    }
}
