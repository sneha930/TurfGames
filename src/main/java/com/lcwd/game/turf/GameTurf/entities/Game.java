package com.lcwd.game.turf.GameTurf.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
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

    private int minPlayers;

    private int maxPlayers;

    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)  // DB column name to link to user ID
    @JsonIgnore
    private User createdBy;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }

    public Game() {
    }

    public Game(String id, String name, String description, List<GameSlot> gameSlots, int minPlayers, int maxPlayers, LocalDate createdAt, User createdBy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gameSlots = gameSlots;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
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

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", gameSlots=" + gameSlots +
                ", minPlayers=" + minPlayers +
                ", maxPlayers=" + maxPlayers +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
