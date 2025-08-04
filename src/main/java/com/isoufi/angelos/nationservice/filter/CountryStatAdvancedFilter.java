package com.isoufi.angelos.nationservice.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Advanced filter for country statistics")
public class CountryStatAdvancedFilter {

    @Schema(description = "Region filter", example = "{ \"equals\": 5 }")
    private BaseFilter<Long> regionId;

    @Schema(description = "Year filter (supports equals, from-to)", example = "{ \"from\": 2000, \"to\": 2010 }")
    private BaseFilter<Integer> year;

    @Schema(description = "Population filter (optional)", example = "{ \"from\": 1000000 }")
    private BaseFilter<Integer> population;

    @Schema(description = "GDP filter (optional)", example = "{ \"from\": 1000000000 }")
    private BaseFilter<BigDecimal> gdp;
}
