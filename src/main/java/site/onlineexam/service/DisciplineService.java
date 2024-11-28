package site.onlineexam.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.onlineexam.model.Discipline;
import site.onlineexam.repository.DisciplineRepository;

import java.util.List;

@Service
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;

    @Autowired
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
        return disciplineRepository.findById(disciplineId)
                .orElseThrow(() -> new EntityNotFoundException("Discipline not found with id: " + disciplineId));
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