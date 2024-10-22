package com.todorian.membercharacter.command.application.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class MemberCharacterCreateRequestDTO {

    private long memberId;
    private long characterId;
    private int growthPoint;

    public MemberCharacterCreateRequestDTO() {}

    public MemberCharacterCreateRequestDTO(long memberId, long characterId, int growthPoint) {
        this.memberId = memberId;
        this.characterId = characterId;
        this.growthPoint = growthPoint;
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
        return "MemberCharacterCreateRequestDTO{" +
                "memberId=" + memberId +
                ", characterId=" + characterId +
                ", growthPoint=" + growthPoint +
                '}';
    }
}
