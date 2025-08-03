package com.isoufi.angelos.nationservice.entity.mariadb;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "region_areas")
@Getter
public class RegionArea {

    @Id
    @Column(name = "region_name", nullable = false, length = 100)
    private String regionName;

    @Column(name = "region_area", nullable = false, precision = 15, scale = 2)
    private BigDecimal regionArea;
}
