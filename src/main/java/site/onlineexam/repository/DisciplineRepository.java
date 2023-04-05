package site.onlineexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import site.onlineexam.model.Discipline;

public interface DisciplineRepository extends JpaRepository<Discipline, Long>{
    
    Discipline findByName(String name);
}
