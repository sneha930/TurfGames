package com.lcwd.game.turf.GameTurf.dtos;

import com.lcwd.game.turf.GameTurf.entities.Game;
import com.lcwd.game.turf.GameTurf.entities.GameSlot;
import com.lcwd.game.turf.GameTurf.entities.Turf;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalTime;

public class GameBookingDto {

    public String id;

    public GameDto game;

    public TurfDto turf;

    public GameSlotDto gameSlot;

    public LocalDate date;

    public LocalTime time;

    public GameBookingDto() {
    }

    public GameBookingDto(String id, GameDto game, TurfDto turf, GameSlotDto gameSlot, LocalDate date, LocalTime time) {
        this.id = id;
        this.game = game;
        this.turf = turf;
        this.gameSlot = gameSlot;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GameDto getGame() {
        return game;
    }

    public void setGame(GameDto game) {
        this.game = game;
    }

    public TurfDto getTurf() {
        return turf;
    }

    public void setTurf(TurfDto turf) {
        this.turf = turf;
    }

    public GameSlotDto getGameSlot() {
        return gameSlot;
    }

    public void setGameSlot(GameSlotDto gameSlot) {
        this.gameSlot = gameSlot;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "GameBookingDto{" +
                "id='" + id + '\'' +
                ", game=" + game +
                ", turf=" + turf +
                ", gameSlot=" + gameSlot +
                ", date=" + date +
                ", time=" + time +
                '}';
    }

    //    Manual builder class
    public static class Builder {
    private String id;
    private GameDto game;
    private TurfDto turf;
    private GameSlotDto gameSlot;
    private LocalDate date;
    private LocalTime time;

    public Builder setId(String id) {
        this.id = id;
        return this;
    }

    public Builder setGame(GameDto game) {
        this.game = game;
        return this;
    }

    public Builder setTurf(TurfDto turf) {
        this.turf = turf;
        return this;
    }

    public Builder setGameSlot(GameSlotDto gameSlot) {
        this.gameSlot = gameSlot;
        return this;
    }

    public Builder setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Builder setTime(LocalTime time) {
        this.time = time;
        return this;
    }

    public GameBookingDto build() {
        return new GameBookingDto(id, game, turf, gameSlot, date, time);
    }
}


}
