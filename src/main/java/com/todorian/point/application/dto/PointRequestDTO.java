package com.todorian.point.application.dto;

public class PointRequestDTO {
    public record addPointDTO(
            String pointContent,
            Long memberId
    ) {}
}
