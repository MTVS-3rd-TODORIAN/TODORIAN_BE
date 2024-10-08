package com.todorian.membercharacter.command.application.service;

import com.todorian.character.command.application.dto.CharacterFindResponseDTO;
import com.todorian.membercharacter.command.application.dto.MemberCharacterFindResponseDTO;
import com.todorian.membercharacter.command.domain.model.MemberCharacter;
import com.todorian.membercharacter.command.domain.repository.MemberCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberCharacterFindService {

    private MemberCharacterRepository memberCharacterRepository;

    @Autowired
    public MemberCharacterFindService(MemberCharacterRepository memberCharacterRepository) {
        this.memberCharacterRepository = memberCharacterRepository;
    }

    public List<MemberCharacterFindResponseDTO> findAllMemberCharacters(){

        List<MemberCharacterFindResponseDTO> memberCharacterList
                = memberCharacterRepository.findAll()
                .stream()
                .map(MemberCharacterFindResponseDTO::new)
                .toList();

        return memberCharacterList;
    }

    public MemberCharacterFindResponseDTO findMemberCharacterById(long id){
        return new MemberCharacterFindResponseDTO(memberCharacterRepository.findById(id).orElseThrow(IllegalArgumentException::new));

    }
}
