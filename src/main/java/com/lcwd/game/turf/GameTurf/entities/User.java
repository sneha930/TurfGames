package com.lcwd.game.turf.GameTurf.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

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

    @NotBlank(message = "Email is required")
    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;

    @Past(message = "Date of birth must be in the past")
    @NotNull(message = "Date of birth is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob;

//    A user has exactly one contact.
//    This is a foreign key: contact_id is stored in the users table.
//    Cascade + orphan removal: If user is deleted or contact is changed, the associated contact row is deleted/updated automatically.
    @OneToOne( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name= "contact_id", referencedColumnName = "id")
    @NotNull(message = "Contact is required")
    private Contact contact;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name= "address_id", referencedColumnName = "id")
    private Address address;

    @Column(nullable = false)
    private String password;

//    This means: one user can create multiple games.
    @OneToMany(mappedBy = "createdBy")
    private List<Game> gamesCreated;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role {
        ADMIN, PLAYER, PLAYERADMIN
    }

    public User() {
    }

    public User(String id, String name, String emailId, LocalDate dob, Contact contact, Address address, String password, Role role) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.dob = dob;
        this.contact = contact;
        this.address = address;
        this.password = password;
        this.role = role;
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", dob=" + dob +
                ", contact=" + contact +
                ", address=" + address +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
