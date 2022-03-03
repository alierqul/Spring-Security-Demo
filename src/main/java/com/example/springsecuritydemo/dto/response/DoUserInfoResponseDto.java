package com.example.springsecuritydemo.dto.response;

import com.example.springsecuritydemo.repository.entity.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DoUserInfoResponseDto {
    String firstName;
    String lastName;
    String userName;
    String email;

    public DoUserInfoResponseDto(User user){
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        this.userName=user.getUsername();
        this.email=user.getEmail();
    }
}
