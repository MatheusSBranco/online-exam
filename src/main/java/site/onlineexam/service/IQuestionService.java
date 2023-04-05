package site.onlineexam.service;

import java.util.List;

import site.onlineexam.model.Discipline;
import site.onlineexam.model.Question;
import site.onlineexam.model.Tag;
import site.onlineexam.model.Theme;
import site.onlineexam.utils.QuestionType;

public interface IQuestionService {
    Question saveQuestion(Question question);
    
    Question updateQuestion(Question question);
    
    void deleteQuestion(Long id);
    
    Question getQuestionById(Long id);
    
    List<Question> getAllQuestions();
    
    List<Question> getQuestionsByDiscipline(Discipline discipline);
    
    List<Question> getQuestionsByTheme(Theme theme);
    
    List<Question> searchByTags(List<Tag> tags);
    
    List<Question> getQuestionsByType(QuestionType type);
    
    List<Question> getQuestionsByDifficulty(int difficulty);
    
    List<Question> getQuestionsByCreatedBy(String createdBy);
}
