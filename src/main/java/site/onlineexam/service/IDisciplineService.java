package site.onlineexam.service;

import java.util.List;
import java.util.Optional;

import site.onlineexam.model.Discipline;

public interface IDisciplineService {
    public List<Discipline> getAllDisciplines();
    
    public Optional<Discipline> getDisciplineById(Long id);

    public void saveDiscipline(Discipline discipline);

    public void deleteDisciplineById(Long id);
}
