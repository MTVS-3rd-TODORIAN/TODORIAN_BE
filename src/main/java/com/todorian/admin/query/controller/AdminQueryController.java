package com.todorian.admin.query.controller;

import com.todorian._core.utils.ApiUtils;
import com.todorian.admin.query.dto.AdminQueryResponseDTO;
import com.todorian.admin.query.service.AdminQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminQueryController {

    private final AdminQueryService adminQueryService;

    /*
        Character List 조회
     */
    @GetMapping("/characters")
    public ResponseEntity<?> getCharacterList() {

        AdminQueryResponseDTO.getCharacterListDTO responseDTO = adminQueryService.getCharacterList();

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }
}
