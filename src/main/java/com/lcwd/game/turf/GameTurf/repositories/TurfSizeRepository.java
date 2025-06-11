package com.lcwd.game.turf.GameTurf.repositories;

import com.lcwd.game.turf.GameTurf.entities.TurfSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TurfSizeRepository extends JpaRepository<TurfSize, String> {

    @Override
    Optional<TurfSize> findById(String string);
}
