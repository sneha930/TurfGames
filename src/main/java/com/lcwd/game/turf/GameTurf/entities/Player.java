package com.lcwd.game.turf.GameTurf.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @ManyToMany(mappedBy = "players", cascade = CascadeType.ALL)
    private List<GameSlot> gameSlots;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /*@OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Game> games;*/

    public Player() {
    }

    public Player(String id, List<GameSlot> gameSlots, User user) {
        this.id = id;
        this.gameSlots = gameSlots;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<GameSlot> getGameSlots() {
        return gameSlots;
    }

    public void setGameSlots(List<GameSlot> gameSlots) {
        this.gameSlots = gameSlots;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", gameSlotPlayers=" + gameSlots +
                ", user=" + user +
                '}';
    }
}
