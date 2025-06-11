package com.lcwd.game.turf.GameTurf.services;

import com.lcwd.game.turf.GameTurf.dtos.GameSlotDto;

import java.util.List;

public interface GameSlotService {

//    create game slot
    GameSlotDto createGameSlot(GameSlotDto gameSlotDto);

//    update game slot service
    public GameSlotDto updateGameSlot(GameSlotDto gameSlotDto);

//    get all game slots
    List<GameSlotDto> getAllGameSlots();
}
