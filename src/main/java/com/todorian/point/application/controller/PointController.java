package com.todorian.point.application.controller;

import com.todorian.point.application.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PointController {
    private final PointService pointService;



}
