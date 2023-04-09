package com.DansMultiPro.Interview_AdeSalahuddin.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long user_id;
    private String username;
    private String email;
    private String name;
    private List<String> roles;

}
