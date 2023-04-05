package site.onlineexam.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.exceptions.TemplateInputException;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
 
    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception e){
        model.addAttribute("errorMsg", e.getMessage());
        return "error";
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public String handleServerError(Model model, HttpServerErrorException e) {
        model.addAttribute("errorMsg", "Server Error: " + e.getStatusCode());
        return "error";
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public String handleClientError(Model model, HttpClientErrorException e) {
        model.addAttribute("errorMsg", "Client Error: " + e.getStatusCode());
        return "error";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(Model model, IllegalArgumentException e) {
        model.addAttribute("errorMsg", e.getMessage());
        return "error";
    }

    @ExceptionHandler(UserException.class)
    public String handleUserException(Model model, UserException e) {
        model.addAttribute("errorMsg", e.getMessage());
        model.addAttribute("data", e.getData());
        return "error";
    }

    @ExceptionHandler(TemplateInputException.class)
    public ModelAndView handleTemplateInputException(TemplateInputException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", "An error occurred while processing the template: " + ex.getMessage());
        return modelAndView;
    }
}
