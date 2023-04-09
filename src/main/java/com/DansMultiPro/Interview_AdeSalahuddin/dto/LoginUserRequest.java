package com.DansMultiPro.Interview_AdeSalahuddin.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserRequest{
    private String username;
    private String password;
}
