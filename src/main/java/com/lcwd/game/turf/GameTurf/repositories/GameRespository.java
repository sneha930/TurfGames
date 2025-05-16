package com.lcwd.game.turf.GameTurf.repositories;

import com.lcwd.game.turf.GameTurf.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRespository extends JpaRepository<Game, String> {
}
