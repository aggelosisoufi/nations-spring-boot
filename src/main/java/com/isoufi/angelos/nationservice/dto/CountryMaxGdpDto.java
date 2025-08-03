package com.isoufi.angelos.nationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class CountryMaxGdpDto {
    private String name;
    private String countryCode3;
    private Integer year;
    private Integer population;
    private BigDecimal gdp;
}