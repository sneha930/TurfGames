package com.lcwd.game.turf.GameTurf.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "game_slots")
public class GameSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String slotName;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isBooked;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @OneToMany(mappedBy = "gameSlot", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameSlotPlayer> gameSlotPlayers;  // Many-to-Many through GameSlotPlayer
}
