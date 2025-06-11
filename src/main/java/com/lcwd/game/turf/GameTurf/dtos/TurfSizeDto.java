package com.lcwd.game.turf.GameTurf.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class TurfSizeDto {
    public String id;

    @NotBlank(message = "Size is required")
    public String sizeName;

    @Positive(message = "Capacity must be a positive number")
    public int capacity;

    public TurfDto turf;

    public List<GameSlotDto> gameSlotDto;

    public TurfSizeDto() {
    }

    public TurfSizeDto(String id, String sizeName, int capacity, TurfDto turf, List<GameSlotDto> gameSlotDto) {
        this.id = id;
        this.sizeName = sizeName;
        this.capacity = capacity;
        this.turf = turf;
        this.gameSlotDto = gameSlotDto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public TurfDto getTurf() {
        return turf;
    }

    public void setTurf(TurfDto turf) {
        this.turf = turf;
    }

    public List<GameSlotDto> getGameSlotDto() {
        return gameSlotDto;
    }

    public void setGameSlotDto(List<GameSlotDto> gameSlotDto) {
        this.gameSlotDto = gameSlotDto;
    }

    @Override
    public String toString() {
        return "TurfSizeDto{" +
                "id='" + id + '\'' +
                ", sizeName='" + sizeName + '\'' +
                ", capacity=" + capacity +
                ", turf=" + turf +
                ", gameSlotDto=" + gameSlotDto +
                '}';
    }

    // Builder Class
    public static class Builder {
        private String id;
        private String sizeName;
        private int capacity;
        private TurfDto turf;
        private List<GameSlotDto> gameSlotDto;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder sizeName(String sizeName) {
            this.sizeName = sizeName;
            return this;
        }

        public Builder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder turf(TurfDto turf) {
            this.turf = turf;
            return this;
        }

        public Builder gameSlotDto(List<GameSlotDto> gameSlotDto) {
            this.gameSlotDto = gameSlotDto;
            return this;
        }

        public TurfSizeDto build() {
            return new TurfSizeDto(id, sizeName, capacity, turf, gameSlotDto);
        }
    }
}
