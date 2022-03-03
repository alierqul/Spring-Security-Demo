package com.example.springsecuritydemo;

import com.example.springsecuritydemo.repository.UserRepository;
import com.example.springsecuritydemo.repository.entity.Authority;
import com.example.springsecuritydemo.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class SpringSecurityDemoApplication  {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemoApplication.class, args);
    }

    @PostConstruct
    protected void init() {
        List<Authority> authorityList=new ArrayList<>();
        authorityList.add(Authority.builder().roleCode("USER").roleDescription("User role").build());
        //authorityList.add(createAuthority("ADMIN","Admin role"));
        User user= User.builder()
                .userName("test")
                .password(passwordEncoder.encode("123456"))
                .firstName("Ali")
                .lastName("Ergül")
                .enabled(true)
                .authorities(authorityList)
                .build();
        userRepository.save(user);
    }
}
