package com.lcwd.game.turf.GameTurf.services.impl;

import com.lcwd.game.turf.GameTurf.dtos.*;
import com.lcwd.game.turf.GameTurf.entities.Address;
import com.lcwd.game.turf.GameTurf.entities.Contact;
import com.lcwd.game.turf.GameTurf.entities.Player;
import com.lcwd.game.turf.GameTurf.entities.User;
import com.lcwd.game.turf.GameTurf.repositories.PlayerRepository;
import com.lcwd.game.turf.GameTurf.repositories.UserRepository;
import com.lcwd.game.turf.GameTurf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlayerRepository playerRepository;

    //    signup
    @Override
    public UserSignUpResponseDto signup(UserSignUpRequestDto userSignUpRequestDto) {

        User.Role role = userSignUpRequestDto.getRole();

        // Convert DTO to User entity
        User user = UserSignUpRequestDtoToEntity(userSignUpRequestDto);

        // Case 1: ADMIN only
        if(role == User.Role.ADMIN) {
            user = userRepository.save(user); // Save only user
        }

        // Case 2: PLAYERADMIN
        else if (role == User.Role.PLAYERADMIN) {
            user = userRepository.save(user); // Save user first

            Player player1 = new Player();
            player1.setUser(user);
            // games and gameSlotPlayers will be empty initially
            playerRepository.save(player1);
        }

        // Case 3: PLAYER only
        else if (role == User.Role.PLAYER) {
            user = userRepository.save(user); // Save user first

            Player player = new Player();
            player.setUser(user);
            // games and gameSlotPlayers will be empty initially
            playerRepository.save(player);
        }

        return entityToUserSignUpResponseDto(user);
    }

    private UserSignUpResponseDto entityToUserSignUpResponseDto(User user) {
        UserSignUpResponseDto userSignUpResponseDto = new UserSignUpResponseDto();
        userSignUpResponseDto.setId(user.getId());
        userSignUpResponseDto.setName(user.getName());
        userSignUpResponseDto.setEmailId(user.getEmailId());
        userSignUpResponseDto.setDob(user.getDob());
        userSignUpResponseDto.setRole(user.getRole());

        Player player = playerRepository.findPlayerByUserId(user.getId()).orElseThrow(() -> new RuntimeException("Player not found with given userId"));
        if(player != null) {
            userSignUpResponseDto.setPlayerDto(entityToPlayerDto(player));
        }

        userSignUpResponseDto.setAddress(entityToAddressDto(user.getAddress()));
        userSignUpResponseDto.setContact(entityToContactDto(user.getContact()));

        return  userSignUpResponseDto;
    }

    private PlayerDto entityToPlayerDto(Player player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(player.getId());

        return playerDto;
    }

    private ContactDto entityToContactDto(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setId(contact.getId());
        contactDto.setHomeContact(contact.getHomeContact());
        contactDto.setPrimaryContact(contact.getPrimaryContact());
        contactDto.setEmergencyContact(contact.getEmergencyContact());

        return  contactDto;
    }

    private AddressDto entityToAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setLine1(address.getLine1());
        addressDto.setLine2(address.getLine2());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setCountry(address.getCountry());
        addressDto.setPincode(address.getPincode());

        return addressDto;
    }

    private User UserSignUpRequestDtoToEntity(UserSignUpRequestDto userSignUpRequestDto) {
        User user = new User();
        user.setName(userSignUpRequestDto.getName());
        user.setEmailId(userSignUpRequestDto.getEmailId());
        user.setPassword(userSignUpRequestDto.getPassword());
        user.setRole(userSignUpRequestDto.getRole());
        user.setDob(userSignUpRequestDto.getDob());
        user.setAddress(addressDtoToEntity(userSignUpRequestDto.getAddressDto()));
        user.setContact(contactDtoToEntity(userSignUpRequestDto.getContactDto()));

        return user;
    }

    private Contact contactDtoToEntity(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setPrimaryContact(contactDto.getPrimaryContact());
        contact.setHomeContact(contactDto.getHomeContact());
        contact.setEmergencyContact(contactDto.getEmergencyContact());

        return contact;
    }

    private Address addressDtoToEntity(AddressDto addressDto) {
        Address address = new Address();
        address.setLine1(addressDto.getLine1());
        address.setLine2(addressDto.getLine2());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());
        address.setPincode(addressDto.getPincode());

        return address;
    }

    //signin
    @Override
    public UserSignUpResponseDto signin(UserSignInRequestDto userSignInRequestDto) {
        User user = userRepository.findByEmailId(userSignInRequestDto.getEmailId()).orElseThrow(() -> new RuntimeException("User not found with email: " + userSignInRequestDto.getEmailId()));
        if(!user.getPassword().equals(userSignInRequestDto.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }

        return entityToUserSignUpResponseDto(user);
    }

    @Override
    public List<UserSignUpResponseDto> getUsersByRole() {
        // Only these two roles should be shown
        List<User.Role> allowedRoles = List.of(
                User.Role.PLAYER,
                User.Role.PLAYERADMIN   // <-- make sure the enum name is correct!
        );

        List<User> users = userRepository.findByRoleIn(allowedRoles);

        return users.stream()
                .map(this::entityToUserSignUpResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserSignUpResponseDto getUserByEmailId(String emailId) {
        User user = userRepository.findByEmailId(emailId).orElseThrow(() -> new RuntimeException("User not found with emailId: "+ emailId));
        return entityToUserSignUpResponseDto(user);
    }


    // getAllUsers
    @Override
    public List<UserSignUpResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserSignUpResponseDto> userSignUpResponseDtos = users.stream()
                .map(this::entityToUserSignUpResponseDto)
                .collect(Collectors.toList());

        return userSignUpResponseDtos;
    }

    private UserSignInResponseDto entityToUserSignInResponseDto(User user) {
        UserSignInResponseDto userSignInResponseDto = new UserSignInResponseDto();
        userSignInResponseDto.setId(user.getId());
        userSignInResponseDto.setName(user.getName());
        userSignInResponseDto.setEmailId(user.getEmailId());
        userSignInResponseDto.setRole(user.getRole());

        return  userSignInResponseDto;
    }

}
