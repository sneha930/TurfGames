package com.lcwd.game.turf.GameTurf.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class TurfSizeDto {
    public String Id;

    @NotBlank(message = "Size is required")
    public String sizeName;

    @Positive(message = "Capacity must be a positive number")
    public int capacity;

    public TurfDto turf;

    public TurfSizeDto() {
    }

    public TurfSizeDto(String id, String sizeName, int capacity, TurfDto turf) {
        id = id;
        this.sizeName = sizeName;
        this.capacity = capacity;
        this.turf = turf;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    @Override
    public String toString() {
        return "TurfSizeDto{" +
                "Id='" + Id + '\'' +
                ", sizeName='" + sizeName + '\'' +
                ", capacity=" + capacity +
                ", turf=" + turf +
                '}';
    }

    // Builder Class
    public static class Builder {
        private String id;
        private String sizeName;
        private int capacity;
        private TurfDto turf;

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

        public TurfSizeDto build() {
            return new TurfSizeDto(id, sizeName, capacity, turf);
        }
    }
}
