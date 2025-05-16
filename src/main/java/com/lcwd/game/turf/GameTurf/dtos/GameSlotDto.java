package com.lcwd.game.turf.GameTurf.dtos;

import java.time.LocalTime;
import java.util.List;

public class GameSlotDto {

    private String id;
    private String slotName;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isBooked;

    private GameDto game;
    private List<GameSlotPlayerDto> gameSlotPlayers;
    private TurfDto turf;

    public GameSlotDto() {
    }

    public GameSlotDto(String id, String slotName, LocalTime startTime, LocalTime endTime, boolean isBooked,
                       GameDto game, List<GameSlotPlayerDto> gameSlotPlayers, TurfDto turf) {
        this.id = id;
        this.slotName = slotName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isBooked = isBooked;
        this.game = game;
        this.gameSlotPlayers = gameSlotPlayers;
        this.turf = turf;
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

    public List<GameSlotPlayerDto> getGameSlotPlayers() {
        return gameSlotPlayers;
    }

    public void setGameSlotPlayers(List<GameSlotPlayerDto> gameSlotPlayers) {
        this.gameSlotPlayers = gameSlotPlayers;
    }

    public TurfDto getTurf() {
        return turf;
    }

    public void setTurf(TurfDto turf) {
        this.turf = turf;
    }

    @Override
    public String toString() {
        return "GameSlotDto{" +
                "id='" + id + '\'' +
                ", slotName='" + slotName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isBooked=" + isBooked +
                ", game=" + game +
                ", gameSlotPlayers=" + gameSlotPlayers +
                ", turf=" + turf +
                '}';
    }

    // Manual Builder Class
    public static class Builder {
        private String id;
        private String slotName;
        private LocalTime startTime;
        private LocalTime endTime;
        private boolean isBooked;
        private GameDto game;
        private List<GameSlotPlayerDto> gameSlotPlayers;
        private TurfDto turf;

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

        public Builder isBooked(boolean isBooked) {
            this.isBooked = isBooked;
            return this;
        }

        public Builder game(GameDto game) {
            this.game = game;
            return this;
        }

        public Builder gameSlotPlayers(List<GameSlotPlayerDto> players) {
            this.gameSlotPlayers = players;
            return this;
        }

        public Builder turf(TurfDto turf) {
            this.turf = turf;
            return this;
        }

        public GameSlotDto build() {
            return new GameSlotDto(id, slotName, startTime, endTime, isBooked, game, gameSlotPlayers, turf);
        }
    }
}
