package com.isoufi.angelos.nationservice.controller;

import com.isoufi.angelos.nationservice.service.interfaces.CountryLanguagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/country-languages")
@RequiredArgsConstructor
public class CountryLanguagesController {

    private final CountryLanguagesService countryLanguagesService;

    /**
     * Returns all languages spoken in a country based on its numeric ID.
     * Example: GET /api/languages/1
     */
    @GetMapping("/{countryId}")
    public List<String> getLanguagesByCountry(@PathVariable Long countryId) {
        return countryLanguagesService.getLanguagesByCountry(countryId);
    }
}
