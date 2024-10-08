package com.todorian.character.command.application.service;

import com.todorian.character.command.application.dto.CharacterFindResponseDTO;
import com.todorian.character.command.domain.model.Character;
import com.todorian.character.command.application.dto.CharacterCreateRequestDTO;
import com.todorian.character.command.domain.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterFindService {

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterFindService(CharacterRepository characterRepository) {
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

    public List<CharacterFindResponseDTO> findAllCharacters() {
        List<CharacterFindResponseDTO> foundCharacters
                = characterRepository.findAll()
                .stream()
                .map(CharacterFindResponseDTO::new)
                .toList();

        return foundCharacters;
    }

    public CharacterFindResponseDTO findCharacterById(long id) {

        return new CharacterFindResponseDTO(characterRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }
}
