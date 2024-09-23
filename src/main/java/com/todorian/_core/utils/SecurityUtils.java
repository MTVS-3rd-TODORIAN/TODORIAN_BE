package com.todorian._core.utils;

import com.todorian._core.error.exception.Exception401;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static Long getCurrentMemberId() {

        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        if(name.equals("anonymousUser")) {
            throw new Exception401("해당 유저의 ID를 찾을 수 없습니다.");
        }

        return Long.parseLong(name);
    }
}
