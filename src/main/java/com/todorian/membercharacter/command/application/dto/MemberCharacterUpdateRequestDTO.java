package com.todorian.membercharacter.command.application.dto;

import java.util.Date;

public class MemberCharacterUpdateRequestDTO {

    private long memberCharacterId;
    private long memberId;
    private long characterId;
    private int growthPoint;

    public MemberCharacterUpdateRequestDTO() {
    }

    public MemberCharacterUpdateRequestDTO(long memberCharacterId, long memberId, long characterId, int growthPoint) {
        this.memberCharacterId = memberCharacterId;
        this.memberId = memberId;
        this.characterId = characterId;
        this.growthPoint = growthPoint;
    }

    public long getMemberCharacterId() {
        return memberCharacterId;
    }

    public long getMemberId() {
        return memberId;
    }

    public long getCharacterId() {
        return characterId;
    }

    public int getGrowthPoint() {
        return growthPoint;
    }

    @Override
    public String toString() {
        return "MemberCharacterUpdateRequestDTO{" +
                "memberCharacterId=" + memberCharacterId +
                ", memberId=" + memberId +
                ", characterId=" + characterId +
                ", growthPoint=" + growthPoint +
                '}';
    }
}
