package site.onlineexam.repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import site.onlineexam.model.Discipline;
import site.onlineexam.model.Question;
import site.onlineexam.model.Tag;
import site.onlineexam.model.Theme;

public interface QuestionRepository extends JpaRepository<Question, Long>{
    List<Question> findByDiscipline(Discipline discipline);
    
    List<Question> findByTheme(Theme theme);
    
    List<Question> findByTagsIn(Collection<Set<Tag>> tags);
}
