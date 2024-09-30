package com.todorian.member.controller;

import com.todorian._core.utils.ApiUtils;
import com.todorian.member.command.application.controller.MemberAuthController;
import com.todorian.member.command.application.service.MemberAuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MemberAuthControllerTest {

    @InjectMocks
    private MemberAuthController memberAuthController;

    @Mock
    private MemberAuthService memberAuthService;
    @Mock
    private HttpServletRequest httpServletRequest;


}
