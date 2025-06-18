package com.lcwd.game.turf.GameTurf.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
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

//    This keeps track of which slots this player has booked.
//    A player can book multiple game slots.
//    Each game slot can be booked by multiple players (in multiplayer games).
//    So, this is a many-to-many relationship.
    @ManyToMany(mappedBy = "players", cascade = CascadeType.ALL)
    private List<GameSlot> gameSlots;

//    This links your Player to the actual login/account user.
//    The User entity stores email, password, role, name, etc.
//    The Player entity stores game-specific things like favourites, bookings, etc.
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // for creation of game in proj
    // This is a @OneToMany because:
    //  One player can create many games.
    //    But each game is created by one player.
    //    One player can create many games.
    //    But each game is created by one player.
    //    Keep this field if you want to show "games created by this player".
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id") // creates FK in Game table
    private List<Game> games;

    // there is already a gamelist from which player will select favGames
    //    These are the games this player marked as favourite.
    //    Game can be favourite for many players.
    //    Player can have many favourites. So this is @ManyToMany.
//    By initializing with new ArrayList<>(), even if there are no favourite games saved yet, the list will be empty—not null—so .stream() works fine.
    @ManyToMany
    private List<Game> favouriteGames = new ArrayList<>();

    public Player() {
    }

    public Player(String id, List<GameSlot> gameSlots, User user, List<Game> games, List<Game> favouriteGames) {
        this.id = id;
        this.gameSlots = gameSlots;
        this.user = user;
        this.games = games;
        this.favouriteGames = favouriteGames;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<GameSlot> getGameSlots() {
        return gameSlots;
    }

    public void setGameSlots(List<GameSlot> gameSlots) {
        this.gameSlots = gameSlots;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<Game> getFavouriteGames() {
        return favouriteGames;
    }

    public void setFavouriteGames(List<Game> favouriteGames) {
        this.favouriteGames = favouriteGames;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", gameSlots=" + gameSlots +
                ", user=" + user +
                ", games=" + games +
                ", favouriteGames=" + favouriteGames +
                '}';
    }
}
