package site.onlineexam.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.onlineexam.model.Theme;
import site.onlineexam.repository.ThemeRepository;

import java.util.List;

@Service
public class ThemeService {

    private final ThemeRepository themeRepository;

    @Autowired
    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public void addTheme(Theme theme) {
        themeRepository.save(theme);
    }

    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    public Theme getThemeById(Long themeId) {
        return themeRepository.findById(themeId)
                .orElseThrow(() -> new EntityNotFoundException("Theme not found with id: " + themeId));
    }

    public void updateTheme(Long themeId, Theme updatedTheme) {
        Theme theme = getThemeById(themeId);
        theme.setName(updatedTheme.getName());
        themeRepository.save(theme);
    }

    public void deleteTheme(Long themeId) {
        Theme theme = getThemeById(themeId);
        themeRepository.delete(theme);
    }
}