package site.onlineexam.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import site.onlineexam.model.Discipline;
import site.onlineexam.model.Question;
import site.onlineexam.model.Theme;
import site.onlineexam.service.DisciplineService;
import site.onlineexam.service.QuestionService;
import site.onlineexam.service.TagService;
import site.onlineexam.service.ThemeService;

import java.util.Collections;

@ExtendWith(SpringExtension.class)
@WebMvcTest(QuestionController.class)
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private ThemeService themeService;

    @MockBean
    private DisciplineService disciplineService;

    @MockBean
    private TagService tagService;

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question();
        question.setId(1L);
        question.setStatement("What is the capital of France?");

        Discipline discipline = new Discipline();
        discipline.setName("Geography");
        question.setDiscipline(discipline);

        Theme theme = new Theme();
        theme.setName("European History");
        question.setTheme(theme);
    }

    @Test
    void shouldShowAddQuestionForm() throws Exception {
        setupMocksForFormDependencies();

        mockMvc.perform(get("/questions/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-question-form"))
                .andExpect(model().attributeExists("question"))
                .andExpect(model().attributeExists("themes"))
                .andExpect(model().attributeExists("disciplines"))
                .andExpect(model().attributeExists("tags"));
    }

    @Test
    void shouldAddQuestion() throws Exception {
        mockMvc.perform(post("/questions/add")
                        .flashAttr("question", question))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/questions"));

        verify(questionService, times(1)).addQuestion(any(Question.class));
    }

    @Test
    void shouldShowAllQuestions() throws Exception {
        when(questionService.getAllQuestions()).thenReturn(Collections.singletonList(question));

        mockMvc.perform(get("/questions"))
                .andExpect(status().isOk())
                .andExpect(view().name("question-list"))
                .andExpect(model().attributeExists("questions"));
    }

    @Test
    void shouldDeleteQuestion() throws Exception {
        mockMvc.perform(get("/questions/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/questions"));

        verify(questionService, times(1)).deleteQuestion(1L);
    }

    @Test
    void shouldShowEditQuestionForm() throws Exception {
        when(questionService.getQuestionById(anyLong())).thenReturn(question);
        setupMocksForFormDependencies();

        mockMvc.perform(get("/questions/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-question-form"))
                .andExpect(model().attributeExists("question"))
                .andExpect(model().attributeExists("themes"))
                .andExpect(model().attributeExists("disciplines"))
                .andExpect(model().attributeExists("tags"));
    }

    @Test
    void shouldEditQuestion() throws Exception {
        mockMvc.perform(post("/questions/1/edit")
                        .flashAttr("question", question))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/questions"));

        verify(questionService, times(1)).updateQuestion(any(Question.class));
    }

    @Test
    void shouldShowQuestionDetails() throws Exception {
        when(questionService.getQuestionById(anyLong())).thenReturn(question);

        mockMvc.perform(get("/questions/1/details"))
                .andExpect(status().isOk())
                .andExpect(view().name("question-details"))
                .andExpect(model().attributeExists("question"));
    }

    private void setupMocksForFormDependencies() {
        when(themeService.getAllThemes()).thenReturn(Collections.emptyList());
        when(disciplineService.getAllDisciplines()).thenReturn(Collections.emptyList());
        when(tagService.getAllTags()).thenReturn(Collections.emptyList());
    }
}