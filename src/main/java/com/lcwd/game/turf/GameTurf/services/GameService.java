package com.lcwd.game.turf.GameTurf.services;

import com.lcwd.game.turf.GameTurf.dtos.GameDto;

import java.util.List;

public interface GameService {

//    create game
    GameDto createGame(GameDto gameDto);

//    Get All Games
    List<GameDto> getAllGames();

//    update game
    GameDto updateGame(GameDto gameDto, String gameId);

    GameDto getGameById(String gameId);

    void deleteGame(String gameId);
}
