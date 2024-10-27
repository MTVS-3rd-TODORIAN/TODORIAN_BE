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

    public static PasswordEncoder passwordEncoder;

    @Profile("local")
    @Bean
    CommandLineRunner localServerStart(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            memberRepository.saveAll(Arrays.asList(
                newMember("Dorian", "test@test.com", "test1234"),
                newMember("During", "test1@test.com", "test1234!"),
                newAdmin("INUK", "admin@test.com", "test1234")
            ));
        };
    }

    private Member newMember(String nickName, String email, String password) {
        return Member.builder()
                .nickName(nickName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .socialType(SocialType.NONE)
                .authority(Authority.USER)
                .status(Status.ACTIVE)
                .build();
    }

    private Member newAdmin(String nickName, String email, String password) {
        return Member.builder()
                .nickName(nickName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .socialType(SocialType.NONE)
                .authority(Authority.ADMIN)
                .status(Status.ACTIVE)
                .build();
    }
}
