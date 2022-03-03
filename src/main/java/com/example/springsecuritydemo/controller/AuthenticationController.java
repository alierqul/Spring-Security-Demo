package com.example.springsecuritydemo.controller;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import com.example.springsecuritydemo.config.security.JWTTokenHelper;
import com.example.springsecuritydemo.dto.request.DoLoginRequestDto;
import com.example.springsecuritydemo.dto.response.DoLoginResponseDto;
import com.example.springsecuritydemo.dto.response.DoUserInfoResponseDto;
import com.example.springsecuritydemo.repository.entity.User;
import com.example.springsecuritydemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Slf4j
public class AuthenticationController {
    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid DoLoginRequestDto dto) throws InvalidKeySpecException, NoSuchAlgorithmException {
        log.debug("DTO= " +dto.toString());
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUserName(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user=(User)authentication.getPrincipal();
        String jwtToken=jwtTokenHelper.generateToken(user.getUsername());

        DoLoginResponseDto response=new DoLoginResponseDto();
        response.setToken(jwtToken);


        return ResponseEntity.ok(response);
    }

    @GetMapping("/auth/userinfo")
    public ResponseEntity<?> getUserInfo(Principal user){
        User userObj= (User) userService.loadUserByUsername(user.getName());

        DoUserInfoResponseDto response= new DoUserInfoResponseDto(userObj);

        return ResponseEntity.ok(response);



    }
}