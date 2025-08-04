package com.isoufi.angelos.nationservice.specification;

import com.isoufi.angelos.nationservice.entity.mariadb.CountryStat;
import com.isoufi.angelos.nationservice.filter.BaseFilter;
import com.isoufi.angelos.nationservice.filter.CountryStatAdvancedFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.math.BigDecimal;

public class CountryStatSpecification {

    /**
     * Builds a dynamic Specification for CountryStat based on the provided filter.
     */
    public static Specification<CountryStat> build(CountryStatAdvancedFilter filter) {
        if (filter == null) {
            return Specification.where(null);
        }

        return Specification.where(regionFilter(filter))
                .and(numberFilter("year", filter.getYear()))
                .and(numberFilter("population", filter.getPopulation()))
                .and(decimalFilter("gdp", filter.getGdp()));
    }

    /**
     * Handles filtering by region (nested: CountryStat -> Country -> Region)
     */
    private static Specification<CountryStat> regionFilter(CountryStatAdvancedFilter filter) {
        if (filter.getRegionId() == null) return null;

        return (root, query, cb) -> {
            Join<Object, Object> countryJoin = root.join("country");
            Join<Object, Object> regionJoin = countryJoin.join("region");

            BaseFilter<Long> regionFilter = filter.getRegionId();

            if (regionFilter.getEquals() != null) {
                return cb.equal(regionJoin.get("id"), regionFilter.getEquals());
            }
            if (regionFilter.getInValues() != null && !regionFilter.getInValues().isEmpty()) {
                return regionJoin.get("id").in(regionFilter.getInValues());
            }
            return null;
        };
    }

    /**
     * Generic filter for numeric fields like year and population
     */
    private static <N extends Number & Comparable<N>> Specification<CountryStat> numberFilter(
            String field, BaseFilter<N> filter) {

        if (filter == null) return null;

        return (root, query, cb) -> {
            if (filter.getEquals() != null) {
                return cb.equal(root.get(field), filter.getEquals());
            }
            if (filter.getFrom() != null && filter.getTo() != null) {
                return cb.between(root.get(field), filter.getFrom(), filter.getTo());
            }
            if (filter.getFrom() != null) {
                return cb.greaterThanOrEqualTo(root.get(field), filter.getFrom());
            }
            if (filter.getTo() != null) {
                return cb.lessThanOrEqualTo(root.get(field), filter.getTo());
            }
            if (filter.getInValues() != null && !filter.getInValues().isEmpty()) {
                return root.get(field).in(filter.getInValues());
            }
            return null;
        };
    }

    /**
     * Generic filter for decimal fields like GDP
     */
    private static Specification<CountryStat> decimalFilter(
            String field, BaseFilter<BigDecimal> filter) {

        if (filter == null) return null;

        return (root, query, cb) -> {
            if (filter.getEquals() != null) {
                return cb.equal(root.get(field), filter.getEquals());
            }
            if (filter.getFrom() != null && filter.getTo() != null) {
                return cb.between(root.get(field), filter.getFrom(), filter.getTo());
            }
            if (filter.getFrom() != null) {
                return cb.greaterThanOrEqualTo(root.get(field), filter.getFrom());
            }
            if (filter.getTo() != null) {
                return cb.lessThanOrEqualTo(root.get(field), filter.getTo());
            }
            if (filter.getInValues() != null && !filter.getInValues().isEmpty()) {
                return root.get(field).in(filter.getInValues());
            }
            return null;
        };
    }
}
