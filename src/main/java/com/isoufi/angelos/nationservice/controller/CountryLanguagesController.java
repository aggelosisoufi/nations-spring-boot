package com.isoufi.angelos.nationservice.controller;

import com.isoufi.angelos.nationservice.service.interfaces.CountryLanguagesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country-languages")
@RequiredArgsConstructor
@Tag(name = "Country Languages", description = "Endpoints for retrieving languages spoken in a country")
public class CountryLanguagesController {

    private final CountryLanguagesService countryLanguagesService;

    @Operation(
            summary = "Get languages by country ID",
            description = "Returns all languages spoken in a country based on its numeric ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the list of languages",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = String.class)),
                                    examples = @ExampleObject(value = "[\"English\", \"French\", \"German\"]")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No languages found for the given country ID"
                    )
            }
    )
    @GetMapping("/{countryId}")
    public ResponseEntity<List<String>> getLanguagesByCountry(@PathVariable Long countryId) {
        List<String> languages = countryLanguagesService.getLanguagesByCountry(countryId);

        if (languages.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(languages);
    }
}
