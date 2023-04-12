package site.onlineexam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import site.onlineexam.model.Theme;
import site.onlineexam.repository.ThemeRepository;
@Service
public class ThemeService {

    private ThemeRepository themeRepository;

    public ThemeService(ThemeRepository themeRepository){
        this.themeRepository = themeRepository;
    }

    public void addTheme(Theme theme) {
        themeRepository.save(theme);
    }

    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    public Theme getThemeById(Long themeId) {
        Optional<Theme> optionalTheme = themeRepository.findById(themeId);
        if (optionalTheme.isPresent()) {
            return optionalTheme.get();
        } else {
            throw new EntityNotFoundException("Theme not found with id: " + themeId);
        }
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
