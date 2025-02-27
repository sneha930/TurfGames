package com.lcwd.game.turf.GameTurf.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "game_slot_players")
public class GameSlotPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "game_slot_id", nullable = false)
    private GameSlot gameSlot;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;
}
