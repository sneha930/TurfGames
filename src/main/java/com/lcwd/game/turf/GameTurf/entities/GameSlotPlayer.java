package com.lcwd.game.turf.GameTurf.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "game_slot_players")
public class GameSlotPlayer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @ManyToOne
    @JoinColumn(name = "game_slot_id", nullable = false)
    private GameSlot gameSlot;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    public GameSlotPlayer() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GameSlot getGameSlot() {
        return gameSlot;
    }

    public void setGameSlot(GameSlot gameSlot) {
        this.gameSlot = gameSlot;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "GameSlotPlayer{" +
                "id='" + id + '\'' +
                ", gameSlot=" + gameSlot +
                ", player=" + player +
                '}';
    }
}
