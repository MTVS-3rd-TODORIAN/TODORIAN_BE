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

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class MemberStatusController {

    private final MemberStatusService memberStatusService;

    /*
        회원 탈퇴
     */
    @DeleteMapping("/{memberId}")
    public ResponseEntity<?> deleteMember(@PathVariable Long memberId) {

        memberStatusService.deleteMember(memberId);

        return ResponseEntity.ok().body(ApiUtils.success(null));
    }


    /*
        회원 복구
     */
}
