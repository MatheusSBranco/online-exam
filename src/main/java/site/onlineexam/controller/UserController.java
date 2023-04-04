package site.onlineexam.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import site.onlineexam.model.User;
import site.onlineexam.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("roleOptions", roleOptions());
        return "user-registration";
    }

    @GetMapping("/list")
    public String showRegistred(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "user-list";
    }

    @PostMapping("/new")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/users/list";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-registration";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user-registration";
        }
        
        userService.updateUser(user);
        return "redirect:/users/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users/list";
    }

    @ModelAttribute("roleOptions")
    private List<String> roleOptions(){
        return Arrays.asList("STUDENT", "TEACHER");
    }

}
