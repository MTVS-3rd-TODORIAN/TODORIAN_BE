package com.todorian.character;

import com.todorian.character.command.domain.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CharacterTests {

    @Autowired
    private CharacterService characterService;


}
