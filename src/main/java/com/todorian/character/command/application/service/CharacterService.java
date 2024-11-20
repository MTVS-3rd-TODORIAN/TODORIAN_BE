package com.todorian.character.command.application.service;

import com.todorian._core.error.exception.Exception400;
import com.todorian.character.command.domain.model.Character;
import com.todorian.character.command.domain.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    /*
    캐릭터 단계별 경험치 조정
*/
    public void patchCharacterGrowthCriteria(Long characterId, Integer step, Integer newGrowthCriteria) {

        // 캐릭터 가져오기
        Character character = getCharacter(characterId);

        // 단계가 0일 경우 예외 처리
        if (step < 1) {
            throw new Exception400("유효하지 않은 단계입니다.");
        }

        // 성장 기준 리스트 가져오기
        List<Integer> growthCriteria = new ArrayList<>(character.getGrowthCriteria());

        // 유효성 검사: 인덱스 범위 확인
        if (step > growthCriteria.size()) {
            throw new Exception400("유효하지 않은 단계입니다.");
        }

        // 성장 기준 업데이트
        growthCriteria.set(step - 1, newGrowthCriteria);
        // 변경된 리스트를 캐릭터에 설정
        character.setGrowthCriteria(growthCriteria);

        // 변경된 캐릭터 저장
        characterRepository.save(character);
    }

    // 캐릭터 조회
    protected Character getCharacter(Long characterId) {
        return characterRepository.findById(characterId)
                .orElseThrow(() -> new Exception400("해당 캐릭터를 찾을 수 없습니다."));
    }
}
