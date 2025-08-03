package com.isoufi.angelos.nationservice.projection;

import java.math.BigDecimal;

public interface CountryMaxGdpProjection {
    String getName();
    String getCountryCode3();
    Integer getYear();
    Integer getPopulation();
    BigDecimal getGdp();
}
