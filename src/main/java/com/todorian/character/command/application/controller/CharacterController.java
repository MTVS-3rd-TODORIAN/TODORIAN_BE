package com.todorian.character.command.application.controller;

import com.todorian.character.command.application.dto.CharacterCreateRequestDTO;
import com.todorian.character.command.application.dto.CharacterFindResponseDTO;
import com.todorian.character.command.application.dto.CharacterUpdateRequestDTO;
import com.todorian.character.command.application.service.CharacterCreateService;
import com.todorian.character.command.application.service.CharacterDeleteService;
import com.todorian.character.command.application.service.CharacterFindService;
import com.todorian.character.command.application.service.CharacterUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CharacterController {

    private CharacterFindService characterFindService;
    private CharacterCreateService characterCreateService;
    private CharacterUpdateService characterUpdateService;
    private CharacterDeleteService characterDeleteService;

    @Autowired
    public CharacterController(
            CharacterFindService characterFindService,
            CharacterCreateService characterCreateService,
            CharacterUpdateService characterUpdateService,
            CharacterDeleteService characterDeleteService
    ) {
        this.characterFindService = characterFindService;
        this.characterCreateService = characterCreateService;
        this.characterUpdateService = characterUpdateService;
        this.characterDeleteService = characterDeleteService;
    }

    @GetMapping("/characters")
    public List<CharacterFindResponseDTO> findAllCharacters(){

        List<CharacterFindResponseDTO> foundCharacters = characterFindService.findAllCharacters();

        return foundCharacters;
    }

    @GetMapping("/characters/{id}")
    public CharacterFindResponseDTO findCharacterById(@PathVariable long id){

        CharacterFindResponseDTO foundCharacter = characterFindService.findCharacterById(id);

        return foundCharacter;
    }

    @PostMapping("/characters")
    public void createCharacter(@RequestBody CharacterCreateRequestDTO newCharacter){

        characterCreateService.createCharacter(newCharacter);

    }

    @PatchMapping("/characters/{id}")
    public void updateCharacter(@PathVariable long id, @RequestBody CharacterUpdateRequestDTO characterInfo){

        characterUpdateService.updateCharacterById(id, characterInfo);

    }

    @DeleteMapping("/characters/{id}")
    public void deleteCharacter(@PathVariable long id){

        characterDeleteService.deleteCharacterById(id);

    }
}
