package com.isoufi.angelos.nationservice.service;

import com.isoufi.angelos.nationservice.dto.CountryDto;
import com.isoufi.angelos.nationservice.repository.mariadb.CountryRepository;
import com.isoufi.angelos.nationservice.service.interfaces.CountriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountriesServiceImpl implements CountriesService {

    private final CountryRepository countryRepository;

    public List<CountryDto> getAllCountriesOrdered() {
        return countryRepository.findAllByOrderByNameAsc()
                .stream()
                .map(c -> CountryDto.builder()
                        .countryCode2(c.getCode2())
                        .name(c.getName())
                        .area(c.getArea())
                        .build())
                .collect(Collectors.toList());
    }
}
