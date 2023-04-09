package com.DansMultiPro.Interview_AdeSalahuddin.models;

import com.DansMultiPro.Interview_AdeSalahuddin.dto.UserResponseDTO;
import lombok.*;
import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.Email;

@Getter
@Setter
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String username;

    @Email
    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "user",
            fetch = FetchType.LAZY)
    private List<UserRole> roles;

    public UserResponseDTO convertToResponse(List<String> roles){
        return UserResponseDTO.builder()
                .user_id(this.getUserId())
                .username(this.getUsername())
                .email(this.getEmail())
                .name(this.getName())
                .roles(roles)
                .build();
    }
}


