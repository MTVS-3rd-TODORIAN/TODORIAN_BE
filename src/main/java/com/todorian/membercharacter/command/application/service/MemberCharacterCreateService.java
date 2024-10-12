package com.todorian.membercharacter.command.application.service;

import com.todorian.membercharacter.command.application.dto.MemberCharacterCreateRequestDTO;
import com.todorian.membercharacter.command.domain.model.MemberCharacter;
import com.todorian.membercharacter.command.domain.repository.MemberCharacterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberCharacterCreateService {

    private MemberCharacterRepository memberCharacterRepository;

    @Autowired
    public MemberCharacterCreateService(MemberCharacterRepository memberCharacterRepository) {
        this.memberCharacterRepository = memberCharacterRepository;
    }

    @Transactional
    public void createMemberCharacter(MemberCharacterCreateRequestDTO memberCharacterInfo) {

        MemberCharacter memberCharacter = new MemberCharacter(
                memberCharacterInfo.getMemberId(),
                memberCharacterInfo.getCharacterId(),
                memberCharacterInfo.getGrowthPoint()
        );

        memberCharacterRepository.save(memberCharacter);
    }
}
