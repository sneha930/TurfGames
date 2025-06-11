package com.lcwd.game.turf.GameTurf.services;

import com.lcwd.game.turf.GameTurf.dtos.TurfDto;
import com.lcwd.game.turf.GameTurf.entities.Turf;

import java.util.List;

public interface TurfService {

    //    get all turfs
    List<TurfDto> getAllTurfs();

//    create turf
    TurfDto createTurf(TurfDto turfDto);
}
