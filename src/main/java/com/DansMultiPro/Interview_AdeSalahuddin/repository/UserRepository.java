package com.DansMultiPro.Interview_AdeSalahuddin.repository;

import com.DansMultiPro.Interview_AdeSalahuddin.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
//    @Query(value = "SELECT * FROM users s WHERE user_id=?1", nativeQuery = true)
//    List<User> findByUserId(Long id);
    Optional<User> findByUsername(String keyword);
    Optional<User> findByPassword(String keyword);
    Optional<User> findByName(String keyword);
    boolean existsByUsername(String username);

    boolean existsByEmail(String username);

}