package com.isoufi.angelos.nationservice.controller;

import com.isoufi.angelos.nationservice.dto.CountryMaxGdpDto;
import com.isoufi.angelos.nationservice.dto.CountryStatsSearchDto;
import com.isoufi.angelos.nationservice.filter.CountryStatAdvancedFilter;
import com.isoufi.angelos.nationservice.service.interfaces.CountryStatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country-stats")
@RequiredArgsConstructor
@Tag(name = "Country Stats", description = "Endpoints for retrieving aggregated country statistics")
public class CountryStatsController {

    private final CountryStatsService countryStatsService;

    @Operation(
            summary = "Get country stats with maximum GDP-to-population ratio",
            description = "Returns a single record per country which has the highest GDP-to-population ratio (GDP per capita).",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the list of country stats with max GDP per capita",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CountryMaxGdpDto.class)),
                                    examples = @ExampleObject(
                                            value = "[{\"name\": \"United States\", \"countryCode3\": \"USA\", \"year\": 2020, " +
                                                    "\"population\": 331000000, \"gdp\": 21000000000000}," +
                                                    "{\"name\": \"Germany\", \"countryCode3\": \"DEU\", \"year\": 2019, " +
                                                    "\"population\": 83000000, \"gdp\": 3800000000000}]"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No records found for any country"
                    )
            }
    )
    @GetMapping("/max-gdp-ratio")
    public ResponseEntity<List<CountryMaxGdpDto>> getMaxGdpPerPopulation() {
        List<CountryMaxGdpDto> results = countryStatsService.getCountriesWithMaxGdpPerPopulation();

        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(results);
    }

    @Operation(
            summary = "Search country statistics with filters",
            description = "Filter by region, year range, population, and GDP. Supports pagination and sorting."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Paginated list of country stats",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CountryStatsSearchDto.class),
                            examples = @ExampleObject(
                                    value = "{ \"content\": [" +
                                            "{\"continentName\": \"Europe\", \"regionName\": \"Western Europe\", \"countryName\": \"Germany\", " +
                                            "\"year\": 2019, \"population\": 83000000, \"gdp\": 3800000000000}]," +
                                            "\"totalElements\": 1, \"totalPages\": 1, \"size\": 20, \"number\": 0 }"
                            )
                    )
            ),
            @ApiResponse(responseCode = "204", description = "No matching records found")
    })
    @PostMapping("/search")
    public ResponseEntity<Page<CountryStatsSearchDto>> filter(
            @RequestBody CountryStatAdvancedFilter filter,
            Pageable pageable) {

        Page<CountryStatsSearchDto> results = countryStatsService.search(filter, pageable);

        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(results);
    }
}
