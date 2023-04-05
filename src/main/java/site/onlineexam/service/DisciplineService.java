package site.onlineexam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import site.onlineexam.model.Discipline;
import site.onlineexam.repository.DisciplineRepository;

@Service
public class DisciplineService{
    
    private final DisciplineRepository disciplineRepository;

    public DisciplineService(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    public List<Discipline> getAllDisciplines() {
        return disciplineRepository.findAll();
    }

    public Optional<Discipline> getDisciplineById(Long id) {
        return disciplineRepository.findById(id);
    }

    public void saveDiscipline(Discipline discipline) {
        disciplineRepository.save(discipline);
    }

    public void deleteDisciplineById(Long id) {
        disciplineRepository.deleteById(id);
    }
}
