package site.onlineexam.controller;

import java.util.Arrays;
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

    @GetMapping
    public String listQuestions(Model model){
        List<Question> questions = questionService.getAllQuestions();
        model.addAttribute("questions", questions);
        return "question-list";
    }

    @GetMapping("/new")
    public String newQuestion(Model model) {
        model.addAttribute("question", new Question());
        model.addAttribute("themes", themeService.getAllThemes());
        model.addAttribute("disciplines", disciplineService.getAllDisciplines());
        model.addAttribute("tags", tagService.getAllTags());
        model.addAttribute("typeOptions", typeOptions());
        return "question-form";
    }

    @PostMapping("/save")
    public String saveQuestion(@ModelAttribute("question") Question question) {
        questionService.saveQuestion(question);
        return "redirect:/questions";
    }

    @GetMapping("/edit/{id}")
    public String editQuestion(@PathVariable("id") Long id, Model model) {
        Question question = questionService.getQuestionById(id);
        model.addAttribute("question", question);
        model.addAttribute("themes", themeService.getAllThemes());
        model.addAttribute("disciplines", disciplineService.getAllDisciplines());
        model.addAttribute("tags", tagService.getAllTags());
        model.addAttribute("typeOptions", typeOptions());
        return "question-form";
    }

    @PostMapping("/update")
    public String updateQuestion(@ModelAttribute("question") Question question, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "question-form";
        }
        
        questionService.updateQuestion(question);
        return "redirect:/questions";
    }

    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable("id") Long id) {
        questionService.deleteQuestion(id);
        return "redirect:/questions";
    }

    @ModelAttribute("typeOptions")
    private List<String> typeOptions(){
        return Arrays.asList("OPEN_ENDED","MULTIPLE_CHOICE_SINGLE_ANSWER","MULTIPLE_CHOICE_MULTIPLE_ANSWERS","TRUE_FALSE");
    }
    
}
