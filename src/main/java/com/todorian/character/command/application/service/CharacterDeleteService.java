package com.todorian.character.command.application.service;

import com.todorian.character.command.domain.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CharacterDeleteService {

    private CharacterRepository characterRepository;

    @Autowired
    public CharacterDeleteService (CharacterRepository characterRepository){
        this.characterRepository = characterRepository;
    }

    @Transactional
    public void deleteCharacterById(long id) {
        if (characterRepository.existsById(id)) {
            characterRepository.deleteById(id);  // ID에 해당하는 엔티티 삭제
        } else {
            throw new IllegalArgumentException("ID에 해당하는 캐릭터가 존재하지 않습니다.");
        }
    }
}
