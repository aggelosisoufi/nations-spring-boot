package com.isoufi.angelos.nationservice.entity.mariadb;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "country_languages")
@Getter
@IdClass(CountryLanguageId.class)
public class CountryLanguage {

    @Id
    @Column(name = "country_id", nullable = false)
    private Long countryId;

    @Id
    @Column(name = "language_id", nullable = false)
    private Long languageId;

    @Column(name = "official", nullable = false)
    private Boolean official;

    @ManyToOne
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "language_id", insertable = false, updatable = false)
    private Language language;
}

@Getter
@Setter
@EqualsAndHashCode
class CountryLanguageId implements Serializable {
    private Long countryId;
    private Long languageId;
}