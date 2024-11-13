package com.todorian.admin.command.application.service;

import com.todorian.admin.command.application.dto.AdminCommandRequestDTO;
import com.todorian.admin.command.application.dto.AdminCommandResponseDTO;
import com.todorian.todo.point.command.application.service.TodoPointCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AdminCommandService {

    private final TodoPointCommandService todoPointCommandService;

    /*
        ToddPoint Ratio 변경
     */
    public AdminCommandResponseDTO.patchTodoPointRatioDTO patchTodoPointRatio(AdminCommandRequestDTO.patchTodoPointRatioDTO requestDTO) {

        Integer ratio = todoPointCommandService.patchTodoPointRatio(requestDTO.ratio());

        return new AdminCommandResponseDTO.patchTodoPointRatioDTO(
                ratio
        );
    }
}
