package com.lcwd.game.turf.GameTurf.controllers;

import com.lcwd.game.turf.GameTurf.dtos.TurfDto;
import com.lcwd.game.turf.GameTurf.services.TurfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turf")
@CrossOrigin(origins = "http://localhost:3000")
public class TurfController {

    @Autowired
    TurfService turfService;

//    create turf
    @PostMapping
    public ResponseEntity<TurfDto> createTurf(@RequestBody TurfDto turfDto) {
        return new ResponseEntity<>(turfService.createTurf(turfDto), HttpStatus.CREATED);
    }

//    get all turfs
    @GetMapping
    public ResponseEntity<List<TurfDto>> getAllTurfs() {
        return new ResponseEntity<>(turfService.getAllTurfs(), HttpStatus.OK);
    }
}
