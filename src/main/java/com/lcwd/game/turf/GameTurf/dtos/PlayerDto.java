package com.lcwd.game.turf.GameTurf.dtos;

import com.lcwd.game.turf.GameTurf.entities.Game;

import java.util.List;

public class PlayerDto {

    private String id;
    private List<GameSlotDto> gameSlotDtos;
    private List<GameDto> gameDtos;
    private UserSignUpResponseDto userSignUpResponseDto;

    public PlayerDto() {
    }

    public PlayerDto(String id, List<GameSlotDto> gameSlotDtos, List<GameDto> gameDtos, UserSignUpResponseDto userSignUpResponseDto) {
        this.id = id;
        this.gameSlotDtos = gameSlotDtos;
        this.gameDtos = gameDtos;
        this.userSignUpResponseDto = userSignUpResponseDto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<GameSlotDto> getGameSlotDtos() {
        return gameSlotDtos;
    }

    public void setGameSlotDtos(List<GameSlotDto> gameSlotDtos) {
        this.gameSlotDtos = gameSlotDtos;
    }

    public UserSignUpResponseDto getUserSignUpResponseDto() {
        return userSignUpResponseDto;
    }

    public void setUserSignUpResponseDto(UserSignUpResponseDto userSignUpResponseDto) {
        this.userSignUpResponseDto = userSignUpResponseDto;
    }

    public List<GameDto> getGameDtos() {
        return gameDtos;
    }

    public void setGameDtos(List<GameDto> gameDtos) {
        this.gameDtos = gameDtos;
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "id='" + id + '\'' +
                ", gameSlotDtos=" + gameSlotDtos +
                ", gameDtos=" + gameDtos +
                ", userSignUpResponseDto=" + userSignUpResponseDto +
                '}';
    }
}