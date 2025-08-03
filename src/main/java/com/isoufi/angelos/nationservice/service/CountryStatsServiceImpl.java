package com.isoufi.angelos.nationservice.service;

import com.isoufi.angelos.nationservice.dto.CountryMaxGdpDto;
import com.isoufi.angelos.nationservice.repository.mariadb.CountryStatRepository;
import com.isoufi.angelos.nationservice.service.interfaces.CountryStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryStatsServiceImpl implements CountryStatsService {

    private final CountryStatRepository countryStatRepository;

    @Override
    public List<CountryMaxGdpDto> getCountriesWithMaxGdpPerPopulation() {
        return countryStatRepository.findMaxGdpPerPopulation()
                .stream()
                .map(proj -> CountryMaxGdpDto.builder()
                        .name(proj.getName())
                        .countryCode3(proj.getCountryCode3())
                        .year(proj.getYear())
                        .population(proj.getPopulation())
                        .gdp(proj.getGdp())
                        .build())
                .collect(Collectors.toList());
    }
}
