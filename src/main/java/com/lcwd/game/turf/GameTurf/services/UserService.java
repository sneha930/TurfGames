package com.lcwd.game.turf.GameTurf.services;

import com.lcwd.game.turf.GameTurf.dtos.UserSignInRequestDto;
import com.lcwd.game.turf.GameTurf.dtos.UserSignInResponseDto;
import com.lcwd.game.turf.GameTurf.dtos.UserSignUpRequestDto;
import com.lcwd.game.turf.GameTurf.dtos.UserSignUpResponseDto;

import java.util.List;

public interface UserService {


    UserSignUpResponseDto signup(UserSignUpRequestDto userSignUpRequestDto);

    List<UserSignUpResponseDto> getAllUsers();

    UserSignUpResponseDto signin(UserSignInRequestDto userSignInRequestDto);

    List<UserSignUpResponseDto> getUsersByRole();

    UserSignUpResponseDto getUserByEmailId(String emailId);
}
