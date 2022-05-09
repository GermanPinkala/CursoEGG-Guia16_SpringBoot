package edu.egg.libreria.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.egg.libreria.entidades.Editorial;
import edu.egg.libreria.repositorios.EditorialRepositorio;

@Service
public class EditorialServicio {
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crear(Editorial editorialDto) {
        if (editorialRepositorio.existsByNombre(editorialDto.getNombre()))
            throw new IllegalArgumentException("El nombre de la editorial ya existe.");
        Editorial editorial = new Editorial();
        editorial.setNombre(editorialDto.getNombre());
        editorialRepositorio.save(editorial);
    }

    @Transactional
    public void actualizar(Editorial editorialDto) {
        Editorial editorial = editorialRepositorio.findById(editorialDto.getId()).get();
        editorial.setNombre(editorialDto.getNombre());
        editorialRepositorio.save(editorial);
    }

    @Transactional(readOnly = true)
    public Editorial obtenerPorId(Integer id) {
        return editorialRepositorio.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Editorial> obtenerTodos() {
        return editorialRepositorio.findAll();
    }

    @Transactional
    public void eliminarPorId(Integer id) throws Exception {
        if(editorialRepositorio.referenciasEnLibro(id)>0){
            throw new Exception("No se puede eliminar porque hay registros asociados.");
        }
        editorialRepositorio.deleteById(id);
    }

}
