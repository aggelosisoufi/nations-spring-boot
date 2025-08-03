package com.isoufi.angelos.nationservice.repository.mariadb;

import com.isoufi.angelos.nationservice.entity.mariadb.Country;
import com.isoufi.angelos.nationservice.projection.CountryProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    List<CountryProjection> findAllByOrderByNameAsc();
}
