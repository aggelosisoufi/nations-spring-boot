package com.isoufi.angelos.nationservice.entity.mariadb;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "languages")
@Getter
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long id;

    @Column(name = "language", nullable = false, length = 50)
    private String language;

    @OneToMany(mappedBy = "language", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CountryLanguage> countries = new ArrayList<>();
}
