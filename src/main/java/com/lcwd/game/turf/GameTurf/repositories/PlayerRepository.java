package com.lcwd.game.turf.GameTurf.repositories;

import com.lcwd.game.turf.GameTurf.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, String> {

   // List<Player> findByNameContaining(String keyword);
   @Override
   Optional<Player> findById(String string);

   Optional<Player> findByUser_Id(String id);
}
