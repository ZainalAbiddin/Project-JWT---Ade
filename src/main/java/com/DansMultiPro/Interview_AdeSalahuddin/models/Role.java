package com.DansMultiPro.Interview_AdeSalahuddin.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "roles")
public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "role",
            fetch = FetchType.LAZY)
    private List<UserRole> users;
}

