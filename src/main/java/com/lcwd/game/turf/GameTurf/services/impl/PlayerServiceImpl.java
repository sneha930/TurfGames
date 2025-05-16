package com.lcwd.game.turf.GameTurf.services.impl;

import com.lcwd.game.turf.GameTurf.dtos.AddressDto;
import com.lcwd.game.turf.GameTurf.dtos.ContactDto;
import com.lcwd.game.turf.GameTurf.dtos.PlayerDto;
import com.lcwd.game.turf.GameTurf.entities.Address;
import com.lcwd.game.turf.GameTurf.entities.Contact;
import com.lcwd.game.turf.GameTurf.entities.Player;
import com.lcwd.game.turf.GameTurf.exceptions.ResouceNotFoundException;
import com.lcwd.game.turf.GameTurf.repositories.PlayerRepository;
import com.lcwd.game.turf.GameTurf.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    // create player
    @Override
    public PlayerDto createPlayer(PlayerDto playerDto) {
        //  generate unique id in string format
        //String playerId = UUID.randomUUID().toString();
        //playerDto.setId(playerId);

        // dto -> entity
        Player player = dtoToEntity(playerDto);
        Contact contact = dtoToContact(playerDto.getContact());
        player.setContact(contact);
        player.setAddress(dtoToAddress(playerDto.getAddress()));
        player = playerRepository.save(player);
        // entity -> dto
        return entityToDto(player);
    }

//    update player
    @Override
    public PlayerDto updatePlayer(PlayerDto playerDto, String playerId) {

        // get player of given id
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new ResouceNotFoundException("Player not found with given id"));

        // update player
        player.setName(playerDto.getName());
        player.setDob(playerDto.getDob());
        player.setAddress(dtoToAddress(playerDto.getAddress()));
        player.setContact(dtoToContact(playerDto.getContact()));

        Player updatedPlayer = playerRepository.save(player);

        return entityToDto(updatedPlayer);
    }

//    search player
    @Override
    public List<PlayerDto> searchPlayer(String keyword) {
        List<Player> players = playerRepository.findByNameContaining(keyword);
        List<PlayerDto> playerDtoList = players.stream().map(player -> entityToDto(player)).collect(Collectors.toList());
        return playerDtoList;
    }

    // get all players
    @Override
    public List<PlayerDto> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        List<PlayerDto> playerDtoList = players.stream()
                .map(player -> entityToDto(player))
                .toList();
        return playerDtoList;
    }

//    get player by id
    @Override
    public PlayerDto getPlayerById(String playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new ResouceNotFoundException("Player not found with given id"));
        return entityToDto(player);
    }

//    delete specific player
    public void deletePlayer(String playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new ResouceNotFoundException("Player not found with given id"));
        playerRepository.delete(player);
    }



    private PlayerDto entityToDto(Player player) {

        PlayerDto playerDto = new PlayerDto.Builder()
                .id(player.getId())
                .name(player.getName())
                .dob(player.getDob())
                .contact(contactToDto(player.getContact()))
                .address(addressToDto(player.getAddress())) // Updated
                .build();
        return playerDto;
    }

    private Player dtoToEntity(PlayerDto playerDto) {

        Player player = new Player(
                playerDto.getId(),
                playerDto.getName(), playerDto.getDob(),
                dtoToAddress(playerDto.getAddress()),dtoToContact(playerDto.getContact()));
        return player;
    }

    private ContactDto contactToDto(Contact contact) {
        if (contact == null) return null;
        return new ContactDto.Builder()
                .id(contact.getId())
                .primaryContact(contact.getPrimaryContact())
                .homeContact(contact.getHomeContact())
                .emergencyContact(contact.getEmergencyContact())
                .build();
    }

    // Convert ContactDto to Contact entity
    private Contact dtoToContact(ContactDto contactDto) {
        if (contactDto == null) {
            System.out.println("ContactDto is NULL!");
            return null;
        }
        System.out.println("1 :> "+ contactDto.primaryContact);
        System.out.println("2 :>"+contactDto.homeContact);
        System.out.println("3 :>"+ contactDto.emergencyContact);

        Contact contact = new Contact();
        contact.setEmergencyContact(contactDto.getEmergencyContact());
        contact.setHomeContact(contactDto.getHomeContact());
        contact.setPrimaryContact(contactDto.getPrimaryContact());
        return contact;
    }

    private AddressDto addressToDto(Address address) {
        if (address == null) return null;
        return new AddressDto.Builder()
                .id(address.getId())
                .line1(address.getLine1())
                .line2(address.getLine2())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .pincode(address.getPincode())
                .build();
    }

    private Address dtoToAddress(AddressDto addressDto) {
        if (addressDto == null) return null;
        return new Address.Builder()
                .id(addressDto.getId())
                .line1(addressDto.getLine1())
                .line2(addressDto.getLine2())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .country(addressDto.getCountry())
                .pincode(addressDto.getPincode())
                .build();
    }
}

