package com.todorian.member.service;

import com.todorian.member.command.application.service.MemberAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional(readOnly = true)
public class MemberAuthServiceTest {

    @Autowired
    private MemberAuthService memberAuthService;
}
