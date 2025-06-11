package com.lcwd.game.turf.GameTurf.dtos;

import com.lcwd.game.turf.GameTurf.entities.GameSlot;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class GameDto {

    public String id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 to 50 characters")
    public String name;

    @NotBlank(message = "Description is required")
    @Size(min = 2, max = 200)
    public String description;

    public List<GameSlotDto> gameSlotDtos;

    public int minPlayers;

    public int maxPlayers;

    public LocalDate createdAt;

    public UserSignUpResponseDto createdBy;

    public GameDto() {
    }

    public GameDto(String id, String name, String description, List<GameSlotDto> gameSlotDtos, int minPlayers, int maxPlayers, LocalDate createdAt, UserSignUpResponseDto createdBy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gameSlotDtos = gameSlotDtos;
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

    public List<GameSlotDto> getGameSlotDtos() {
        return gameSlotDtos;
    }

    public void setGameSlotDtos(List<GameSlotDto> gameSlotDtos) {
        this.gameSlotDtos = gameSlotDtos;
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

    public UserSignUpResponseDto getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserSignUpResponseDto createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "GameDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", gameSlotDtos=" + gameSlotDtos +
                ", minPlayers=" + minPlayers +
                ", maxPlayers=" + maxPlayers +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
