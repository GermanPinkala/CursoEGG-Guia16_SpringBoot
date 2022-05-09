package edu.egg.libreria.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.egg.libreria.entidades.Autor;
import edu.egg.libreria.repositorios.AutorRepositorio;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional
    public void crear(Autor autorDto) {

        if (autorRepositorio.existsByNombre(autorDto.getNombre())){
            throw new IllegalArgumentException("Ya existe un autor con ese nombre");
        }
        Autor autor = new Autor();
        autor.setNombre(autorDto.getNombre());
        autorRepositorio.save(autor);
    }

    

    @Transactional
    public void actualizar(Autor autorDto) {
        Autor autor = autorRepositorio.findById(autorDto.getId()).get();
        autor.setNombre(autorDto.getNombre());
        autorRepositorio.save(autor);
    }

    @Transactional(readOnly = true)
    public Autor obtenerPorId(Integer id) {
        return autorRepositorio.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Autor> obtenerTodos() {
        return autorRepositorio.findAll();
    }

    @Transactional
    public void eliminarPorId(Integer id) throws Exception {
        if(autorRepositorio.referenciasEnLibro(id)>0){
            throw new Exception("No se puede eliminar porque hay registros asociados.");
        }
        autorRepositorio.deleteById(id);
    }
}
