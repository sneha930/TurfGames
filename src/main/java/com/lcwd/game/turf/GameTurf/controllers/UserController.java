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
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<UserSignUpResponseDto> signin(@RequestBody UserSignInRequestDto userSignInRequestDto) {
        try {
            UserSignUpResponseDto loggedInUser = userService.signin(userSignInRequestDto);
            return ResponseEntity.ok(loggedInUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponseDto> signup(@RequestBody UserSignUpRequestDto userSignUpRequestDto) {
        System.out.println("Received DTO: " + userSignUpRequestDto);
        return new ResponseEntity<>(userService.signup(userSignUpRequestDto), HttpStatus.CREATED);
    }


    // get all users
    @GetMapping("/get_all_users")
    public ResponseEntity<List<UserSignUpResponseDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    // get users by role
    @GetMapping("/get_users_by_role")
    public  ResponseEntity<List<UserSignUpResponseDto>> getUsersByRole() {
        return new ResponseEntity<>(userService.getUsersByRole(), HttpStatus.OK);
    }

    // get user by emailId
    @GetMapping("/get_user_by_emailid/{emailId}")
    public ResponseEntity<UserSignUpResponseDto> getUserByEmailId(@PathVariable String emailId) {
        return new ResponseEntity<>(userService.getUserByEmailId(emailId), HttpStatus.OK);
    }

}
