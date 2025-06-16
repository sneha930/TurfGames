package com.lcwd.game.turf.GameTurf.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class GameSlotDto {

    public String id;
    public String slotName;
    public LocalTime startTime;
    public LocalTime endTime;
    public LocalDate date;
    public boolean isBooked;

    public GameDto game;
    public List<PlayerDto> playerDtos;
    public TurfSizeDto turfSizeDto;

    public GameSlotDto() {
    }

    public GameSlotDto(String id, String slotName, LocalTime startTime, LocalTime endTime, LocalDate date, boolean isBooked, GameDto game, List<PlayerDto> playerDtos, TurfSizeDto turfSizeDto) {
        this.id = id;
        this.slotName = slotName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.isBooked = isBooked;
        this.game = game;
        this.playerDtos = playerDtos;
        this.turfSizeDto = turfSizeDto;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public GameDto getGame() {
        return game;
    }

    public void setGame(GameDto game) {
        this.game = game;
    }

    public List<PlayerDto> getPlayerDtos() {
        return playerDtos;
    }

    public void setPlayerDtos(List<PlayerDto> playerDtos) {
        this.playerDtos = playerDtos;
    }

    public TurfSizeDto getTurfSizeDto() {
        return turfSizeDto;
    }

    public void setTurfSizeDto(TurfSizeDto turfSizeDto) {
        this.turfSizeDto = turfSizeDto;
    }

    public LocalDate getDate() {return date;}

    public void setDate(LocalDate date) {this.date = date;}

    @Override
    public String toString() {
        return "GameSlotDto{" +
                "id='" + id + '\'' +
                ", slotName='" + slotName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", date=" + date +
                ", isBooked=" + isBooked +
                ", game=" + game +
                ", playerDtos=" + playerDtos +
                ", turfSizeDto=" + turfSizeDto +
                ", date=" + date +
                '}';
    }

    // Manual Builder Class
    public static class Builder {
        private String id;
        private String slotName;
        private LocalTime startTime;
        private LocalTime endTime;
        private LocalDate date;
        private boolean isBooked;
        private GameDto game;
        private List<PlayerDto> playerDtos;
        private TurfSizeDto turfSizeDto;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder slotName(String slotName) {
            this.slotName = slotName;
            return this;
        }

        public Builder startTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder isBooked(boolean isBooked) {
            this.isBooked = isBooked;
            return this;
        }

        public Builder game(GameDto game) {
            this.game = game;
            return this;
        }

        public Builder gameSlotPlayers(List<PlayerDto> players) {
            this.playerDtos = players;
            return this;
        }

        public Builder turfSizeDto(TurfSizeDto turfSizeDto) {
            this.turfSizeDto = turfSizeDto;
            return this;
        }

        public GameSlotDto build() {
            return new GameSlotDto(id, slotName, startTime, endTime, date, isBooked, game, playerDtos, turfSizeDto);
        }
    }
}
