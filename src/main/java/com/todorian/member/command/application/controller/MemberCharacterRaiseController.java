package com.todorian.member.command.application.controller;

import com.todorian.membercharacter.command.application.service.MemberCharacterRaiseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberCharacterRaiseController {

    private final MemberCharacterRaiseService memberCharacterRaiseService;


}
