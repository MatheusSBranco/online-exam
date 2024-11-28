package site.onlineexam.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import site.onlineexam.model.Discipline;
import site.onlineexam.repository.DisciplineRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DisciplineServiceTest {

    @Mock
    private DisciplineRepository disciplineRepository;

    @InjectMocks
    private DisciplineService disciplineService;

    private Discipline discipline;

    @BeforeEach
    void setUp() {
        discipline = new Discipline();
        discipline.setId(1L);
        discipline.setName("Mathematics");
    }

    @Test
    void shouldAddDiscipline() {
        disciplineService.addDiscipline(discipline);
        verify(disciplineRepository, times(1)).save(discipline);
    }

    @Test
    void shouldReturnAllDisciplines() {
        when(disciplineRepository.findAll()).thenReturn(Collections.singletonList(discipline));

        List<Discipline> retrievedDisciplines = disciplineService.getAllDisciplines();

        assertEquals(1, retrievedDisciplines.size());
        assertEquals("Mathematics", retrievedDisciplines.get(0).getName());
    }

    @Test
    void shouldReturnDisciplineById() {
        when(disciplineRepository.findById(anyLong())).thenReturn(Optional.of(discipline));

        Discipline retrievedDiscipline = disciplineService.getDisciplineById(1L);

        assertNotNull(retrievedDiscipline);
        assertEquals("Mathematics", retrievedDiscipline.getName());
    }

    @Test
    void shouldThrowExceptionWhenDisciplineNotFoundById() {
        when(disciplineRepository.findById(anyLong())).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            disciplineService.getDisciplineById(1L);
        });

        assertEquals("Discipline not found with id: 1", exception.getMessage());
    }

    @Test
    void shouldUpdateDiscipline() {
        Discipline updatedDiscipline = new Discipline();
        updatedDiscipline.setName("Physics");

        when(disciplineRepository.findById(anyLong())).thenReturn(Optional.of(discipline));

        disciplineService.updateDiscipline(1L, updatedDiscipline);

        verify(disciplineRepository, times(1)).save(discipline);
        assertEquals("Physics", discipline.getName());
    }

    @Test
    void shouldDeleteDiscipline() {
        when(disciplineRepository.findById(anyLong())).thenReturn(Optional.of(discipline));

        disciplineService.deleteDiscipline(1L);

        verify(disciplineRepository, times(1)).delete(discipline);
    }
}