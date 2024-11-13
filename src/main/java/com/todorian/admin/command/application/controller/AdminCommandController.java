package com.todorian.admin.command.application.controller;

import com.todorian.admin.command.application.service.AdminCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminCommandController {

    private final AdminCommandService adminCommandService;
}
