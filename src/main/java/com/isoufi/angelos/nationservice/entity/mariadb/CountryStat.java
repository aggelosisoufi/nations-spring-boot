package com.isoufi.angelos.nationservice.entity.mariadb;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "country_stats")
@IdClass(CountryStatId.class)
@Getter
public class CountryStat {

    @Id
    @Column(name = "country_id", nullable = false)
    private Long countryId;

    @Id
    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "population")
    private Integer population;

    @Column(name = "gdp", precision = 15)
    private BigDecimal gdp;

    @ManyToOne
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private Country country;
}

