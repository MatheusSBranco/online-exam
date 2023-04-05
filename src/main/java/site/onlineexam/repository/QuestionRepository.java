package site.onlineexam.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import site.onlineexam.model.Discipline;
import site.onlineexam.model.Question;
import site.onlineexam.model.Tag;
import site.onlineexam.model.Theme;
import site.onlineexam.utils.QuestionType;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByDiscipline(Discipline discipline);

    List<Question> findByTheme(Theme theme);

    List<Question> findByTagsIn(List<Tag> tags);

    List<Question> findByType(QuestionType type);

    List<Question> findByDifficulty(int difficulty);

    List<Question> findByCreatedBy(String createdBy);

    List<Question> findByCreationDate(LocalDate creationDate);
}
