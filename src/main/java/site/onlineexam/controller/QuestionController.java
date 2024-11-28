package site.onlineexam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import site.onlineexam.model.Question;
import site.onlineexam.service.DisciplineService;
import site.onlineexam.service.QuestionService;
import site.onlineexam.service.TagService;
import site.onlineexam.service.ThemeService;

import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final ThemeService themeService;
    private final DisciplineService disciplineService;
    private final TagService tagService;

    @Autowired
    public QuestionController(QuestionService questionService, ThemeService themeService, DisciplineService disciplineService, TagService tagService) {
        this.questionService = questionService;
        this.themeService = themeService;
        this.disciplineService = disciplineService;
        this.tagService = tagService;
    }

    @GetMapping("/add")
    public String showAddQuestionForm(Model model) {
        populateModelWithQuestionDependencies(model, new Question());
        return "add-question-form";
    }

    @PostMapping("/add")
    public String addQuestion(@ModelAttribute("question") Question question, BindingResult result, Model model) {
        if (result.hasErrors()) {
            populateModelWithQuestionDependencies(model, question);
            return "add-question-form";
        }
        questionService.addQuestion(question);
        return "redirect:/questions";
    }

    @GetMapping
    public String showAllQuestions(Model model) {
        List<Question> questions = questionService.getAllQuestions();
        model.addAttribute("questions", questions);
        return "question-list";
    }

    @GetMapping("/{id}/delete")
    public String deleteQuestion(@PathVariable("id") Long questionId) {
        questionService.deleteQuestion(questionId);
        return "redirect:/questions";
    }

    @GetMapping("/{id}/edit")
    public String showEditQuestionForm(@PathVariable("id") Long questionId, Model model) {
        Question question = questionService.getQuestionById(questionId);
        populateModelWithQuestionDependencies(model, question);
        return "add-question-form";
    }

    @PostMapping("/{id}/edit")
    public String editQuestion(@ModelAttribute("question") Question question,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            populateModelWithQuestionDependencies(model, question);
            return "add-question-form";
        }
        questionService.updateQuestion(question);
        return "redirect:/questions";
    }

    @GetMapping("/{id}/details")
    public String showQuestionDetails(@PathVariable("id") Long questionId, Model model) {
        Question question = questionService.getQuestionById(questionId);
        model.addAttribute("question", question);
        return "question-details";
    }

    private void populateModelWithQuestionDependencies(Model model, Question question) {
        model.addAttribute("question", question);
        model.addAttribute("themes", themeService.getAllThemes());
        model.addAttribute("disciplines", disciplineService.getAllDisciplines());
        model.addAttribute("tags", tagService.getAllTags());
    }
}