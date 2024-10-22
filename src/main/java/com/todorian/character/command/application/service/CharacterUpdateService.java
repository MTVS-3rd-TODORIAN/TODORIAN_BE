package com.todorian.character.command.application.service;

import com.todorian.character.command.application.dto.CharacterUpdateRequestDTO;
import com.todorian.character.command.domain.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.todorian.character.command.domain.model.Character;

import java.util.Optional;

@Service
public class CharacterUpdateService {

    private CharacterRepository characterRepository;

    @Autowired
    public CharacterUpdateService(CharacterRepository characterRepository){
        this.characterRepository = characterRepository;
    }

    @Transactional
    public void updateCharacterById(long id, CharacterUpdateRequestDTO characterInfo){

        Optional<Character> optionalCharacter = characterRepository.findById(id);

        if(optionalCharacter.isPresent()){
            Character character = optionalCharacter.get();

            if (characterInfo.getCharacterName() != null) {
                character.setCharacterName(characterInfo.getCharacterName());
            }
            // 수정 필요
            if (characterInfo.getCharacterPrice() < 0) {
                character.setCharacterPrice(characterInfo.getCharacterPrice());
            }
            if (characterInfo.getCharacterDescription() != null) {
                character.setCharacterDescription(characterInfo.getCharacterDescription());
            }
            if (characterInfo.getCharacterImgsrc() != null) {
                character.setCharacterImgsrc(characterInfo.getCharacterImgsrc());
            }
            if (characterInfo.getCharacterCategory() != null) {
                character.setCharacterCategory(characterInfo.getCharacterCategory());
            }

        } else {
            throw new IllegalArgumentException("ID에 해당하는 결제 내역이 존재하지 않습니다.");
        }
    }
}
