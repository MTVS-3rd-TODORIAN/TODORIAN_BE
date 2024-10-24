package com.todorian;

import com.todorian.member.command.domain.model.property.Authority;
import com.todorian.member.command.domain.model.Member;
import com.todorian.member.command.domain.model.property.SocialType;
import com.todorian.member.command.domain.model.property.Status;
import com.todorian.member.command.domain.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class TodorianApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodorianApplication.class, args);
    }

    @Profile("local")
    @Bean
    CommandLineRunner localServerStart(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            memberRepository.saveAll(Arrays.asList(
                newMember("test@test.com", "test1234!", passwordEncoder)
            ));
        };
    }

    private Member newMember(String email, String password, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .nickName("Dorian")
                .email(email)
                .password(passwordEncoder.encode(password))
                .socialType(SocialType.NONE)
                .authority(Authority.USER)
                .status(Status.ACTIVE)
                .build();
    }
}
