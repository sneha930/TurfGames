package com.lcwd.game.turf.GameTurf.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "game_slots")
public class GameSlot {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @NotBlank(message = "Slot name cannot be empty")
    private String slotName;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    private boolean isBooked;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToMany
    @JoinTable(
            name = "game_slot_players",
            joinColumns = @JoinColumn(name = "game_slot_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> players;

    @ManyToOne
    @JoinColumn(name = "turfSize_id", nullable = true)
    private TurfSize turfSize;

    public GameSlot() {
    }

    public GameSlot(String id, String slotName, LocalTime startTime, LocalTime endTime, boolean isBooked, Game game, List<Player> players, TurfSize turfSize) {
        this.id = id;
        this.slotName = slotName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isBooked = isBooked;
        this.game = game;
        this.players = players;
        this.turfSize = turfSize;
    }

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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public TurfSize getTurfSize() {
        return turfSize;
    }

    public void setTurfSize(TurfSize turfSize) {
        this.turfSize = turfSize;
    }

    @Override
    public String toString() {
        return "GameSlot{" +
                "id='" + id + '\'' +
                ", slotName='" + slotName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isBooked=" + isBooked +
                ", game=" + game +
                ", players=" + players +
                ", turfSize=" + turfSize +
                '}';
    }
}