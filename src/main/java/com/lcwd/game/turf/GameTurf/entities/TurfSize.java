package com.lcwd.game.turf.GameTurf.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.GenericGenerator;

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

    public TurfSize() {
    }

    public TurfSize(String id, String sizeName, int capacity, Turf turf) {
        id = id;
        this.sizeName = sizeName;
        this.capacity = capacity;
        this.turf = turf;
    }

    @Override
    public String toString() {
        return "TurfSize{" +
                "id='" + id + '\'' +
                ", sizeName='" + sizeName + '\'' +
                ", capacity=" + capacity +
                ", turf=" + turf +
                '}';
    }
}
