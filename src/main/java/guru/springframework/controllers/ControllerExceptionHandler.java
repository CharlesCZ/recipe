package guru.springframework.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

   /* @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(Exception exception){

        log.error("Handling Number Format Exception");
        log.error(exception.getMessage());
        ModelAndView modelAndView=new ModelAndView("400error");
        modelAndView.addObject("exception",exception);

        return modelAndView;
    }*/
}
