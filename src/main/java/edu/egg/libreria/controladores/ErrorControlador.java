package edu.egg.libreria.controladores;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static javax.servlet.RequestDispatcher.ERROR_STATUS_CODE;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorControlador implements ErrorController {
    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("fragmentos/error");
        Integer estado = (int) request.getAttribute(ERROR_STATUS_CODE);
        String mensaje;

        switch (estado) {
            case 403:
                mensaje = "No tiene suficientes permisos para acceder al recurso solicitado";
                break;
            case 404:
                mensaje = "No se encontró el recurso solicitado";
                break;
            case 500:
                mensaje = "Error de servidor interno";
                break;
            default:
                mensaje = "Error inesperado";
        }

        mav.addObject("mensaje", mensaje);
        mav.addObject("estado", estado);
        return mav;
    }
}
