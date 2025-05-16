package com.lcwd.game.turf.GameTurf.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "game_bookings")
public class GameBooking {

    @jakarta.persistence.Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "turf_id", nullable = false)
    private Turf turf;

    @ManyToOne
    @JoinColumn(name = "gameSlot_id", nullable = false)
    private GameSlot gameSlot;

    private LocalDate date;

    private LocalTime time;

    public GameBooking() {
    }

    public GameBooking(String id, Game game, Turf turf, GameSlot gameSlot, LocalDate date, LocalTime time) {
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Turf getTurf() {
        return turf;
    }

    public void setTurf(Turf turf) {
        this.turf = turf;
    }

    public GameSlot getGameSlot() {
        return gameSlot;
    }

    public void setGameSlot(GameSlot gameSlot) {
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
        return "GameBooking{" +
                "id='" + id + '\'' +
                ", game=" + game +
                ", turf=" + turf +
                ", gameSlot=" + gameSlot +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
