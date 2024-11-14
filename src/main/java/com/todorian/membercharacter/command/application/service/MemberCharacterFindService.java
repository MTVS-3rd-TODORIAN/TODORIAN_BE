package com.todorian.membercharacter.command.application.service;

import com.todorian.character.command.application.dto.CharacterFindResponseDTO;
import com.todorian.membercharacter.command.application.dto.MemberCharacterFindResponseDTO;
import com.todorian.membercharacter.command.domain.model.MemberCharacter;
import com.todorian.membercharacter.command.domain.repository.MemberCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MemberCharacterFindService {

    private MemberCharacterRepository memberCharacterRepository;

    @Autowired
    public MemberCharacterFindService(MemberCharacterRepository memberCharacterRepository) {
        this.memberCharacterRepository = memberCharacterRepository;
    }

//    public List<MemberCharacterFindResponseDTO> findAllMemberCharacters(){
//
//        List<MemberCharacterFindResponseDTO> memberCharacterList
//                = memberCharacterRepository.findAll()
//                .stream()
//                .map(MemberCharacterFindResponseDTO::new)
//                .toList();
//
//        return memberCharacterList;
//    }

    public MemberCharacter findMemberCharacterById(long id){
        return memberCharacterRepository.findById(id).orElseThrow(IllegalArgumentException::new);

    }

    public List<MemberCharacterFindResponseDTO> findMemberCharactersByMemberId(Long memberId) {

        List<MemberCharacterFindResponseDTO> memberCharacterList =
                memberCharacterRepository
                        .findMemberCharactersByMemberId(memberId)
                        .stream()
                        .map(MemberCharacterFindResponseDTO::new)
                        .toList();

        return memberCharacterList;
    }

    public MemberCharacterFindResponseDTO findCurrentMemberCharacterByMemberId(Long memberId) {

        MemberCharacterFindResponseDTO currentMemberCharacter =
                new MemberCharacterFindResponseDTO(memberCharacterRepository.findCurrentMemberCharacterByMemberId(memberId));

        return currentMemberCharacter;
    }

    public MemberCharacter findOneMemberCharacter(Long memberId) {
        return memberCharacterRepository.findOneByMemberId(memberId);
    }
}
