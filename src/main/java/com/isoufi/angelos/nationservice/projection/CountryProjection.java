package com.isoufi.angelos.nationservice.projection;

import java.math.BigDecimal;

public interface CountryProjection {
    Long getId();
    String getName();
    BigDecimal getArea();
    String getCode2();
}
