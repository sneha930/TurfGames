package com.lcwd.game.turf.GameTurf.services;

import com.lcwd.game.turf.GameTurf.dtos.PlayerDto;

import java.util.List;

public interface PlayerService  {

//    create player
    PlayerDto createPlayer(PlayerDto playerDto);

//    get all players
    List<PlayerDto> getAllPlayers();

//    get player by id
    PlayerDto getPlayerById(String playerId);

}
