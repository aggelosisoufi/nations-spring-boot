package com.isoufi.angelos.nationservice.service;

import com.isoufi.angelos.nationservice.dto.CountryMaxGdpDto;
import com.isoufi.angelos.nationservice.dto.CountryStatsSearchDto;
import com.isoufi.angelos.nationservice.entity.mariadb.CountryStat;
import com.isoufi.angelos.nationservice.filter.CountryStatAdvancedFilter;
import com.isoufi.angelos.nationservice.repository.mariadb.CountryStatRepository;
import com.isoufi.angelos.nationservice.service.interfaces.CountryStatsService;
import com.isoufi.angelos.nationservice.specification.CountryStatSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    @Override
    public Page<CountryStatsSearchDto> search(CountryStatAdvancedFilter filter, Pageable pageable) {
        Specification<CountryStat> spec = CountryStatSpecification.build(filter);

        return countryStatRepository.findAll(spec, pageable)
                .map(stat -> CountryStatsSearchDto.builder()
                        .continentName(stat.getCountry().getRegion().getContinent().getName())
                        .regionName(stat.getCountry().getRegion().getName())
                        .countryName(stat.getCountry().getName())
                        .year(stat.getYear())
                        .population(stat.getPopulation())
                        .gdp(stat.getGdp())
                        .build()
                );
    }
}
