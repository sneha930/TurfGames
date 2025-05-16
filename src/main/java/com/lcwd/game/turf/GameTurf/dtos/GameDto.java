package com.lcwd.game.turf.GameTurf.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public class GameDto {

    public String id;

    @NotBlank(message = "Game name cannot be empty")
    @Size(min = 3, max = 50, message = "Game name must be between 3 and 50 characters")
    public String name;

    @NotBlank(message = "Game description cannot be empty")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    public String description;

    // Bidirectional Mapping
    @NotNull(message = "GameSlot is required")
    public List<GameSlotDto> gameSlots;

    public GameDto() {
    }

    public GameDto(String id, String name, String description, List<GameSlotDto> gameSlots) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gameSlots = gameSlots;
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

    public List<GameSlotDto> getGameSlots() {
        return gameSlots;
    }

    public void setGameSlots(List<GameSlotDto> gameSlots) {
        this.gameSlots = gameSlots;
    }


    @Override
    public String toString() {
        return "GameDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", gameSlots=" + gameSlots +
                '}';
    }

//    Manual builder class

    public static class Builder {
        private String id;
        private String name;
        private String description;
        private List<GameSlotDto> gameSlots;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setGameSlots(List<GameSlotDto> gameSlots) {
            this.gameSlots = gameSlots;
            return this;
        }

        public GameDto build() {
            return new GameDto(id, name, description, gameSlots);
        }
    }

}
