package com.isoufi.angelos.nationservice.controller;

import com.isoufi.angelos.nationservice.dto.CountryMaxGdpDto;
import com.isoufi.angelos.nationservice.service.interfaces.CountryStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/country-stats")
@RequiredArgsConstructor
public class CountryStatsController {

    private final CountryStatsService countryStatsService;

    /**
     * Returns the list of countries with their country stats record
     * that has the maximum GDP-to-population ratio.
     *
     * Example:
     * GET /api/country-stats/max-gdp-ratio
     *
     * @return a list of {@link CountryMaxGdpDto} objects containing
     *         name, countryCode3, year, population, and gdp
     *         for the record with the highest GDP per capita.
     */
    @GetMapping("/max-gdp-ratio")
    public List<CountryMaxGdpDto> getMaxGdpPerPopulation() {
        return countryStatsService.getCountriesWithMaxGdpPerPopulation();
    }
}