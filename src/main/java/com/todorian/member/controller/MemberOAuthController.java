package com.todorian.member.controller;

import com.todorian._core.utils.ApiUtils;
import com.todorian.member.dto.MemberResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/oauth")
public class MemberOAuthController {

    private final MemberOAuthService memberOAuthService;

    // https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=049ebf43b54fe431e060d0aa0a8e39af&redirect_uri=http://localhost:8080/api/auth/kakao/login
    /*
        카카오 로그인
     */
    @GetMapping("/kakao/login")
    public ResponseEntity<?> kakaoLogin(@RequestParam(name = "code") String code) {

        MemberResponseDTO.authTokenDTO responseDTO = memberSocialLoginService.kakaoLogin(code);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }
}
