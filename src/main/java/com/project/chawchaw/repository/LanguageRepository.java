package com.project.chawchaw.repository;

import com.project.chawchaw.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language,Long> {
    Optional<Language> findByAbbr(String abbr);
}
