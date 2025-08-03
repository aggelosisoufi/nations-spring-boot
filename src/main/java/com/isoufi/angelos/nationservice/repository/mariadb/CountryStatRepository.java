package com.isoufi.angelos.nationservice.repository.mariadb;

import com.isoufi.angelos.nationservice.entity.mariadb.CountryStat;
import com.isoufi.angelos.nationservice.entity.mariadb.CountryStatId;
import com.isoufi.angelos.nationservice.projection.CountryMaxGdpProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryStatRepository extends JpaRepository<CountryStat, CountryStatId>{
    @Query(value =
            "SELECT c.name AS name, " +
                    "       c.country_code3 AS countryCode3, " +
                    "       cs.year AS year, " +
                    "       cs.population AS population, " +
                    "       cs.gdp AS gdp " +
                    "FROM country_stats cs " +
                    "INNER JOIN countries c ON c.country_id = cs.country_id " +
                    "INNER JOIN ( " +
                    "    SELECT country_id, MAX(gdp/population) AS max_ratio " +
                    "    FROM country_stats " +
                    "    WHERE population > 0 " +
                    "    GROUP BY country_id " +
                    ") mx ON cs.country_id = mx.country_id " +
                    "   AND (cs.gdp / cs.population) = mx.max_ratio " +
                    "ORDER BY c.name ASC",
            nativeQuery = true)
    List<CountryMaxGdpProjection> findMaxGdpPerPopulation();
}
