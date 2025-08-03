package com.isoufi.angelos.nationservice.service;

import com.isoufi.angelos.nationservice.repository.mariadb.CountryLanguageRepository;
import com.isoufi.angelos.nationservice.service.interfaces.CountryLanguagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryLanguagesServiceImpl implements CountryLanguagesService{
    private final CountryLanguageRepository countryLanguageRepository;

    @Override
    public List<String> getLanguagesByCountry(Long countryId) {
        return countryLanguageRepository.getLanguageNamesByCountryId(countryId);
    }
}
