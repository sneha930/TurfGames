package com.lcwd.game.turf.GameTurf.dtos;

import com.lcwd.game.turf.GameTurf.entities.GameSlot;
import com.lcwd.game.turf.GameTurf.entities.Player;
import jakarta.validation.constraints.NotNull;

public class GameSlotPlayerDto {

    public String id;

    @NotNull(message = "Gameslot cannot be null")
    public GameSlotDto gameSlot;

    @NotNull(message = "Player cannot be null")
    public PlayerDto player;

    public GameSlotPlayerDto() {
    }

    public GameSlotPlayerDto(String id, GameSlotDto gameSlot, PlayerDto player) {
        this.id = id;
        this.gameSlot = gameSlot;
        this.player = player;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GameSlotDto getGameSlot() {
        return gameSlot;
    }

    public void setGameSlot(GameSlotDto gameSlot) {
        this.gameSlot = gameSlot;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDto player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "GameSlotPlayerDto{" +
                "id='" + id + '\'' +
                ", gameSlot=" + gameSlot +
                ", player=" + player +
                '}';
    }

    public static class Builder {
        private String id;
        private GameSlotDto gameSlot;
        private PlayerDto player;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder gameSlot(GameSlotDto gameSlot) {
            this.gameSlot = gameSlot;
            return this;
        }

        public Builder player(PlayerDto player) {
            this.player = player;
            return this;
        }

        public GameSlotPlayerDto build() {
            return new GameSlotPlayerDto(id, gameSlot, player);
        }
    }

}
