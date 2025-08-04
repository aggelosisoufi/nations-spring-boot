package com.isoufi.angelos.nationservice.service.interfaces;

import com.isoufi.angelos.nationservice.dto.CountryMaxGdpDto;
import com.isoufi.angelos.nationservice.dto.CountryStatsSearchDto;
import com.isoufi.angelos.nationservice.filter.CountryStatAdvancedFilter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CountryStatsService {
    List<CountryMaxGdpDto> getCountriesWithMaxGdpPerPopulation();
    Page<CountryStatsSearchDto> search(CountryStatAdvancedFilter filter, Pageable pageable);
}
