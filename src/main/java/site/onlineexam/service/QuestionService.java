package site.onlineexam.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.onlineexam.model.Question;
import site.onlineexam.repository.QuestionRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
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
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + questionId));
    }

    public void updateQuestion(Question updatedQuestion) {
        Question question = questionRepository.findById(updatedQuestion.getId())
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + updatedQuestion.getId()));

        updateQuestionDetails(question, updatedQuestion);
        questionRepository.save(question);
    }

    private void updateQuestionDetails(Question question, Question updatedQuestion) {
        question.setDiscipline(updatedQuestion.getDiscipline());
        question.setTheme(updatedQuestion.getTheme());
        question.setTags(updatedQuestion.getTags());
        question.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
        question.setQuestionType(updatedQuestion.getQuestionType());
        question.setStatement(updatedQuestion.getStatement());
        question.setAnswer(updatedQuestion.getAnswer());
        question.setCorrectAnswer(updatedQuestion.getCorrectAnswer());
    }

    public void deleteQuestion(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + questionId));
        questionRepository.delete(question);
    }
}