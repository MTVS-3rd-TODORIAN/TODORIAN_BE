package com.todorian.member.command.domain.model.property;

import com.todorian._core.error.exception.Exception400;
import lombok.Getter;

@Getter
public enum Status {
    // 활성, 휴면, 탈퇴, 정지
    ACTIVE("활성"),
    DORMANT("휴면"),
    DEACTIVATED("탈퇴"),
    SUSPENDED("정지");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public void checkActive() {
        switch (this) {
            case ACTIVE -> {
                return;
            }
            case DORMANT -> throw new Exception400("휴면 계정입니다.");
            case DEACTIVATED -> throw new Exception400("탈퇴한 계정입니다.");
            case SUSPENDED -> throw new Exception400("정지된 계정입니다.");
        }
    }
}
