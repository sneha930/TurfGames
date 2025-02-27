package com.lcwd.game.turf.GameTurf.repositories;

import com.lcwd.game.turf.GameTurf.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
}
