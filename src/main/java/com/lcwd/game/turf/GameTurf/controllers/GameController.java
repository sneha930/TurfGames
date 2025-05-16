package com.lcwd.game.turf.GameTurf.controllers;

import com.lcwd.game.turf.GameTurf.dtos.ApiResponseMessage;
import com.lcwd.game.turf.GameTurf.dtos.GameDto;
import com.lcwd.game.turf.GameTurf.entities.Game;
import com.lcwd.game.turf.GameTurf.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "http://localhost:3000")
public class GameController {

    @Autowired
    GameService gameService;

//    create game
    @PostMapping
    public ResponseEntity<GameDto> createGame(@RequestBody GameDto gameDto) {
        return new ResponseEntity<>(gameService.createGame(gameDto), HttpStatus.CREATED) ;
    }

//    Get All Games
    @GetMapping
    public ResponseEntity<List<GameDto>> getAllGames() {
        return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK);
    }

//    get game by Id
    @GetMapping("/{gameId}")
    public ResponseEntity<GameDto> getGameById(@PathVariable String gameId) {
        return new ResponseEntity<>(gameService.getGameById(gameId), HttpStatus.OK);
    }

//    update game
    @PutMapping("/{gameId}")
    public ResponseEntity<GameDto> updateGame(@RequestBody GameDto gameDto, @PathVariable String gameId) {
        GameDto gameDto1 = gameService.updateGame(gameDto, gameId);
        return new ResponseEntity<>(gameDto1, HttpStatus.OK);
    }

//    delete game
    @DeleteMapping("/{gameId}")
    public ResponseEntity<ApiResponseMessage> deleteGame(@PathVariable String gameId) {
        gameService.deleteGame(gameId);
        ApiResponseMessage message = new ApiResponseMessage.Builder().message("Game deleted successfully").success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
