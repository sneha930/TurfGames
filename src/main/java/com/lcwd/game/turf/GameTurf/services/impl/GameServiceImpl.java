package com.lcwd.game.turf.GameTurf.services.impl;

import com.lcwd.game.turf.GameTurf.dtos.*;
import com.lcwd.game.turf.GameTurf.entities.*;
import com.lcwd.game.turf.GameTurf.exceptions.ResouceNotFoundException;
import com.lcwd.game.turf.GameTurf.repositories.GameRepository;
import com.lcwd.game.turf.GameTurf.repositories.UserRepository;
import com.lcwd.game.turf.GameTurf.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private PlayerServiceImpl playerService;

    @Transactional
    @Override
    public GameDto createGame(GameDto gameDto) {
        // gameDto To Entity
        Game game = gameDtoToEntity(gameDto);


        if(!Objects.isNull(game) && !Objects.isNull(game.getGameSlots()) && !game.getGameSlots().isEmpty()) {
            game.getGameSlots().forEach(slot -> slot.setGame(game));
        }

        // Save game to database
        Game savedGame = gameRepository.save(game);
        return gameEntityToDto(savedGame);
    }

    @Override
    public List<GameDto> getAllGames() {

        List<Game> games = gameRepository.findAll();

        return games.stream()
                .map(this::gameEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public GameDto updateGame(GameDto gameDto, String gameId) {
        Game existingGame = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResouceNotFoundException("Game not found with given id"));
        existingGame.setName(gameDto.getName());
        existingGame.setDescription(gameDto.getDescription());
        List<GameSlot> updatedGameSlots = gameDto.getGameSlotDtos().stream()
                .map(this::gameSlotDtoToEntity)
                .collect(Collectors.toList());
        updatedGameSlots.forEach(slot -> slot.setGame(existingGame));
        existingGame.setGameSlots(updatedGameSlots);
        Game updatedGame = gameRepository.save(existingGame);
        return gameEntityToDto(updatedGame);
    }

    @Override
    public void deleteGame(String gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResouceNotFoundException("Game not found with given id"));
        gameRepository.delete(game);
    }

    // Manual mapping methods

    @Override
    public GameDto getGameById(String gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResouceNotFoundException("Game not found with given id"));
        return gameEntityToDto(game);
    }

//    **
    private GameDto gameEntityToDto(Game game) {
        if(!Objects.isNull(game)) {
            GameDto gameDto = new GameDto();

            gameDto.setId(game.getId());
            gameDto.setName(game.getName());
            gameDto.setDescription(game.getDescription());
            gameDto.setMinPlayers(game.getMinPlayers());
            gameDto.setMaxPlayers(game.getMaxPlayers());

            if (!Objects.isNull(game.getGameSlots()) && !game.getGameSlots().isEmpty()) {

                List<GameSlotDto> gameSlotDtos = new ArrayList<>();
                for (GameSlot gameSlot : game.getGameSlots()) {
                    gameSlotDtos.add(gameSlotToGameSlotDto(gameSlot));
                }
                gameDto.setGameSlotDtos(gameSlotDtos);
            }
            return gameDto;
        }
        return null;
    }

    private UserSignUpResponseDto userEntityToUserDto(User createdBy) {
        if (createdBy == null) return null;

        UserSignUpResponseDto dto = new UserSignUpResponseDto();
        dto.setId(createdBy.getId());
        dto.setName(createdBy.getName());
        dto.setEmailId(createdBy.getEmailId());
        dto.setDob(createdBy.getDob());
        dto.setRole(createdBy.getRole());
        dto.setContact(contactEntityToDto(createdBy.getContact()));
        dto.setAddress(addressEntityToDto(createdBy.getAddress()));
        return dto;
    }

    //    **
    private Game gameDtoToEntity(GameDto gameDto) {
        if(!Objects.isNull(gameDto)) {
            Game game = new Game();

            if(!Objects.isNull(gameDto.getGameSlotDtos()) && !gameDto.getGameSlotDtos().isEmpty()) {
                List<GameSlot> gameSlots = gameDto.getGameSlotDtos().stream()
                        .map(this::gameSlotDtoToEntity)
                        .toList();
                game.setGameSlots(gameSlots);
            }

            game.setName(gameDto.getName());
            game.setDescription(gameDto.getDescription());
            game.setMinPlayers(gameDto.getMinPlayers());
            game.setMaxPlayers(gameDto.getMaxPlayers());
            game.setCreatedAt(LocalDate.now());

            User user = userRepository.findById(gameDto.getCreatedBy().getId())
                    .orElseThrow(() -> new ResouceNotFoundException("User not found with id"));

            game.setCreatedBy(user);

            return game;
        }
        return null;
    }

//    *
    private GameSlot gameSlotDtoToEntity(GameSlotDto gameSlotDto) {
        if(!Objects.isNull(gameSlotDto)) {
            GameSlot gameSlot = new GameSlot();
            gameSlot.setSlotName(gameSlotDto.getSlotName());
            gameSlot.setStartTime(gameSlotDto.getStartTime());
            gameSlot.setEndTime(gameSlotDto.getEndTime());
            gameSlot.setBooked(gameSlotDto.isBooked());

            List<Player> players = new ArrayList<>();
            if (gameSlotDto.getPlayerDtos() != null) {
                for(PlayerDto playerDto : gameSlotDto.getPlayerDtos()){
                    players.add(playerService.dtoToEntity(playerDto));
                }
            }
            gameSlot.setPlayers(players);
            return gameSlot;
        }
        return null;
    }

    private Player playerDtoToEntity(PlayerDto playerDto) {
        Player player = new Player();
        player.setUser(UserSignUpResponseDtoToUserEntity(playerDto.getUserSignUpResponseDto()));

        return  player;
    }

    private User UserSignUpResponseDtoToUserEntity(UserSignUpResponseDto userSignUpResponseDto) {
        User user = new User();
        user.setName(userSignUpResponseDto.getName());
        user.setRole(userSignUpResponseDto.getRole());
        user.setEmailId(userSignUpResponseDto.getEmailId());
        user.setDob(userSignUpResponseDto.getDob());
        user.setAddress(addressDtoToEntity(userSignUpResponseDto.getAddress()));
        user.setContact(contactDtoToEntity(userSignUpResponseDto.getContact()));

        return user;
    }

    private Contact contactDtoToEntity(ContactDto contact) {
        Contact contact1 = new Contact();
        contact1.setPrimaryContact(contact.getPrimaryContact());
        contact1.setHomeContact(contact.getHomeContact());
        contact1.setEmergencyContact(contact.getEmergencyContact());

        return  contact1;
    }

    private Address addressDtoToEntity(AddressDto address) {
        Address address1 = new Address();
        address1.setLine1(address.getLine1());
        address1.setLine2(address.getLine2());
        address1.setCity(address.getCity());
        address1.setState(address.getState());
        address1.setCountry(address.getCountry());
        address1.setPincode(address.getPincode());

        return address1;
    }

    //    **
    private GameSlotDto gameSlotToGameSlotDto(GameSlot gameSlot) {
        if(!Objects.isNull(gameSlot)) {

            GameSlotDto dto = new GameSlotDto();
            dto.setId(gameSlot.getId());
            dto.setSlotName(gameSlot.getSlotName());
            dto.setStartTime(gameSlot.getStartTime());
            dto.setEndTime(gameSlot.getEndTime());
            dto.setBooked(gameSlot.isBooked());
            
            //dto.setGame(gameEntityToGameDto(gameSlot.getGame()));
            dto.setGame(minimalGameInfo(gameSlot.getGame()));

            
            List<PlayerDto> playerDtos = new ArrayList<>();
            if (gameSlot.getPlayers() != null) {
                for(Player player: gameSlot.getPlayers()){
                    playerDtos.add(playerService.entityToDto(player));
                }
            }
            dto.setPlayerDtos(playerDtos);

            dto.setTurfSizeDto(turfSizeEntityToDto(gameSlot.getTurfSize()));
            return dto;
        }return null;
    }

    private GameDto minimalGameInfo(Game game) {
        if (game == null) return null;
        GameDto dto = new GameDto();
        dto.setId(game.getId());
        dto.setName(game.getName());
        return dto;
    }

/*

    private GameSlotPlayerDto gameSlotPlayerEntityToDto(GameSlotPlayer gameSlotPlayer) {
        if(!Objects.isNull(gameSlotPlayer)) {
            GameSlotPlayerDto gameSlotPlayerDto = new GameSlotPlayerDto();
            gameSlotPlayerDto.setId(gameSlotPlayer.getId());
            gameSlotPlayerDto.setGameSlotDto(gameSlotEntityToDto(gameSlotPlayer.getGameSlot()));
            gameSlotPlayerDto.setPlayerDto(playerEntityToDto(gameSlotPlayer.getPlayer()));
            return gameSlotPlayerDto;
        }
        return null;
    }



    private PlayerDto playerEntityToDto(Player player) {
        if(!Objects.isNull(player)) {
            PlayerDto playerDto = new PlayerDto();
            playerDto.setId(player.getId());

            List<GameSlotPlayerDto> gameSlotPlayerDtos = new ArrayList<>();
            if (player.getGameSlotPlayers() != null) {
                gameSlotPlayerDtos = player.getGameSlotPlayers().stream()
                        .map(this::gameSlotPlayerEntityToDto)
                        .collect(Collectors.toList());
            }
            playerDto.setGameSlotPlayerDtos(gameSlotPlayerDtos);

            return playerDto;
        }
        return null;
    }
*/

    private ContactDto contactEntityToDto(Contact contact) {
        if(!Objects.isNull(contact)) {
            ContactDto contactDto = new ContactDto();

            contactDto.setId(contact.getId());
            contactDto.setPrimaryContact(contact.getPrimaryContact());
            contactDto.setEmergencyContact(contact.getEmergencyContact());
            contactDto.setHomeContact(contact.getHomeContact());

            return contactDto;
        }
        return null;
    }

    private AddressDto addressEntityToDto(Address address) {
        if(!Objects.isNull(address)) {
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
        return null;
    }

/*

    private GameSlotDto gameSlotEntityToDto(GameSlot gameSlot) {
        if(!Objects.isNull(gameSlot)) {
            GameSlotDto gameSlotDto = new GameSlotDto();
            gameSlotDto.setId(gameSlot.getId());
            gameSlotDto.setSlotName(gameSlot.getSlotName());
            gameSlotDto.setStartTime(gameSlot.getStartTime());
            gameSlotDto.setEndTime(gameSlot.getEndTime());
            gameSlotDto.setBooked(gameSlot.isBooked());

            gameSlotDto.setGame(gameEntityToDto(gameSlot.getGame()));

            List<GameSlotPlayerDto> gameSlotPlayerDtos = new ArrayList<>();
            if (gameSlot.getGameSlotPlayers() != null) {
                gameSlotPlayerDtos = gameSlot.getGameSlotPlayers().stream()
                        .map(this::gameSlotPlayerEntityToDto)
                        .collect(Collectors.toList());
            }
            gameSlotDto.setGameSlotPlayers(gameSlotPlayerDtos);

            gameSlotDto.setTurfSizeDto(turfSizeEntityToDto(gameSlot.getTurfSize()));

            return gameSlotDto;
        }
        return null;
    }
*/

    /*private TurfDto turfEntityToDto(Turf turf) {
        if(!Objects.isNull(turf)) {
            TurfDto turfDto = new TurfDto();

            turfDto.setId(turf.getId());
            turfDto.setName(turf.getName());
            turfDto.setAddress(turf.getAddress());
            turfDto.setDescription(turf.getDescription());

            List<TurfSizeDto> turfSizeDtos = new ArrayList<>();
            if (turf.getTurfSize() != null) {
                turfSizeDtos = turf.getTurfSize().stream()
                        .map(this::turfSizeEntityToDto).collect(Collectors.toList());
            }
            turfDto.setTurfSize(turfSizeDtos);

            return turfDto;
        }
        return null;
    }*/

    private TurfSizeDto turfSizeEntityToDto(TurfSize turfSize) {
        if(!Objects.isNull(turfSize)) {
            TurfSizeDto turfSizeDto = new TurfSizeDto();

            turfSizeDto.setId(turfSize.getId());
            turfSizeDto.setSizeName(turfSize.getSizeName());
            turfSizeDto.setCapacity(turfSize.getCapacity());
            //turfSizeDto.setTurf(turfEntityToDto(turfSize.getTurf()));

            return turfSizeDto;
        }
        return null;
    }

}
