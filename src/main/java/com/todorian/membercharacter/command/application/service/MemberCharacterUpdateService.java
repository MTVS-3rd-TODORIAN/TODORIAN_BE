package com.todorian.membercharacter.command.application.service;

import com.todorian.character.command.application.dto.CharacterUpdateRequestDTO;
import com.todorian.character.command.domain.model.Character;
import com.todorian.character.command.domain.repository.CharacterRepository;
import com.todorian.membercharacter.command.application.dto.MemberCharacterUpdateRequestDTO;
import com.todorian.membercharacter.command.domain.model.MemberCharacter;
import com.todorian.membercharacter.command.domain.repository.MemberCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MemberCharacterUpdateService {

    private MemberCharacterRepository memberCharacterRepository;
    private CharacterRepository characterRepository;

    @Autowired
    public MemberCharacterUpdateService(
            MemberCharacterRepository memberCharacterRepository,
            CharacterRepository characterRepository
    ) {
        this.memberCharacterRepository = memberCharacterRepository;
        this.characterRepository = characterRepository;
    }

//    @Transactional
//    public void updateMemberCharacterById(long id, MemberCharacterUpdateRequestDTO memberCharacterInfo){
//
//        Optional<MemberCharacter> optionalMemberCharacter = memberCharacterRepository.findById(id);
//
//        if(optionalMemberCharacter.isPresent()){
//            MemberCharacter memberCharacter = optionalMemberCharacter.get();
//
//            // 수정 필요
//            if (memberCharacterInfo.getMemberId() < 0) {
//                memberCharacter.setMemberId(memberCharacterInfo.getMemberId());
//            }
//            if (memberCharacterInfo.getCharacterId() < 0) {
//                memberCharacter.setCharacterId(memberCharacterInfo.getCharacterId());
//            }
//            if (memberCharacterInfo.getGrowthPoint() < 0) {
//                memberCharacter.setMemberId(memberCharacterInfo.getMemberId());
//            }
//
//        } else {
//            throw new IllegalArgumentException("ID에 해당하는 회원의 캐릭터가 존재하지 않습니다.");
//        }
//    }

    @Transactional
    public void useGrowthPoint(MemberCharacter memberCharacter) {

        Integer growthPoint = memberCharacter.getGrowthPoint();
        Integer usedGrowthPoint = memberCharacter.getUsedGrowthPoint();
        List<Integer> growthCriteria = characterRepository.findById(memberCharacter.getCharacterId()).get().getGrowthCriteria();

        if(growthPoint >= 0){
            memberCharacter.setUsedGrowthPoint(usedGrowthPoint + 1);
            memberCharacter.setGrowthPoint(growthPoint - 1);
        }else{
            throw new IllegalArgumentException("보유 성장 포인트가 부족합니다.");
        }

        if(usedGrowthPoint < growthCriteria.get(0)){
            memberCharacter.setStep(0);
        }else if(usedGrowthPoint < growthCriteria.get(1)){
            memberCharacter.setStep(1);
        }else if(usedGrowthPoint < growthCriteria.get(2)){
            memberCharacter.setStep(2);
        }else{
            memberCharacter.setStep(3);
        }
    }
}
