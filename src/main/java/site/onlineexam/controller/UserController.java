package site.onlineexam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import site.onlineexam.model.User;
import site.onlineexam.service.UserService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new")
    public String showRegistrationForm(Model model) {
        populateUserModel(model, new User());
        return "user-registration";
    }

    @PostMapping("/new")
    public String registerUser(@ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            populateUserModel(model, user);
            return "user-registration";
        }
        userService.createUser(user);
        return "redirect:/users/list";
    }

    @GetMapping("/list")
    public String showRegisteredUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user-list";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        populateUserModel(model, user);
        return "user-registration";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            populateUserModel(model, user);
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
    private List<String> roleOptions() {
        return Arrays.asList("STUDENT", "TEACHER");
    }

    private void populateUserModel(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("roleOptions", roleOptions());
    }
}