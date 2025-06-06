package com.lcwd.game.turf.GameTurf.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "Games")
public class Game {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 to 50 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 2, max = 200)
    private String description;

    // Bidirectional Mapping
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<GameSlot> gameSlots;

    public Game(String id, String name, String description, List<GameSlot> gameSlots) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gameSlots = gameSlots;
    }

    public Game() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<GameSlot> getGameSlots() {
        return gameSlots;
    }

    public void setGameSlots(List<GameSlot> gameSlots) {
        this.gameSlots = gameSlots;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", gameSlots=" + gameSlots +
                '}';
    }
}
