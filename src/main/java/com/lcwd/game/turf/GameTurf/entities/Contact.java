package com.lcwd.game.turf.GameTurf.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private User user;

    public Contact(String id, String primaryContact, String homeContact, String emergencyContact, User user) {
        this.id = id;
        this.primaryContact = primaryContact;
        this.homeContact = homeContact;
        this.emergencyContact = emergencyContact;
        this.user = user;
    }

    public Contact() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // static Builder class
    public static class Builder {
        private String id;
        private String primaryContact;
        private String homeContact;
        private String emergencyContact;
        private User user;

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

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Contact build() {
            return new Contact(id, primaryContact, homeContact, emergencyContact, user);
        }
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", primaryContact='" + primaryContact + '\'' +
                ", homeContact='" + homeContact + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", user=" + user +
                '}';
    }
}
