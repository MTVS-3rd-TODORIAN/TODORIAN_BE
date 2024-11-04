package com.todorian.membercharacter.command.domain.model;

import com.todorian.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(name = "step")
    private int step;

    @Column
    private int usedGrowthPoint;

    @Column(name="growth_point")
    private int growthPoint;

    @Builder
    public MemberCharacter(long memberId, long characterId) {
        this.memberId = memberId;
        this.characterId = characterId;
        this.step = 0;
        this.usedGrowthPoint = 0;
        this.growthPoint = 0;
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
