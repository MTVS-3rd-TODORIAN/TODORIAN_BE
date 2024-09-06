package com.todorian.member.command.application.controller;

import com.todorian._core.utils.ApiUtils;
import com.todorian.member.command.application.service.MemberStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.todorian._core.utils.SecurityUtils.getCurrentMemberId;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class MemberStatusController {

    private final MemberStatusService memberStatusService;

    /*
        회원 탈퇴
     */
    @DeleteMapping("/")
    public ResponseEntity<?> deleteMember() {

        memberStatusService.deleteMember(getCurrentMemberId());

        return ResponseEntity.ok().body(ApiUtils.success(null));
    }


    /*
        회원 복구
     */
}
