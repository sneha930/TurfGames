package com.lcwd.game.turf.GameTurf.dtos;

import java.util.List;

public class PlayerDto {

    private String id;
    private List<GameSlotDto> gameSlotDtos;
    private UserSignUpResponseDto userSignUpResponseDto;

    public PlayerDto() {
    }

    public PlayerDto(String id, List<GameSlotDto> gameSlotDtos, UserSignUpResponseDto userSignUpResponseDto) {
        this.id = id;
        this.gameSlotDtos = gameSlotDtos;
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

    @Override
    public String toString() {
        return "PlayerDto{" +
                "id='" + id + '\'' +
                ", gameSlotDtos =" + gameSlotDtos +
                ", userSignUpResponseDto=" + userSignUpResponseDto +
                '}';
    }
}