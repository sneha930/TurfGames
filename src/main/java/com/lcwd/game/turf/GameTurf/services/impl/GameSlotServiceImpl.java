package com.lcwd.game.turf.GameTurf.services.impl;

import com.lcwd.game.turf.GameTurf.dtos.*;
import com.lcwd.game.turf.GameTurf.entities.*;
import com.lcwd.game.turf.GameTurf.repositories.GameRepository;
import com.lcwd.game.turf.GameTurf.repositories.GameSlotRepository;
import com.lcwd.game.turf.GameTurf.repositories.TurfSizeRepository;
import com.lcwd.game.turf.GameTurf.services.GameSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service
public class GameSlotServiceImpl implements GameSlotService {

    @Autowired
    GameSlotRepository gameSlotRepository;

    @Autowired
    PlayerServiceImpl playerServiceImpl;

    @Autowired
    GameRepository gameRespository;

    @Autowired
    TurfSizeRepository turfSizeRepository;

//    create game_slot
    @Override
    public GameSlotDto createGameSlot(GameSlotDto gameSlotDto) {

//        Fetch Game and TurfSize by their IDs from DB
        Optional<Game> gameOptional = gameRespository.findById(gameSlotDto.getGame().getId());
        Optional<TurfSize> turfSizeOptional = turfSizeRepository.findById(gameSlotDto.getTurfSizeDto().getId());

        GameSlot gameSlot = gameSlotDtoToEntity(gameSlotDto);

//        Set the game and turfSize.
        gameSlot.setTurfSize(turfSizeOptional.get());
        gameSlot.setGame(gameOptional.get());

        GameSlot savedGameSlot = (GameSlot) gameSlotRepository.save(gameSlot);
        return gameSlotEntityToDto(savedGameSlot);
    }

    // update game slot
    @Override
    public GameSlotDto updateGameSlot(GameSlotDto gameSlotDto) {
//        Fetch existing GameSlot by ID.
        GameSlot gameSlot = gameSlotRepository.findById(gameSlotDto.getId()).orElseThrow(() -> new RuntimeException("GameSlot not found with ID: " + gameSlotDto.getId()));

        // Set Players ✅
        List<Player> updatedPlayers = new ArrayList<>();

//        check If players are passed
        if (gameSlotDto.getPlayerDtos() != null) {
//            check for player IDs
            for (PlayerDto playerDto : gameSlotDto.getPlayerDtos()) {
                if (playerDto.getId() == null) {
                    throw new RuntimeException("Player ID is missing in request");
                }

                // check if player present with given id
                Optional<Player> playerOpt = playerServiceImpl.getById(playerDto.getId());
                if (playerOpt.isEmpty()) {
                    throw new RuntimeException("Player not found with ID: " + playerDto.getId());
                }

//                add player in updated player list that we created
                updatedPlayers.add(playerOpt.get());
            }
        }
        if(!Objects.isNull(gameSlot.getPlayers()) && !gameSlot.getPlayers().isEmpty()){
            for(Player player: gameSlot.getPlayers()){
                ListIterator<Player> iterator = updatedPlayers.listIterator();
                while(iterator.hasNext()){
                    if(iterator.next().getId().equals(player.getId())){
                        iterator.remove();
                    }
                }
            }
            gameSlot.getPlayers().addAll(updatedPlayers);
        }else{
            gameSlot.setPlayers(updatedPlayers);
        }

        GameSlot savedGameSlot = gameSlotRepository.save(gameSlot);
        return gameSlotEntityToDto(savedGameSlot);
    }


    //    get all game slots
    @Override
    public List<GameSlotDto> getAllGameSlots() {
        List<GameSlot> gameSlots = gameSlotRepository.findAll();

        return gameSlots.stream().map(this::gameSlotEntityToDto)
                .collect(Collectors.toList());
    }

    private GameSlotDto gameSlotEntityToDto(GameSlot savedGameSlot) {
        GameSlotDto gameSlotDto = new GameSlotDto();
        gameSlotDto.setId(savedGameSlot.getId());
        gameSlotDto.setSlotName(savedGameSlot.getSlotName());
        gameSlotDto.setStartTime(savedGameSlot.getStartTime());
        gameSlotDto.setEndTime(savedGameSlot.getEndTime());
        gameSlotDto.setDate(savedGameSlot.getDate());
        gameSlotDto.setBooked(savedGameSlot.isBooked());

        /*gameSlotDto.setGame(gameEntityToDto(savedGameSlot.getGame()));*/
        // new code to avoid recursion
        GameDto gameDto = new GameDto();
        gameDto.setId(savedGameSlot.getGame().getId());
        gameDto.setName(savedGameSlot.getGame().getName());
        gameDto.setDescription(savedGameSlot.getGame().getDescription());
        gameDto.setMinPlayers(savedGameSlot.getGame().getMinPlayers());
        gameDto.setMaxPlayers(savedGameSlot.getGame().getMaxPlayers());

        gameSlotDto.setGame(gameDto);

        if(!Objects.isNull(savedGameSlot.getPlayers()) && !savedGameSlot.getPlayers().isEmpty()) {
            List<PlayerDto> playerDtos = new ArrayList<>();
            for(Player player : savedGameSlot.getPlayers()){
                playerDtos.add(playerServiceImpl.entityToDto(player));
            }

            gameSlotDto.setPlayerDtos(playerDtos);
        }

        gameSlotDto.setTurfSizeDto(turfSizeEntityToDto(savedGameSlot.getTurfSize()));

        return gameSlotDto;
    }

    private TurfSizeDto turfSizeEntityToDto(TurfSize turfSize) {
        TurfSizeDto turfSizeDto = new TurfSizeDto();
        turfSizeDto.setId(turfSize.getId());
        turfSizeDto.setSizeName(turfSize.getSizeName());
        turfSizeDto.setCapacity(turfSize.getCapacity());

        /*turfSizeDto.setTurf(turfEntityToDto(turfSize.getTurf()));*/
        // new code to avoid recursion
        TurfDto turfDto = new TurfDto();
        turfDto.setId(turfSize.getTurf().getId());
        turfDto.setName(turfSize.getTurf().getName());
        turfDto.setDescription(turfSize.getTurf().getDescription());
        turfDto.setAddress(turfSize.getTurf().getAddress());
        turfSizeDto.setTurf(turfDto);

        /*List<GameSlotDto> gameSlotDtos = new ArrayList<>();
        gameSlotDtos = turfSize.getGameSlot().stream()
                        .map(this::gameSlotEntityToDto)
                                .collect(Collectors.toList());
        turfSizeDto.setGameSlotDto(gameSlotDtos);*/

        return turfSizeDto;
    }

    private TurfDto turfEntityToDto(Turf turf) {
        TurfDto turfDto = new TurfDto();
        turfDto.setId(turf.getId());
        turfDto.setName(turf.getName());
        turfDto.setDescription(turf.getDescription());
        turfDto.setAddress(turf.getAddress());

        List<TurfSizeDto> turfSizeDtos = new ArrayList<>();
        turfSizeDtos = turf.getTurfSize().stream()
                        .map(this::turfSizeEntityToDto)
                                .collect(Collectors.toList());
        turfDto.setTurfSize(turfSizeDtos);

        return turfDto;
    }

    private PlayerDto playerEntityToDto(Player player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(player.getId());

        /*List<GameSlotPlayerDto> gameSlotPlayerDtos = player.getGameSlotPlayers()
                        .stream().map(this::gameSlotPlayerEntityToDto)
                        .collect(Collectors.toList());
        playerDto.setGameSlotPlayerDtos(gameSlotPlayerDtos);*/

        playerDto.setUserSignUpResponseDto(userEntityToUserSignUpResponseDto(player.getUser()));

        return  playerDto;
    }

    private UserSignUpResponseDto userEntityToUserSignUpResponseDto(User user) {
        UserSignUpResponseDto userSignUpResponseDto = new UserSignUpResponseDto();
        userSignUpResponseDto.setId(user.getId());
        userSignUpResponseDto.setName(user.getName());
        userSignUpResponseDto.setEmailId(user.getEmailId());
        userSignUpResponseDto.setRole(user.getRole());
        userSignUpResponseDto.setDob(user.getDob());
        userSignUpResponseDto.setAddress(addressEntityToDto(user.getAddress()));
        userSignUpResponseDto.setContact(contactEntityToDto(user.getContact()));

        return userSignUpResponseDto;
    }

    private ContactDto contactEntityToDto(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setId(contact.getId());
        contactDto.setPrimaryContact(contact.getPrimaryContact());
        contactDto.setHomeContact(contact.getHomeContact());
        contactDto.setEmergencyContact(contact.getEmergencyContact());

        return  contactDto;
    }

    private AddressDto addressEntityToDto(Address address) {
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

    private GameDto gameEntityToDto(Game game) {
        GameDto gameDto = new GameDto();
        gameDto.setId(game.getId());
        gameDto.setName(game.getName());
        gameDto.setDescription(game.getDescription());

        List<GameSlotDto> gameSlotDtos = new ArrayList<>();
        gameSlotDtos = game.getGameSlots().stream()
                        .map(this::gameSlotEntityToDto)
                                .collect(Collectors.toList());
        gameDto.setGameSlotDtos(gameSlotDtos);

        return gameDto;
    }

    private GameSlot gameSlotDtoToEntity(GameSlotDto gameSlotDto) {
        GameSlot gameSlot = new GameSlot();
        gameSlot.setSlotName(gameSlotDto.getSlotName());
        gameSlot.setStartTime(gameSlotDto.getStartTime());
        gameSlot.setEndTime(gameSlotDto.getEndTime());
        gameSlot.setDate(gameSlotDto.getDate());
        gameSlot.setBooked(gameSlotDto.isBooked());
        gameSlot.setGame(gameDtoToEntity(gameSlotDto.getGame()));

        if(!Objects.isNull(gameSlotDto.getPlayerDtos()) && !gameSlotDto.getPlayerDtos().isEmpty()) {
            List<Player> gameSlotPlayers = new ArrayList<>();

                for (PlayerDto playerDto : gameSlotDto.getPlayerDtos()) {
                    Optional<Player> playerOpt = playerServiceImpl.getById(playerDto.getId());
                    playerOpt.ifPresent(gameSlotPlayers::add);  // Add only if present
                }

                gameSlot.setPlayers(gameSlotPlayers);   // Assign saved players only

        }

        /*if (gameSlotDto.getPlayerDtos() != null && !gameSlotDto.getPlayerDtos().isEmpty()) {
            List<Player> gameSlotPlayers = gameSlotDto.getPlayerDtos().stream()
                    .map(playerDto -> playerServiceImpl.getById(playerDto.getId()))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());

            gameSlot.setPlayers(gameSlotPlayers);
        }*/

        gameSlot.setTurfSize(turfSizeDtoToEntity(gameSlotDto.getTurfSizeDto()));

        return gameSlot;
    }

    private TurfSize turfSizeDtoToEntity(TurfSizeDto turfSizeDto) {
        if(!Objects.isNull(turfSizeDto)) {
            TurfSize turfSize = new TurfSize();
            turfSize.setSizeName(turfSizeDto.getSizeName());
            turfSize.setCapacity(turfSizeDto.getCapacity());
            turfSize.setCapacity(turfSizeDto.getCapacity());
            turfSize.setTurf(turfDtoToEntity(turfSizeDto.getTurf()));

            return turfSize;
        }
        return null;
    }

    private Turf turfDtoToEntity(TurfDto turf) {
        if(!Objects.isNull(turf)) {
            Turf turf1 = new Turf();
            turf1.setName(turf.getName());
            turf1.setDescription(turf.getDescription());
            turf1.setAddress(turf.getAddress());

            List<TurfSize> turfSizes = new ArrayList<>();
            if(!Objects.isNull(turf) && !Objects.isNull(turf.getTurfSize()) && !turf.getTurfSize().isEmpty()) {
                turfSizes = turf.getTurfSize().stream()
                        .map(this::turfSizeDtoToEntity)
                        .collect(Collectors.toList());
            }
            turf1.setTurfSize(turfSizes);

            return turf1;
        }
        return null;
    }


    private Player playerDtoToEntity(PlayerDto player) {
        Player player1 = new Player();
        player1.setUser(userSignUpResponseDtoToUserEntity(player.getUserSignUpResponseDto()));

        if(!Objects.isNull(player.getGameSlotDtos()) && ! player.getGameSlotDtos().isEmpty()) {
            List<GameSlot> gameSlotPlayers = player.getGameSlotDtos().stream()
                    .map(this::gameSlotDtoToEntity).collect(Collectors.toList());
            player1.setGameSlots(gameSlotPlayers);
        }
        return  player1;
    }

    private User userSignUpResponseDtoToUserEntity(UserSignUpResponseDto userSignUpResponseDto) {
        User user = new User();
        user.setName(userSignUpResponseDto.getName());
        user.setEmailId(userSignUpResponseDto.getEmailId());
        user.setRole(userSignUpResponseDto.getRole());
        user.setDob(userSignUpResponseDto.getDob());
        user.setAddress(addressDtoToEntity(userSignUpResponseDto.getAddress()));
        user.setContact(contactDtoToEntity(userSignUpResponseDto.getContact()));

        return  user;
    }

    private Address addressDtoToEntity(AddressDto address) {
        Address address1 = new Address();
        address1.setLine1(address.getLine1());
        address1.setLine2(address.getLine2());
        address1.setCity(address.getCity());
        address1.setState(address.getState());
        address1.setCountry(address.getCountry());
        address1.setPincode(address.getPincode());

        return  address1;
    }

    private Contact contactDtoToEntity(ContactDto contact) {
        Contact contact1 = new Contact();
        contact1.setPrimaryContact(contact.getPrimaryContact());
        contact1.setHomeContact(contact.getHomeContact());
        contact1.setEmergencyContact(contact.getEmergencyContact());

        return contact1;
    }

    private Game gameDtoToEntity(GameDto game) {
        if(!Objects.isNull(game)) {
            Game game1 = new Game();
            game1.setName(game.getName());
            game1.setDescription(game.getDescription());
            game1.setMinPlayers(game.getMinPlayers());
            game1.setMaxPlayers(game.getMaxPlayers());
            game1.setCreatedBy(game.getCreatedBy());

            if(!Objects.isNull(game.getGameSlotDtos()) && !game.getGameSlotDtos().isEmpty()) {
                List<GameSlot> gameSlots = new ArrayList<>();
                gameSlots = game.getGameSlotDtos().stream()
                        .map(this::gameSlotDtoToEntity)
                        .collect(Collectors.toList());
                gameSlots.forEach(slot -> slot.setGame(game1)); // set back-reference
                game1.setGameSlots(gameSlots);
            }

            return game1;
        }
        return null;
    }

}
