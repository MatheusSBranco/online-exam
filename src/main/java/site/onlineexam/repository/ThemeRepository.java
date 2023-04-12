package site.onlineexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import site.onlineexam.model.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Long>{
    
}
