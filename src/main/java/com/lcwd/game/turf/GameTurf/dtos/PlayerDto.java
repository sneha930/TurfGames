package com.lcwd.game.turf.GameTurf.dtos;

import com.lcwd.game.turf.GameTurf.entities.Game;

import java.util.List;

public class PlayerDto {

    public String id;
    public List<GameSlotDto> gameSlotDtos;
    public List<GameDto> gameDtos;
    public UserSignUpResponseDto userSignUpResponseDto;
    public List<GameDto> favouriteGameDtos;

    public PlayerDto() {
    }

    public PlayerDto(String id, List<GameSlotDto> gameSlotDtos, List<GameDto> gameDtos, UserSignUpResponseDto userSignUpResponseDto, List<GameDto> favouriteGames) {
        this.id = id;
        this.gameSlotDtos = gameSlotDtos;
        this.gameDtos = gameDtos;
        this.userSignUpResponseDto = userSignUpResponseDto;
        this.favouriteGameDtos = favouriteGameDtos;
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

    public List<GameDto> getFavouriteGameDtos() {
        return favouriteGameDtos;
    }

    public void setFavouriteGameDtos(List<GameDto> favouriteGameDtos) {
        this.favouriteGameDtos = favouriteGameDtos;
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "id='" + id + '\'' +
                ", gameSlotDtos=" + gameSlotDtos +
                ", gameDtos=" + gameDtos +
                ", userSignUpResponseDto=" + userSignUpResponseDto +
                ", favouriteGameDtos=" + favouriteGameDtos +
                '}';
    }
}