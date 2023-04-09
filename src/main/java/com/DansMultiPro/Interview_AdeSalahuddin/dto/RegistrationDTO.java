package com.DansMultiPro.Interview_AdeSalahuddin.dto;

import com.DansMultiPro.Interview_AdeSalahuddin.models.User;
import lombok.*;

import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    @Size(min = 3, max = 40)
    private String name;
    @Size(max = 50)
    private String username;
    @Size(max = 50)
    private String email;
    @Size(min = 6, max=40)
    private String password;

    public User convertToEntity(){
        return User.builder()
                .name(this.name)
                .username(this.username)
                .email(this.email)
                .password(this.password)
                .build();
    }
}

