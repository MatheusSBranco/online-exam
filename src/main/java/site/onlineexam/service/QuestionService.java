package site.onlineexam.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import site.onlineexam.model.Question;
import site.onlineexam.repository.QuestionRepository;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public void addQuestion(Question question) {
        question.setCreationDate(LocalDate.now());
        question.setCreatedBy("ADMIN");
        questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isPresent()) {
            return optionalQuestion.get();
        } else {
            throw new IllegalArgumentException("Question not found with id: " + questionId);
        }
    }

    public void updateQuestion(Question updatedQuestion) {
        Optional<Question> optionalQuestion = questionRepository.findById(updatedQuestion.getId());
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            question.setDiscipline(updatedQuestion.getDiscipline());
            question.setTheme(updatedQuestion.getTheme());
            question.setTags(updatedQuestion.getTags());
            question.setCreationDate(updatedQuestion.getCreationDate());
            question.setCreatedBy(updatedQuestion.getCreatedBy());
            question.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
            question.setQuestionType(updatedQuestion.getQuestionType());
            question.setStatement(updatedQuestion.getStatement());
            question.setAnswer(updatedQuestion.getAnswer());
            question.setCorrectAnswer(updatedQuestion.getCorrectAnswer());
            questionRepository.save(question);
        } else {
            throw new EntityNotFoundException("Question not found with id " + updatedQuestion.getId());
        }
    }

    public void deleteQuestion(Long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isPresent()) {
            questionRepository.delete(optionalQuestion.get());
        } else {
            throw new IllegalArgumentException("Question not found with id: " + questionId);
        }
    }
}
