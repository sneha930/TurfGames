package com.lcwd.game.turf.GameTurf.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import java.util.List;

@Entity
@Table(name = "turf")
public class Turf {

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

    private String address;

    private String description;

    @OneToMany(mappedBy = "turf", cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<TurfSize> turfSize;

    public Turf() {
    }

    public Turf(String id, String name, String address, String description, List<TurfSize> turfSize) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.turfSize = turfSize;
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

    public List<TurfSize> getTurfSize() {
        return turfSize;
    }

    public void setTurfSize(List<TurfSize> turfSize) {
        this.turfSize = turfSize;
    }

    @Override
    public String toString() {
        return "Turf{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", turfSize=" + turfSize +
                '}';
    }
}
