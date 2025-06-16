package com.lcwd.game.turf.GameTurf.repositories;

import com.lcwd.game.turf.GameTurf.entities.Player;
import com.lcwd.game.turf.GameTurf.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmailId(String emailId);
    Optional<User> findByEmailId(String emailId);

    List<User> findByRoleIn(List<User.Role> roles);
}
