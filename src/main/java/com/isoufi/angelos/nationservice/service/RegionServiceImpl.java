package com.isoufi.angelos.nationservice.service;

import com.isoufi.angelos.nationservice.dto.RegionDto;
import com.isoufi.angelos.nationservice.repository.mariadb.RegionRepository;
import com.isoufi.angelos.nationservice.service.interfaces.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    @Override
    public List<RegionDto> getAllRegions() {
        return regionRepository.findAll()
                .stream()
                .map(region -> RegionDto.builder()
                        .id(region.getId())
                        .name(region.getName())
                        .build()
                )
                .collect(Collectors.toList());
    }
}