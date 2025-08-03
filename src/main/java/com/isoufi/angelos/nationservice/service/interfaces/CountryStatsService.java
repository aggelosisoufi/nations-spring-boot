package com.isoufi.angelos.nationservice.service.interfaces;

import com.isoufi.angelos.nationservice.dto.CountryMaxGdpDto;

import java.util.List;

public interface CountryStatsService {
    List<CountryMaxGdpDto> getCountriesWithMaxGdpPerPopulation();
}
