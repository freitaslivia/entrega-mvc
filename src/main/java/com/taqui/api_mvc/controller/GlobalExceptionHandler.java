package com.taqui.api_mvc.controller;


import com.taqui.api_mvc.service.ErroNegocioException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ErroNegocioException.class)
    public ModelAndView handleUsuarioNotFoundException(ErroNegocioException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", "Ocorreu um erro inesperado: " + ex.getMessage());
        return modelAndView;
    }
}

