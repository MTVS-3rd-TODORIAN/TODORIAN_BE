package com.todorian.character.command.domain.service;

import com.todorian.character.command.domain.model.Character;
import com.todorian.character.command.domain.model.CharacterCreateRequestDTO;
import com.todorian.character.command.domain.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public void save(CharacterCreateRequestDTO characterInfo){
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
