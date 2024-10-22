package com.todorian.membercharacter.command.domain.model;

import com.todorian.BaseTimeEntity;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="tbl_member_character")
public class MemberCharacter extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_character_id")
    private long memberCharacterId;

    @Column(name="member_id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="member_id")
    private long memberId;

    @Column(name="character_id")
    private long characterId;

    @Column(name="growth_point")
    private int growthPoint;

    public MemberCharacter() {}

    public MemberCharacter(long memberId, long characterId, int growthPoint) {
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

    public void setMemberCharacterId(long memberCharacterId) {
        this.memberCharacterId = memberCharacterId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public void setCharacterId(long characterId) {
        this.characterId = characterId;
    }

    public void setGrowthPoint(int growthPoint) {
        this.growthPoint = growthPoint;
    }

    @Override
    public String toString() {
        return "MemberCharacter{" +
                "memberCharacterId=" + memberCharacterId +
                ", memberId=" + memberId +
                ", characterId=" + characterId +
                ", growthPoint=" + growthPoint +
                '}';
    }
}
