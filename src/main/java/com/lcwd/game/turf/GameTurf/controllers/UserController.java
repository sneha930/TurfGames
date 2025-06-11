package com.lcwd.game.turf.GameTurf.controllers;

import com.lcwd.game.turf.GameTurf.dtos.UserSignInRequestDto;
import com.lcwd.game.turf.GameTurf.dtos.UserSignInResponseDto;
import com.lcwd.game.turf.GameTurf.dtos.UserSignUpRequestDto;
import com.lcwd.game.turf.GameTurf.dtos.UserSignUpResponseDto;
import com.lcwd.game.turf.GameTurf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<UserSignInResponseDto> signin(@RequestBody UserSignInRequestDto userSignInRequestDto) {
        try {
            UserSignInResponseDto loggedInUser = userService.signin(userSignInRequestDto);
            return ResponseEntity.ok(loggedInUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponseDto> signup(@RequestBody UserSignUpRequestDto userSignUpRequestDto) {
        return new ResponseEntity<>(userService.signup(userSignUpRequestDto), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<UserSignUpResponseDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

}
