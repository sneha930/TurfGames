package com.lcwd.game.turf.GameTurf.services.impl;

import com.lcwd.game.turf.GameTurf.dtos.TurfDto;
import com.lcwd.game.turf.GameTurf.dtos.TurfSizeDto;
import com.lcwd.game.turf.GameTurf.entities.Turf;
import com.lcwd.game.turf.GameTurf.entities.TurfSize;
import com.lcwd.game.turf.GameTurf.repositories.TurfRepository;
import com.lcwd.game.turf.GameTurf.services.TurfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TurfServiceImpl implements TurfService {

    @Autowired
    TurfRepository turfRepository;

    @Override
    public List<TurfDto> getAllTurfs() {
        List<Turf> turfs = turfRepository.findAll();
        List<TurfDto> turfDtoList = turfs.stream()
                .map(turf -> turfEntityToDto(turf))
                .toList();
        return turfDtoList;
    }

    @Override
    public TurfDto createTurf(TurfDto turfDto) {
        Turf turf = turfDtoToEntity(turfDto);
        Turf savedTurf = turfRepository.save(turf);
        return turfEntityToDto(savedTurf);
    }

    private Turf turfDtoToEntity(TurfDto turfDto) {
        if(!Objects.isNull(turfDto)) {
            Turf turf = new Turf();
            turf.setName(turfDto.getName());
            turf.setAddress(turfDto.getAddress());
            turf.setDescription(turfDto.getDescription());

            List<TurfSize> turfSizes = turfDto.getTurfSize().stream()
                    .map(turfSize -> turfSizeDtoToEntity(turfSize)).collect(Collectors.toList());
            turfSizes.forEach(turfSize -> { turfSize.setTurf(turf);});
            turf.setTurfSize(turfSizes);

            return turf;
        }
        return null;
    }

    private TurfSize turfSizeDtoToEntity(TurfSizeDto turfSize) {
        if(!Objects.isNull(turfSize)) {
            TurfSize turfSize1 = new TurfSize();
            turfSize1.setSizeName(turfSize.getSizeName());
            turfSize1.setCapacity(turfSize.getCapacity());

//            turfSize1.setTurf(turfDtoToEntity(turfSize.getTurf())); // Dangerous recursion!
            Turf turf = new Turf();
//            turf.setId(turfSize.getId());  // Just set ID to avoid recursion
            turfSize1.setTurf(turf);

            return turfSize1;
        }
        return null;
    }

    private TurfDto turfEntityToDto(Turf turf) {
        if(!Objects.isNull(turf)) {
            TurfDto turfDto = new TurfDto();
            turfDto.setId(turf.getId());
            turfDto.setName(turf.getName());
            turfDto.setDescription(turf.getDescription());
            turfDto.setAddress(turf.getAddress());

            List<TurfSizeDto> turfSizeDtos = turf.getTurfSize().stream()
                    .map(this::turfSizeEntityToDto).collect(Collectors.toList());

            turfDto.setTurfSize(turfSizeDtos);

            return turfDto;
        }
        return null;
    }

    private TurfSizeDto turfSizeEntityToDto(TurfSize turfSize) {
        if(!Objects.isNull(turfSize)) {
            TurfSizeDto turfSizeDto = new TurfSizeDto();
            turfSizeDto.setId(turfSize.getId());
            turfSizeDto.setSizeName(turfSize.getSizeName());
            turfSizeDto.setCapacity(turfSize.getCapacity());

            // no need o
            //turfSizeDto.setTurf(turfEntityToDto(turfSize.getTurf()));
            turfSizeDto.setTurf(minimalTurfInfo(turfSize.getTurf()));

            return turfSizeDto;
        }
        return null;
    }

    private TurfDto minimalTurfInfo(Turf turf) {
        if (turf == null) return null;

        TurfDto dto = new TurfDto();
        dto.setId(turf.getId());
        dto.setName(turf.getName());
        return dto;
    }
}
