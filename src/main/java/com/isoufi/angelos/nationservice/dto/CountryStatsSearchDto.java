package com.isoufi.angelos.nationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryStatsSearchDto {
    private String continentName;
    private String regionName;
    private String countryName;
    private Integer year;
    private Integer population;
    private BigDecimal gdp;
}