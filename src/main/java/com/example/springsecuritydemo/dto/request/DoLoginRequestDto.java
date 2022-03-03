package com.example.springsecuritydemo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoLoginRequestDto {
    @Size(min = 3 ,max =254 )
    String userName;
    @Size(min = 6 ,max =254 )
    String password;
}
