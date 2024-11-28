package site.onlineexam.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import site.onlineexam.model.Theme;
import site.onlineexam.repository.ThemeRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ThemeServiceTest {

    @Mock
    private ThemeRepository themeRepository;

    @InjectMocks
    private ThemeService themeService;

    private Theme theme;

    @BeforeEach
    void setUp() {
        theme = new Theme();
        theme.setId(1L);
        theme.setName("History");
    }

    @Test
    void shouldAddTheme() {
        themeService.addTheme(theme);
        verify(themeRepository, times(1)).save(theme);
    }

    @Test
    void shouldReturnAllThemes() {
        when(themeRepository.findAll()).thenReturn(Collections.singletonList(theme));

        List<Theme> retrievedThemes = themeService.getAllThemes();

        assertEquals(1, retrievedThemes.size());
        assertEquals("History", retrievedThemes.get(0).getName());
    }

    @Test
    void shouldReturnThemeById() {
        when(themeRepository.findById(anyLong())).thenReturn(Optional.of(theme));

        Theme retrievedTheme = themeService.getThemeById(1L);

        assertNotNull(retrievedTheme);
        assertEquals("History", retrievedTheme.getName());
    }

    @Test
    void shouldThrowExceptionWhenThemeNotFoundById() {
        when(themeRepository.findById(anyLong())).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            themeService.getThemeById(1L);
        });

        assertEquals("Theme not found with id: 1", exception.getMessage());
    }

    @Test
    void shouldUpdateTheme() {
        Theme updatedTheme = new Theme();
        updatedTheme.setName("Science");

        when(themeRepository.findById(anyLong())).thenReturn(Optional.of(theme));

        themeService.updateTheme(1L, updatedTheme);

        verify(themeRepository, times(1)).save(theme);
        assertEquals("Science", theme.getName());
    }

    @Test
    void shouldDeleteTheme() {
        when(themeRepository.findById(anyLong())).thenReturn(Optional.of(theme));

        themeService.deleteTheme(1L);

        verify(themeRepository, times(1)).delete(theme);
    }
}