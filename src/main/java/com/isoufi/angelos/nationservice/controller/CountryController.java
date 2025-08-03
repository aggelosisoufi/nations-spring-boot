package com.isoufi.angelos.nationservice.controller;

import com.isoufi.angelos.nationservice.dto.CountryDto;
import com.isoufi.angelos.nationservice.service.interfaces.CountriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountriesService countriesService;

    /**
     * Returns all countries ordered by their name in asc.
     * Example: GET /api/countries/all
     */
    @Operation(
            summary = "Get all countries",
            description = "Retrieves all countries sorted by their name in ascending order.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the list of countries",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CountryDto.class)),
                                    examples = @ExampleObject(
                                            value = "[{\"id\": 1, \"name\": \"Albania\", \"area\": 28748.00, \"countryCode2\": \"AL\"}," +
                                                    "{\"id\": 2, \"name\": \"Belgium\", \"area\": 30528.00, \"countryCode2\": \"BE\"}]"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No countries found"
                    )
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<CountryDto>> getAllCountriesOrdered() {
        List<CountryDto> countries = countriesService.getAllCountriesOrdered();

        if (countries.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(countries);
    }
}
