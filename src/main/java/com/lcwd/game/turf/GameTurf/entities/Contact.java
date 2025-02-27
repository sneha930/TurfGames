package com.lcwd.game.turf.GameTurf.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Contacts")
public class Contact {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    private String primaryContact;

    private String homeContact;

    private String emergencyContact;

    @OneToOne(mappedBy = "contact")
    private Player player;

    public Contact(String id, String primaryContact, String homeContact, String emergencyContact, Player player) {
        this.id = id;
        this.primaryContact = primaryContact;
        this.homeContact = homeContact;
        this.emergencyContact = emergencyContact;
        this.player = player;
    }

    public Contact() {
    }

    public Contact(String id, String primaryContact, String homeContact, String emergencyContact) {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(String primaryContact) {
        this.primaryContact = primaryContact;
    }

    public String getHomeContact() {
        return homeContact;
    }

    public void setHomeContact(String homeContact) {
        this.homeContact = homeContact;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    // Add a static Builder class
    public static class Builder {
        private String id;
        private String primaryContact;
        private String homeContact;
        private String emergencyContact;
        private Player player;

        public Builder id(String id) {
            this.id=id;
            return this;
        }

        public Builder primaryContact(String primaryContact) {
            this.primaryContact = primaryContact;
            return this;
        }

        public Builder homeContact(String homeContact) {
            this.homeContact = homeContact;
            return this;
        }

        public Builder emergencyContact(String emergencyContact) {
            this.emergencyContact = emergencyContact;
            return this;
        }

        public Builder player(Player player) {
            this.player = player;
            return this;
        }

        public Contact build() {
            return new Contact(id, primaryContact, homeContact, emergencyContact);
        }
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", primaryContact='" + primaryContact + '\'' +
                ", homeContact='" + homeContact + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", player=" + player +
                '}';
    }
}
