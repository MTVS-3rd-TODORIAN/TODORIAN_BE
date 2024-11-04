package com.todorian.system.command.application.controller;

import com.todorian.system.command.application.service.MemberSystemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/setting/member")
public class MemberSystemController {

    private final MemberSystemService memberSystemService;

}
