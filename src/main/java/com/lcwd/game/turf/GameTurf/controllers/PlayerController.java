package com.lcwd.game.turf.GameTurf.controllers;

import com.lcwd.game.turf.GameTurf.dtos.PlayerDto;
import com.lcwd.game.turf.GameTurf.entities.Player;
import com.lcwd.game.turf.GameTurf.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    //    create player

    @PostMapping
    public PlayerDto createPlayer(@RequestBody PlayerDto playerDto) {
        PlayerDto playerDto1 = playerService.createPlayer(playerDto);
        return playerDto1;
    }
    @GetMapping
    public  List<PlayerDto> getAllPlayers() {
        List<PlayerDto> playerDtoList = playerService.getAllPlayers();
        return playerDtoList;
    }

    //    update player
    //    delete player
    //    get all player

    //    get single player
    @GetMapping("/{playerId}")
    public PlayerDto getPlayerById(@PathVariable String  playerId) {
        return playerService.getPlayerById(playerId);
    }

    //    search player
    //    upload player image
    //    serve player image

}
