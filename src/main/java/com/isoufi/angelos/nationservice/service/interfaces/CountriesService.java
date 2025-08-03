package com.isoufi.angelos.nationservice.service.interfaces;

import com.isoufi.angelos.nationservice.dto.CountryDto;

import java.util.List;

public interface CountriesService {
    List<CountryDto> getAllCountriesOrdered();
}
