package com.todorian.membercharacter.command.domain.service;

import com.todorian.membercharacter.command.domain.model.MemberCharacter;
import com.todorian.membercharacter.command.domain.model.MemberCharacterCreateRequestDTO;
import com.todorian.membercharacter.command.domain.model.MemberCharacterFindResponseDTO;
import com.todorian.membercharacter.command.domain.repository.MemberCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberCharacterService {


    private MemberCharacterRepository memberCharacterRepository;

    @Autowired
    public MemberCharacterService(MemberCharacterRepository memberCharacterRepository) {
        this.memberCharacterRepository = memberCharacterRepository;
    }

    public void createMemberCharacter(MemberCharacterCreateRequestDTO memberCharacterInfo) {
        MemberCharacter memberCharacter = new MemberCharacter(
                memberCharacterInfo.getMemberId(),
                memberCharacterInfo.getCharacterId(),
                memberCharacterInfo.getAcquisitionTime()
        );

        memberCharacterRepository.save(memberCharacter);
    }

    public void removeMemberCharacter(long memberCharacterId) {
        memberCharacterRepository.deleteById(memberCharacterId);
    }

    public List<MemberCharacter> findAllMemberCharacters(){
        List<MemberCharacter> memberCharacterList = memberCharacterRepository.findAll();

        return memberCharacterList;
    }

    public MemberCharacter findMemberCharacterById(long memberCharacterId) {
        return memberCharacterRepository.findById(memberCharacterId).get();
    }
}
