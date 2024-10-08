package com.todorian.membercharacter.command.application.service;

import com.todorian.character.command.application.dto.CharacterUpdateRequestDTO;
import com.todorian.character.command.domain.model.Character;
import com.todorian.membercharacter.command.application.dto.MemberCharacterUpdateRequestDTO;
import com.todorian.membercharacter.command.domain.model.MemberCharacter;
import com.todorian.membercharacter.command.domain.repository.MemberCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberCharacterUpdateService {

    private MemberCharacterRepository memberCharacterRepository;

    @Autowired
    public MemberCharacterUpdateService(MemberCharacterRepository memberCharacterRepository) {
        this.memberCharacterRepository = memberCharacterRepository;
    }

    @Transactional
    public void updateMemberCharacterById(long id, MemberCharacterUpdateRequestDTO memberCharacterInfo){

        Optional<MemberCharacter> optionalMemberCharacter = memberCharacterRepository.findById(id);

        if(optionalMemberCharacter.isPresent()){
            MemberCharacter memberCharacter = optionalMemberCharacter.get();

            // 수정 필요
            if (memberCharacterInfo.getMemberId() < 0) {
                memberCharacter.setMemberId(memberCharacterInfo.getMemberId());
            }
            if (memberCharacterInfo.getCharacterId() < 0) {
                memberCharacter.setCharacterId(memberCharacterInfo.getCharacterId());
            }
            if (memberCharacterInfo.getGrowthPoint() < 0) {
                memberCharacter.setMemberId(memberCharacterInfo.getMemberId());
            }

        } else {
            throw new IllegalArgumentException("ID에 해당하는 회원의 캐릭터가 존재하지 않습니다.");
        }
    }
}
