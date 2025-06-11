/*
package com.lcwd.game.turf.GameTurf.controllers;

import com.lcwd.game.turf.GameTurf.dtos.ApiResponseMessage;
import com.lcwd.game.turf.GameTurf.dtos.PlayerDto;
import com.lcwd.game.turf.GameTurf.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/players")
@CrossOrigin(origins = "http://localhost:3000")
public class PlayerController {

    @Autowired
    PlayerService playerService;

//    create player
    @PostMapping
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerDto playerDto) {
        return new ResponseEntity<>(playerService.createPlayer(playerDto), HttpStatus.CREATED);
    }

//    get all players
    @GetMapping
    public  ResponseEntity<List<PlayerDto>> getAllPlayers() {
        return new ResponseEntity<>(playerService.getAllPlayers(), HttpStatus.OK);
    }

//       update player
    @PutMapping("/{playerId}")
    public  ResponseEntity<PlayerDto> updatePlayer(@RequestBody PlayerDto playerDto, @PathVariable String playerId) {
        PlayerDto playerDto1 = playerService.updatePlayer(playerDto, playerId);
        return  new ResponseEntity<>(playerDto1, HttpStatus.OK);
    }


//    get player by id
    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable String  playerId) {
        return new ResponseEntity<>(playerService.getPlayerById(playerId), HttpStatus.OK);
    }

//  delete player
    @DeleteMapping("/{playerId}")
    public ResponseEntity<ApiResponseMessage> deletePlayer(@PathVariable String playerId) {
        playerService.deletePlayer(playerId);
        ApiResponseMessage message = new ApiResponseMessage.Builder().message("Player deleted successfully").success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //    search player
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<PlayerDto>> searchPlayer(@PathVariable String keywords) {
        return  new ResponseEntity<>(playerService.searchPlayer(keywords), HttpStatus.OK);
    }

    //    upload player image
    //    serve player image

}
*/
