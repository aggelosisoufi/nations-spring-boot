package com.isoufi.angelos.nationservice.entity.mariadb;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class CountryStatId implements Serializable {
    private Long countryId;
    private Integer year;
}
