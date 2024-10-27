package com.todorian.member.command.application.controller;

import com.todorian._core.utils.ApiUtils;
import com.todorian.member.command.application.dto.MemberRequestDTO;
import com.todorian.member.command.application.dto.MemberResponseDTO;
import com.todorian.member.command.application.service.MemberAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class MemberAdminController {

    private final MemberAdminService memberAdminService;

    /*
        관리자 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberRequestDTO.authDTO requestDTO) {

        MemberResponseDTO.authTokenDTO responseDTO = memberAdminService.login(requestDTO);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, responseDTO.grantType() + " " + responseDTO.accessToken())
                .header("Refresh-Token", responseDTO.grantType() + " " + responseDTO.refreshToken())
                .body(ApiUtils.success(null));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {

        return ResponseEntity.ok().body(ApiUtils.success(null));
    }
}
