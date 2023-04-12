package site.onlineexam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import site.onlineexam.model.Discipline;
import site.onlineexam.repository.DisciplineRepository;

@Service
public class DisciplineService {
    private DisciplineRepository disciplineRepository;

    public DisciplineService(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    public void addDiscipline(Discipline discipline) {
        disciplineRepository.save(discipline);
    }

    public List<Discipline> getAllDisciplines() {
        return disciplineRepository.findAll();
    }

    public Discipline getDisciplineById(Long disciplineId) {
        Optional<Discipline> optionalDiscipline = disciplineRepository.findById(disciplineId);
        if (optionalDiscipline.isPresent()) {
            return optionalDiscipline.get();
        } else {
            throw new EntityNotFoundException("Discipline not found with id: " + disciplineId);
        }
    }

    public void updateDiscipline(Long disciplineId, Discipline updatedDiscipline) {
        Discipline discipline = getDisciplineById(disciplineId);
        discipline.setName(updatedDiscipline.getName());
        disciplineRepository.save(discipline);
    }

    public void deleteDiscipline(Long disciplineId) {
        Discipline discipline = getDisciplineById(disciplineId);
        disciplineRepository.delete(discipline);
    }
}
