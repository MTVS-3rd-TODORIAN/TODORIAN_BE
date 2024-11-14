package com.todorian.todo.point.command.domain.model;

import com.todorian._core.error.exception.Exception400;

public enum TodoPointType {
    TODO, FEED, REMAIN;

    public static TodoPointType fromString(String todoPointType) {
        return switch (todoPointType) {
            case "todo" -> TODO;
            case "feed" -> FEED;
            case "remain" -> REMAIN;
            default -> throw new Exception400("해당 성장 포인트 사용처는 없는 사용처 입니다.");
        };
    }
}
