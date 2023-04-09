package com.DansMultiPro.Interview_AdeSalahuddin.repository;

import com.DansMultiPro.Interview_AdeSalahuddin.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    List<UserRole> findByUserUserId(Long id);
    List<UserRole> findByRoleRoleNameIgnoreCase(String keyword);
}
