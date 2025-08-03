package com.isoufi.angelos.nationservice.service.interfaces;

import java.util.List;

public interface CountryLanguagesService {
    List<String> getLanguagesByCountry(Long countryId);
}
