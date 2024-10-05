package com.todorian.character.command.application.service;

import com.todorian.character.command.application.dto.CharacterCreateRequestDTO;
import com.todorian.character.command.domain.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.todorian.character.command.domain.model.Character;

@Service
public class CharacterCreateService {

    private CharacterRepository characterRepository;

    @Autowired
    public CharacterCreateService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Transactional
    public void createCharacter(CharacterCreateRequestDTO characterInfo) {
        Character character = new Character(
                characterInfo.getCharacterName(),
                characterInfo.getCharacterPrice(),
                characterInfo.getCharacterDescription(),
                characterInfo.getCharacterImgsrc(),
                characterInfo.getCharacterCategory()
        );

        characterRepository.save(character);
    }
}
