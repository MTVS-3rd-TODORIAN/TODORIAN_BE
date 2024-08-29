package com.todorian.character.command.domain.service;

import com.todorian.character.command.domain.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {

    private MenuRepository menuRepository;

    @Autowired
    public CharacterService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }


}
