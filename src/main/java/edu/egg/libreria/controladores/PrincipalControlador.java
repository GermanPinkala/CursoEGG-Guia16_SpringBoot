package edu.egg.libreria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrincipalControlador {

    @GetMapping
    public ModelAndView getIndex() {
        return new ModelAndView("home");
    }
}