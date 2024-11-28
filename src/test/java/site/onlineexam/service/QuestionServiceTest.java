package site.onlineexam.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import site.onlineexam.model.Question;
import site.onlineexam.repository.QuestionRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService questionService;

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question();
        question.setId(1L);
        question.setStatement("What is the capital of France?");
        question.setCreationDate(LocalDate.of(2024, 11, 27));
        question.setCreatedBy("ADMIN");
    }

    @Test
    void shouldAddQuestion() {
        questionService.addQuestion(question);

        assertEquals(LocalDate.now(), question.getCreationDate());
        assertEquals("ADMIN", question.getCreatedBy());
        verify(questionRepository, times(1)).save(question);
    }

    @Test
    void shouldReturnAllQuestions() {
        when(questionRepository.findAll()).thenReturn(Collections.singletonList(question));

        List<Question> retrievedQuestions = questionService.getAllQuestions();

        assertEquals(1, retrievedQuestions.size());
        assertEquals("What is the capital of France?", retrievedQuestions.get(0).getStatement());
    }

    @Test
    void shouldReturnQuestionById() {
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));

        Question retrievedQuestion = questionService.getQuestionById(1L);

        assertNotNull(retrievedQuestion);
        assertEquals("What is the capital of France?", retrievedQuestion.getStatement());
    }

    @Test
    void shouldThrowExceptionWhenQuestionNotFoundById() {
        when(questionRepository.findById(anyLong())).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            questionService.getQuestionById(1L);
        });

        assertEquals("Question not found with id: 1", exception.getMessage());
    }

    @Test
    void shouldUpdateQuestion() {
        Question updatedQuestion = new Question();
        updatedQuestion.setId(1L);
        updatedQuestion.setStatement("What is the capital of Germany?");
        updatedQuestion.setCreationDate(LocalDate.of(2024, 11, 27));
        updatedQuestion.setCreatedBy("ADMIN");

        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));

        questionService.updateQuestion(updatedQuestion);

        verify(questionRepository, times(1)).save(question);
        assertEquals("What is the capital of Germany?", question.getStatement());
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentQuestion() {
        Question updatedQuestion = new Question();
        updatedQuestion.setId(1L);

        when(questionRepository.findById(anyLong())).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            questionService.updateQuestion(updatedQuestion);
        });

        assertEquals("Question not found with id: 1", exception.getMessage());
    }

    @Test
    void shouldDeleteQuestion() {
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));

        questionService.deleteQuestion(1L);

        verify(questionRepository, times(1)).delete(question);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentQuestion() {
        when(questionRepository.findById(anyLong())).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            questionService.deleteQuestion(1L);
        });

        assertEquals("Question not found with id: 1", exception.getMessage());
    }
}