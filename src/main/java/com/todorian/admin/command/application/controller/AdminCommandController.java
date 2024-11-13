package com.todorian.admin.command.application.controller;

import com.todorian._core.utils.ApiUtils;
import com.todorian.admin.command.application.dto.AdminCommandRequestDTO;
import com.todorian.admin.command.application.dto.AdminCommandResponseDTO;
import com.todorian.admin.command.application.service.AdminCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
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
        ToddPoint Ratio 변경
     */
    @PutMapping("/todo-point")
    public ResponseEntity<?> patchTodoPointRatio(@RequestBody AdminCommandRequestDTO.patchTodoPointRatioDTO requestDTO) {

        AdminCommandResponseDTO.patchTodoPointRatioDTO responseDTO = adminCommandService.patchTodoPointRatio(requestDTO);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }
}
