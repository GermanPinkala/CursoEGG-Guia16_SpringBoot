package edu.egg.libreria.controladores;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import edu.egg.libreria.entidades.Editorial;

import edu.egg.libreria.servicios.EditorialServicio;

@Controller
@RequestMapping("/editoriales")
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ModelAndView obtenerEditoriales(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("editorial/index.html");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null)
            mav.addObject("exito", inputFlashMap.get("exito"));
        mav.addObject("editoriales", editorialServicio.obtenerTodos());
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/formulario")
    public ModelAndView obtenerFormulario(HttpServletRequest request) {
        /*ModelAndView mav = new ModelAndView("editorial/formulario.html");
        mav.addObject("editorial", new Editorial());
        mav.addObject("action", "crear");*/
        ModelAndView mav = new ModelAndView("editorial/formulario.html");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            mav.addObject("editorial", inputFlashMap.get("editorial"));
            mav.addObject("exception", inputFlashMap.get("exception"));
        } else {
            mav.addObject("editorial", new Editorial());
        }
        mav.addObject("action", "crear");
        return mav;
        
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public RedirectView crear(Editorial editorialDto, RedirectAttributes atributos) {
        RedirectView redireccion = new RedirectView("/editoriales");
        
        try {
            editorialServicio.crear(editorialDto);
            atributos.addFlashAttribute("exito", "La editorial se ha almacenado.");
        } catch (IllegalArgumentException e) {
            atributos.addFlashAttribute("editorial", editorialDto);
            atributos.addFlashAttribute("error", e.getMessage());
            redireccion.setUrl("/editoriales/formulario");
        }
            
        return redireccion;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/formulario/{id}")
    public ModelAndView obtenerFormularioActualizar(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("/editorial/formulario");
        mav.addObject("editorial", editorialServicio.obtenerPorId(id));
        mav.addObject("action", "actualizar");
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actualizar")
    public RedirectView atualizar(Editorial editorialDto, RedirectAttributes atributos) {
        RedirectView redireccion = new RedirectView("/editoriales");
        editorialServicio.actualizar(editorialDto);
        atributos.addFlashAttribute("exito", "Editorial modicado con éxito.");
        return redireccion;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Integer id, RedirectAttributes atributos) {
        
        RedirectView redireccion = new RedirectView("/editoriales");

        try {       
            editorialServicio.eliminarPorId(id);
            atributos.addFlashAttribute("exito", "Autor eliminado.");
        } catch (Exception e) {
            atributos.addFlashAttribute("error", e.getMessage());
        }
        return redireccion;
    }
}
