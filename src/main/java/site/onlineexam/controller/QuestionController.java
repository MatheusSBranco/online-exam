package site.onlineexam.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import site.onlineexam.model.Question;
import site.onlineexam.service.DisciplineService;
import site.onlineexam.service.QuestionService;
import site.onlineexam.service.TagService;
import site.onlineexam.service.ThemeService;

@Controller
@RequestMapping("/questions")
public class QuestionController {
    
    private final QuestionService questionService;
    private final ThemeService themeService;
    private final DisciplineService disciplineService;
    private final TagService tagService;

    public QuestionController(QuestionService questionService, ThemeService themeService, DisciplineService disciplineService, TagService tagService) {
        this.questionService = questionService;
        this.themeService = themeService;
        this.disciplineService = disciplineService;
        this.tagService = tagService;
    }

    @GetMapping("/add")
    public String showAddQuestionForm(Model model) {
        model.addAttribute("question", new Question());
        model.addAttribute("themes", themeService.getAllThemes());
        model.addAttribute("disciplines", disciplineService.getAllDisciplines());
        model.addAttribute("tags", tagService.getAllTags());
        return "add-question-form";
    }

    @PostMapping("/add")
    public String addQuestion(@ModelAttribute("question") Question question) {
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
            model.addAttribute("question", question);
            model.addAttribute("themes", themeService.getAllThemes());
            model.addAttribute("disciplines", disciplineService.getAllDisciplines());
            model.addAttribute("tags", tagService.getAllTags());
            return "add-question-form";
    }

    @PostMapping("/{id}/edit")
    public String editQuestion(@ModelAttribute("question") Question question, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "question-form";
        }
        
        questionService.updateQuestion(question);
        return "redirect:/questions";
    }

    @GetMapping("/{id}/details")
    public String showQuestionDetails(@PathVariable Long id, Model model) {
        Question question = questionService.getQuestionById(id);
        model.addAttribute("question", question);
        return "question-details";
    }
}
