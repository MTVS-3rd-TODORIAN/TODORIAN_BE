package com.todorian.member.command.application.controller;

import com.todorian._core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    /*
        Member Profile
     */
    @GetMapping("/profile")
    public ResponseEntity<?> getMemberProfile() {

        return ResponseEntity.ok().body(ApiUtils.success(null));
    }
}
