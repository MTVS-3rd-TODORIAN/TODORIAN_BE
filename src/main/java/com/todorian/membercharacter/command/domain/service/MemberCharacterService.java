package com.todorian.membercharacter.command.domain.service;

import com.todorian.membercharacter.command.domain.model.MemberCharacter;
import com.todorian.membercharacter.command.domain.model.MemberCharacterCreateRequestDTO;
import com.todorian.membercharacter.command.domain.repository.MemberCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberCharacterService {

    @Autowired
    private MemberCharacterRepository memberCharacterRepository;

    public MemberCharacterService(MemberCharacterRepository memberCharacterRepository) {
        this.memberCharacterRepository = memberCharacterRepository;
    }

    public void save(MemberCharacterCreateRequestDTO memberCharacterInfo) {
        MemberCharacter memberCharacter = new MemberCharacter(
                memberCharacterInfo.getMemberId(),
                memberCharacterInfo.getCharacterId(),
                memberCharacterInfo.getAcquisitionTime()
        );

        memberCharacterRepository.save(memberCharacter);
    }
}
