package com.isoufi.angelos.nationservice.repository.mariadb;

import com.isoufi.angelos.nationservice.entity.mariadb.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {}
