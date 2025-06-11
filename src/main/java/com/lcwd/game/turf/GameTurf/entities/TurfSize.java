package com.lcwd.game.turf.GameTurf.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "turf_size")
public class TurfSize {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @NotBlank(message = "Size is required")
    private String sizeName;

    @Positive(message = "Capacity must be a positive number")
    private int capacity;

    @OneToMany(mappedBy = "turfSize", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameSlot> gameSlot;

    @ManyToOne
    @JoinColumn(name = "turf_id")
    private Turf turf;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
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

    public Turf getTurf() {
        return turf;
    }

    public void setTurf(Turf turf) {
        this.turf = turf;
    }

    public List<GameSlot> getGameSlot() {
        return gameSlot;
    }

    public void setGameSlot(List<GameSlot> gameSlot) {
        this.gameSlot = gameSlot;
    }

    public TurfSize() {
    }

    public TurfSize(String id, String sizeName, int capacity, List<GameSlot> gameSlot, Turf turf) {
        this.id = id;
        this.sizeName = sizeName;
        this.capacity = capacity;
        this.gameSlot = gameSlot;
        this.turf = turf;
    }

    @Override
    public String toString() {
        return "TurfSize{" +
                "id='" + id + '\'' +
                ", sizeName='" + sizeName + '\'' +
                ", capacity=" + capacity +
                ", gameSlot=" + gameSlot +
                ", turf=" + turf +
                '}';
    }
}
