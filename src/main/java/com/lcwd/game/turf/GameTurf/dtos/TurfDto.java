package com.lcwd.game.turf.GameTurf.dtos;

import com.lcwd.game.turf.GameTurf.entities.GameSlot;
import com.lcwd.game.turf.GameTurf.entities.TurfSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class TurfDto {

    public String id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 to 50 characters")
    public String name;

    public String address;

    public String description;

    public List<TurfSizeDto> turfSize;

    public  List<GameSlotDto> gameSlot;

    public TurfDto() {
    }

    public TurfDto(String id, String name, String address, String description, List<TurfSizeDto> turfSize, List<GameSlotDto> gameSlot) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.turfSize = turfSize;
        this.gameSlot = gameSlot;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TurfSizeDto> getTurfSize() {
        return turfSize;
    }

    public void setTurfSize(List<TurfSizeDto> turfSize) {
        this.turfSize = turfSize;
    }

    public List<GameSlotDto> getGameSlot() {
        return gameSlot;
    }

    public void setGameSlot(List<GameSlotDto> gameSlot) {
        this.gameSlot = gameSlot;
    }

    @Override
    public String toString() {
        return "TurfDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", turfSize=" + turfSize +
                ", gameSlot=" + gameSlot +
                '}';
    }

    // Builder Class
    public static class Builder {
        private String id;
        private String name;
        private String address;
        private String description;
        private List<TurfSizeDto> turfSize;
        private List<GameSlotDto> gameSlot;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder turfSize(List<TurfSizeDto> turfSize) {
            this.turfSize = turfSize;
            return this;
        }

        public Builder gameSlot(List<GameSlotDto> gameSlot) {
            this.gameSlot = gameSlot;
            return this;
        }

        public TurfDto build() {
            return new TurfDto(id, name, address, description, turfSize, gameSlot);
        }
    }
}
