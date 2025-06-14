package com.lcwd.game.turf.GameTurf.controllers;

import com.lcwd.game.turf.GameTurf.dtos.GameSlotDto;
import com.lcwd.game.turf.GameTurf.entities.GameSlot;
import com.lcwd.game.turf.GameTurf.services.GameSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gameslot")
@CrossOrigin(origins = "http://localhost:3000")
public class GameSlotController {

    @Autowired
    GameSlotService gameSlotService;

//    create game slot
    @PostMapping
    public ResponseEntity<GameSlotDto> createGameSlot(@RequestBody GameSlotDto gameSlotDto) {
        return new ResponseEntity<>(gameSlotService.createGameSlot(gameSlotDto), HttpStatus.CREATED);
    }
//    update game slot
    @PutMapping
    public ResponseEntity<GameSlotDto> updateGameSlot(@RequestBody GameSlotDto gameSlotDto) {
        return new ResponseEntity<>(gameSlotService.createGameSlot(gameSlotDto), HttpStatus.CREATED);
    }

//    get all game slots
    @GetMapping
    public ResponseEntity<List<GameSlotDto>> getAllGameSlots() {
        return new ResponseEntity<>(gameSlotService.getAllGameSlots(), HttpStatus.OK);
    }
}
