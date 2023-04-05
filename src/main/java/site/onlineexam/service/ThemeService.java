package site.onlineexam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import site.onlineexam.model.Theme;
import site.onlineexam.repository.ThemeRepository;

@Service
public class ThemeService {
    
    private final ThemeRepository themeRepository;

    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    public Optional<Theme> getThemeById(Long id) {
        return themeRepository.findById(id);
    }

    public void saveTheme(Theme theme) {
        themeRepository.save(theme);
    }

    public void deleteThemeById(Long id) {
        themeRepository.deleteById(id);
    }
}
