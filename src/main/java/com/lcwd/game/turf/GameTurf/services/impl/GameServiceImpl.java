package com.lcwd.game.turf.GameTurf.services.impl;

import com.lcwd.game.turf.GameTurf.dtos.GameDto;
import com.lcwd.game.turf.GameTurf.dtos.GameSlotDto;
import com.lcwd.game.turf.GameTurf.dtos.GameSlotPlayerDto;
import com.lcwd.game.turf.GameTurf.entities.Game;
import com.lcwd.game.turf.GameTurf.entities.GameSlot;
import com.lcwd.game.turf.GameTurf.entities.GameSlotPlayer;
import com.lcwd.game.turf.GameTurf.exceptions.ResouceNotFoundException;
import com.lcwd.game.turf.GameTurf.repositories.GameRespository;
import com.lcwd.game.turf.GameTurf.services.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameRespository gameRespository;

    @Autowired
    private ModelMapper mapper;

//    create game
    @Override
    public GameDto createGame(GameDto gameDto) {
        // dto to entity
        Game game = gameDtoToEntity(gameDto);

        // Convert GameSlots DTOs to Entities
        List<GameSlot> gameSlots = gameDto.getGameSlots().stream().map(this::dtoToGameSlot).collect(Collectors.toList());
        game.setGameSlots(gameSlots);
        gameSlots.stream().forEach(gameSlot -> gameSlot.setGame(game));
        // Save game to database
        Game savedGame = gameRespository.save(game);

        // Convert saved entity back to DTO
        return entityToGameDto(savedGame);
    }

    @Override
    public List<GameDto> getAllGames() {
        List<Game> games = gameRespository.findAll();
        List<GameDto> gameDtoList = games.stream()
                .map(game -> entityToGameDto(game)).toList();
        return gameDtoList;
    }

//    update game
    @Override
    public GameDto updateGame(GameDto gameDto, String gameId) {

        // get game of given id
        Game game = gameRespository.findById(gameId).orElseThrow(() -> new ResouceNotFoundException("Game not found with given id"));

        // update game
        game.setName(gameDto.getName());
        game.setDescription(gameDto.getDescription());


        List<GameSlot> updatedGameSlots = gameDto.getGameSlots().stream()
                .map(this::dtoToGameSlot)
                .collect(Collectors.toList());

        updatedGameSlots.forEach(slot -> slot.setGame(game));
        game.setGameSlots(updatedGameSlots);

        Game updatedGame = gameRespository.save(game);
        return entityToGameDto(updatedGame);
    }

    // get game By Id
    @Override
    public GameDto getGameById(String gameId) {
        Game game = gameRespository.findById(gameId).orElseThrow(() -> new ResouceNotFoundException("Game not found with given id"));
        return entityToGameDto(game);
    }

    @Override
    public void deleteGame(String gameId) {
        Game game = gameRespository.findById(gameId).orElseThrow(() -> new ResouceNotFoundException("Game not found with given id"));
        gameRespository.delete(game);
    }

//        gameSlotDto to gameSlotEntity
    private GameSlot dtoToGameSlot(GameSlotDto gameSlotDto) {
        /*List<GameSlotPlayer> gameSlotPlayers = new ArrayList<>();
        if(gameSlotDto.getGameSlotPlayers() != null && !gameSlotDto.getGameSlotPlayers().isEmpty()) {
             gameSlotPlayers = gameSlotDto.getGameSlotPlayers().stream().map(this::gameSlotPlayerDtoToEntity).collect(Collectors.toList());
        }

        GameSlot gameSlot = new GameSlot(
                gameSlotDto.getId(),
                gameSlotDto.getSlotName(),
                gameSlotDto.getStartTime(),
                gameSlotDto.getEndTime(),
                gameSlotDto.isBooked(),
                null,
                gameSlotPlayers);
        return gameSlot;*/
        return mapper.map(gameSlotDto, GameSlot.class);
    }


//    GameSlotPlayerDto To GameSlotPlayerEntity
    private GameSlotPlayer gameSlotPlayerDtoToEntity(GameSlotPlayerDto gameSlotPlayerDto) {
        /*return new GameSlotPlayer(
                gameSlotPlayerDto.getId(),gameSlotPlayerDto.getGameSlot()
        );*/
        return mapper.map(gameSlotPlayerDto, GameSlotPlayer.class);
    }


//    GameSlotEntity to GameSlotDto: correct
    private GameSlotDto entityTogameSlotDto(GameSlot gameSlot) {
        /*// Convert GameSlotPlayer entities to PlayerDto objects
        List<GameSlotPlayerDto> gameSlotPlayerDtos = gameSlot.getGameSlotPlayers().stream().map(this::entityTogameSlotPlayerDto).collect(Collectors.toList());

        GameSlotDto gameSlotDto = new GameSlotDto(
                gameSlot.getId(),
                gameSlot.getSlotName(),
                gameSlot.getStartTime(),
                gameSlot.getEndTime(),
                gameSlot.isBooked(),
                gameSlot.getGame().getId(),
                gameSlotPlayerDtos
                );
        return  gameSlotDto;*/
        return mapper.map(gameSlot, GameSlotDto.class);
    }

    private GameSlotPlayerDto entityTogameSlotPlayerDto(GameSlotPlayer gameSlotPlayer) {

        /*GameSlotPlayerDto gameSlotPlayerDto = new GameSlotPlayerDto(
                gameSlotPlayer.getId(),
                gameSlotPlayer.getGameSlot(),
                gameSlotPlayer.getPlayer()
        );
        return gameSlotPlayerDto;*/
        return mapper.map(gameSlotPlayer, GameSlotPlayerDto.class);
    }

    //    gameDto to entity
    private Game gameDtoToEntity(GameDto gameDto) {
        /*List<GameSlot> gameSlots = gameDto.getGameSlots().stream()
                .map(this::dtoToGameSlot)
                .collect(Collectors.toList());

        Game game = new Game(
                gameDto.getId(),
                gameDto.getName(),
                gameDto.getDescription(),
                gameSlots);
        return game;*/
        return mapper.map(gameDto, Game.class);
    }

//    entity to gameDto
    private GameDto entityToGameDto(Game game) {
        /*List<GameSlotDto> gameSlotDtos = new ArrayList<>();
        game.getGameSlots().forEach(gameSlot -> gameSlotDtos.add(entityTogameSlotDto(gameSlot)));

        GameDto gameDto = new GameDto(
                game.getId(),
                game.getName(),
                game.getDescription(),
                gameSlotDtos
                );
        return gameDto;*/

        return mapper.map(game, GameDto.class);
    }

}
