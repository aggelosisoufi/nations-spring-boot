package com.isoufi.angelos.nationservice.repository.mariadb;

import com.isoufi.angelos.nationservice.entity.mariadb.CountryLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryLanguageRepository extends JpaRepository<CountryLanguage, Long> {
    @Query("SELECT cl.language.language FROM CountryLanguage cl WHERE cl.country.id = :countryId")
    List<String> getLanguageNamesByCountryId(@Param("countryId") Long countryId);
}
