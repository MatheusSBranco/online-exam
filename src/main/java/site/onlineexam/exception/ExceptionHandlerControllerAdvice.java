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

    private static final String ERROR_VIEW = "error";
    private static final String ERROR_MSG_ATTRIBUTE = "errorMsg";

    /**
     * Handle HttpServerErrorException and return an error view with a specific message.
     */
    @ExceptionHandler(HttpServerErrorException.class)
    public String handleServerError(Model model, HttpServerErrorException e) {
        addErrorMessage(model, "Server Error: " + e.getStatusCode());
        return ERROR_VIEW;
    }

    /**
     * Handle HttpClientErrorException and return an error view with a specific message.
     */
    @ExceptionHandler(HttpClientErrorException.class)
    public String handleClientError(Model model, HttpClientErrorException e) {
        addErrorMessage(model, "Client Error: " + e.getStatusCode());
        return ERROR_VIEW;
    }

    /**
     * Handle IllegalArgumentException and return an error view with the exception message.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(Model model, IllegalArgumentException e) {
        addErrorMessage(model, e.getMessage());
        return ERROR_VIEW;
    }

    /**
     * Handle UserException and return an error view with the exception message and additional data.
     */
    @ExceptionHandler(UserException.class)
    public String handleUserException(Model model, UserException e) {
        addErrorMessage(model, e.getMessage());
        model.addAttribute("additionalData", e.getAdditionalInfo());
        return ERROR_VIEW;
    }

    /**
     * Handle TemplateInputException and return an error view with a specific message.
     */
    @ExceptionHandler(TemplateInputException.class)
    public ModelAndView handleTemplateInputException(TemplateInputException ex) {
        ModelAndView modelAndView = new ModelAndView(ERROR_VIEW);
        modelAndView.addObject(ERROR_MSG_ATTRIBUTE, "An error occurred while processing the template: " + ex.getMessage());
        return modelAndView;
    }

    /**
     * Handle generic Exception and return an error view with the exception message.
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception e) {
        addErrorMessage(model, e.getMessage());
        return ERROR_VIEW;
    }

    /**
     * Utility method to add an error message to the model.
     */
    private void addErrorMessage(Model model, String message) {
        model.addAttribute(ERROR_MSG_ATTRIBUTE, message);
    }
}