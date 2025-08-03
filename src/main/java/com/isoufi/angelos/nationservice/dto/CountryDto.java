package com.isoufi.angelos.nationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
public class CountryDto {
    private Long id;
    private String name;
    private BigDecimal area;
    private String countryCode2;
}