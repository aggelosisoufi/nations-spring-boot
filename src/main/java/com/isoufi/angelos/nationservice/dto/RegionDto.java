package com.isoufi.angelos.nationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegionDto {
    private Long id;
    private String name;
}