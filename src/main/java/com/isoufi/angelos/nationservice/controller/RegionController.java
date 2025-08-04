package com.isoufi.angelos.nationservice.controller;

import com.isoufi.angelos.nationservice.dto.RegionDto;
import com.isoufi.angelos.nationservice.service.interfaces.RegionService;
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
@RequestMapping("/api/regions")
@RequiredArgsConstructor
@Tag(name = "Regions", description = "Endpoints for retrieving region data")
public class RegionController {

    private final RegionService regionService;

    @Operation(
            summary = "Get all regions",
            description = "Returns a list of all regions with their IDs and names.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the list of regions",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = RegionDto.class)),
                                    examples = @ExampleObject(
                                            value = "[{\"id\":1,\"name\":\"Western Europe\"},{\"id\":2,\"name\":\"Eastern Europe\"}]"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No regions found"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<RegionDto>> getRegions() {
        List<RegionDto> regions = regionService.getAllRegions();

        if (regions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(regions);
    }
}
