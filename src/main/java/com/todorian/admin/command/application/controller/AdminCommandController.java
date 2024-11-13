package com.todorian.admin.command.application.controller;

import com.todorian._core.utils.ApiUtils;
import com.todorian.admin.command.application.dto.AdminCommandRequestDTO;
import com.todorian.admin.command.application.dto.AdminCommandResponseDTO;
import com.todorian.admin.command.application.service.AdminCommandService;
import com.todorian.member.command.application.dto.MemberRequestDTO;
import com.todorian.member.command.application.dto.MemberResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminCommandController {

    private final AdminCommandService adminCommandService;

    /*
        관리자 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminCommandRequestDTO.authDTO requestDTO) {

        AdminCommandResponseDTO.authTokenDTO responseDTO = adminCommandService.login(requestDTO);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, responseDTO.grantType() + " " + responseDTO.accessToken())
                .header("Refresh-Token", responseDTO.grantType() + " " + responseDTO.refreshToken())
                .body(ApiUtils.success(null));
    }
}
