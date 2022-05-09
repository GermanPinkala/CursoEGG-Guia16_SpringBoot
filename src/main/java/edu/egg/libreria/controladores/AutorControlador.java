package edu.egg.libreria.controladores;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import edu.egg.libreria.entidades.Autor;
import edu.egg.libreria.servicios.AutorServicio;

@Controller
@RequestMapping("/autores")
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping
    public ModelAndView obtenerAutores(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("autor/index.html");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null){
            if(inputFlashMap.containsKey("exito")){
                mav.addObject("exito", inputFlashMap.get("exito"));
            }
            if(inputFlashMap.containsKey("error")){
                mav.addObject("error", inputFlashMap.get("error"));
            }
        }
        mav.addObject("autores", autorServicio.obtenerTodos());
        return mav;
    }

    @GetMapping("/formulario")
    public ModelAndView obtenerFormulario(HttpServletRequest request) {
        
        ModelAndView mav = new ModelAndView("autor/formulario.html");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            mav.addObject("autor", inputFlashMap.get("autor"));
            mav.addObject("exception", inputFlashMap.get("exception"));
        } else {
            mav.addObject("autor", new Autor());
        }
        mav.addObject("action", "crear");
        return mav;
    }

    @PostMapping("/crear")
    public RedirectView crear(Autor autorDto, RedirectAttributes atributos) {
        RedirectView redireccion = new RedirectView("/autores");
        
        try {
            autorServicio.crear(autorDto);
            atributos.addFlashAttribute("exito", "El autor se ha almacenado.");
        } catch (IllegalArgumentException e) {
            atributos.addFlashAttribute("autor", autorDto);
            atributos.addFlashAttribute("error", e.getMessage());
            redireccion.setUrl("/autores/formulario");
        }

        return redireccion;
    }

    @GetMapping("/formulario/{id}")
    public ModelAndView obtenerFormularioActualizar(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("/autor/formulario");
        mav.addObject("autor", autorServicio.obtenerPorId(id));
        mav.addObject("action", "actualizar");
        return mav;
    }

    @PostMapping("/actualizar")
    public RedirectView atualizar(Autor autorDto, RedirectAttributes atributos) {
        RedirectView redireccion = new RedirectView("/autores");
        autorServicio.actualizar(autorDto);
        atributos.addFlashAttribute("exito", "Autor modificado.");
        return redireccion;
    }

    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Integer id, RedirectAttributes atributos) {
        RedirectView redireccion = new RedirectView("/autores");
        try {       
            autorServicio.eliminarPorId(id);
            atributos.addFlashAttribute("exito", "Autor eliminado.");
        } catch (Exception e) {
            atributos.addFlashAttribute("error", e.getMessage());
        }
        return redireccion;
    }
}
