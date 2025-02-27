package com.lcwd.game.turf.GameTurf.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {

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

    @Past(message = "Date of birth must be in the past")
    @NotNull(message = "Date of birth is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @OneToOne( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name= "contact_id", referencedColumnName = "id")
    private Contact contact;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name= "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameSlotPlayer> gameSlotPlayers;

    public Player(String id, String name, LocalDate dob, Contact contact, Address address, List<GameSlotPlayer> gameSlotPlayers) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.contact = contact;
        this.address = address;
        this.gameSlotPlayers = gameSlotPlayers;
    }

    public Player() {
    }

    public Player(String id, String name, LocalDate dob, Address address, Contact contact) {
        this.id=id;
        this.name=name;
        this.dob=dob;
        this.address=address;
        this.contact=contact;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Contact getContact() {
        return contact;
    }

    public Address getAddress() {
        return address;
    }

    public List<GameSlotPlayer> getGameSlotPlayers() {
        return gameSlotPlayers;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setGameSlotPlayers(List<GameSlotPlayer> gameSlotPlayers) {
        this.gameSlotPlayers = gameSlotPlayers;
    }

    // Simple Builder Method
    public static class Builder {
        private String id;
        private String name;
        private LocalDate dob;
        private Contact contact;
        private Address address;
        private List<GameSlotPlayer> gameSlotPlayers;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder dob(LocalDate dob) {
            this.dob = dob;
            return this;
        }

        public Builder contact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder gameSlotPlayers(List<GameSlotPlayer> gameSlotPlayers) {
            this.gameSlotPlayers = gameSlotPlayers;
            return this;
        }

        public Player build() {
            return new Player(id, name, dob, contact, address, gameSlotPlayers);
        }
    }



    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", contact=" + contact +
                ", address=" + address +
                ", gameSlotPlayers=" + gameSlotPlayers +
                '}';
    }


}
