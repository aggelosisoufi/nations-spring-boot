package com.isoufi.angelos.nationservice.controller;

import com.isoufi.angelos.nationservice.dto.CountryDto;
import com.isoufi.angelos.nationservice.service.interfaces.CountriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountriesService countriesService;

    /**
     * Returns all countries ordered by their name in asc.
     * Example: GET /api/countries/all
     */
    @GetMapping("/all")
    public List<CountryDto> getAllCountriesOrdered() {
        return countriesService.getAllCountriesOrdered();
    }
}
