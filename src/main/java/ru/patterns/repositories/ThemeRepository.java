package ru.patterns.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.patterns.models.Theme;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
}
