package com.todorian.admin.query.service;

import com.todorian.admin.query.dto.AdminQueryResponseDTO;
import com.todorian.character.command.domain.model.Character;
import com.todorian.character.command.domain.repository.CharacterRepository;
import com.todorian.todo.point.command.domain.model.TodoPoint;
import com.todorian.todo.point.command.domain.model.TodoPointType;
import com.todorian.todo.point.command.domain.repository.TodoPointCommandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AdminQueryService {

    private final CharacterRepository characterRepository;
    private final TodoPointCommandRepository todoPointCommandRepository;

    /*
        Character List 조회
     */
    public AdminQueryResponseDTO.getCharacterListDTO getCharacterList() {

        List<Character> characterList = characterRepository.findAll();
        List<AdminQueryResponseDTO.CharacterDTO> characterDTOList = characterList.stream()
                .map(character -> new AdminQueryResponseDTO.CharacterDTO(
                        character.getCharacterId(),
                        character.getCharacterName(),
                        character.getCharacterImgsrc(),
                        character.getGrowthCriteria()
                ))
                .toList();

        return new AdminQueryResponseDTO.getCharacterListDTO(characterDTOList);
    }

    /*
        Point 정책 List 조회
     */
    public AdminQueryResponseDTO.getPointListDTO getPointList() {

        List<TodoPoint> todoPointList = Stream.of(TodoPointType.values())
                .map(todoPointType -> todoPointCommandRepository.findFirstByTodoPointTypeOrderByCreatedAt(todoPointType)
                        .orElse(null))
                .filter(Objects::nonNull)
                .toList();

        return new AdminQueryResponseDTO.getPointListDTO(
                todoPointList.stream()
                        .map(todoPoint -> new AdminQueryResponseDTO.pointDTO(
                                todoPoint.getId(),
                                todoPoint.getTodoPointType().toString(),
                                todoPoint.getCurrentRatio()
                        ))
                        .toList()
        );
    }
}
