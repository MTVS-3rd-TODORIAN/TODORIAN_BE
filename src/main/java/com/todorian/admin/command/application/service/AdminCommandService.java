package com.todorian.admin.command.application.service;

import com.todorian._core.error.exception.Exception400;
import com.todorian.admin.command.application.dto.AdminCommandRequestDTO;
import com.todorian.admin.command.application.dto.AdminCommandResponseDTO;
import com.todorian.character.command.application.service.CharacterFindService;
import com.todorian.character.command.application.service.CharacterService;
import com.todorian.character.command.domain.model.Character;
import com.todorian.character.command.domain.repository.CharacterRepository;
import com.todorian.todo.point.command.application.service.TodoPointCommandService;
import com.todorian.todo.point.command.domain.model.TodoPointType;
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
    private final CharacterService characterService;

    /*
        ToddPoint Ratio 변경
     */
    public AdminCommandResponseDTO.patchTodoPointRatioDTO patchTodoPointRatio(AdminCommandRequestDTO.patchTodoPointRatioDTO requestDTO) {

        Integer ratio = todoPointCommandService.patchTodoPointRatio(TodoPointType.fromString(requestDTO.todoPointType()), requestDTO.ratio());

        return new AdminCommandResponseDTO.patchTodoPointRatioDTO(
                ratio
        );
    }

    /*
        Character Level 별 growthCriteria 변경
     */
    public void patchCharacterGrowthCriteria(Long characterId, AdminCommandRequestDTO.patchCharacterGrowthCriteriaDTO requestDTO) {

        characterService.patchCharacterGrowthCriteria(characterId, requestDTO.step(), requestDTO.growthCriteria());
    }
}
