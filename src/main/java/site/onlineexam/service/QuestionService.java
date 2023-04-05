package site.onlineexam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import site.onlineexam.model.Discipline;
import site.onlineexam.model.Question;
import site.onlineexam.model.Tag;
import site.onlineexam.model.Theme;
import site.onlineexam.repository.QuestionRepository;
import site.onlineexam.utils.QuestionType;

@Service
public class QuestionService{
    
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question saveQuestion(Question question){
        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    public List<Question> getAllQuestions() {
        return (List<Question>) questionRepository.findAll();
    }

    public List<Question> getQuestionsByDiscipline(Discipline discipline) {
        return questionRepository.findByDiscipline(discipline);
    }

    public List<Question> getQuestionsByTheme(Theme theme) {
        return questionRepository.findByTheme(theme);
    }

    public List<Question> searchByTags(List<Tag> tags) {
        return questionRepository.findByTagsIn(tags);
    }

    public List<Question> getQuestionsByType(QuestionType type) {
        return questionRepository.findByType(type);
    }

    public List<Question> getQuestionsByDifficulty(int difficulty) {
        return questionRepository.findByDifficulty(difficulty);
    }

    public List<Question> getQuestionsByCreatedBy(String createdBy) {
        return questionRepository.findByCreatedBy(createdBy);
    }
}
