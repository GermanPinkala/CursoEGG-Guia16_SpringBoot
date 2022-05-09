package edu.egg.libreria.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.egg.libreria.entidades.Libro;
import edu.egg.libreria.repositorios.LibroRepositorio;

@Service
public class LibroServicio {
    @Autowired
    private LibroRepositorio libroRepositorio;

    @Transactional
    public void crear(Libro libroDto) {

        if (libroRepositorio.existsByTitulo(libroDto.getTitulo())){
            throw new IllegalArgumentException("Ya existe un libro con este titulo"); 
        }
        if  (libroRepositorio.existsByIsbn(libroDto.getIsbn())){
            throw new IllegalArgumentException("Ya existe un libro con este isbn");
        }

        Libro libro = new Libro();
        
        libro.setIsbn(libroDto.getIsbn());
        libro.setTitulo(libroDto.getTitulo());
        libro.setAnio(libroDto.getAnio());
        libro.setEjemplares(libroDto.getEjemplares());
        libro.setEjemplaresPrestados(libroDto.getEjemplaresPrestados());
        libro.setEjemplaresRestantes(libroDto.getEjemplaresRestantes());
        libro.setAutor(libroDto.getAutor());
        libro.setEditorial(libroDto.getEditorial());

        libroRepositorio.save(libro);
    }

    @Transactional
    public void actualizar(Libro libroDto) {
        Libro libro = libroRepositorio.findById(libroDto.getId()).get();
        
        libro.setIsbn(libroDto.getIsbn());
        libro.setTitulo(libroDto.getTitulo());
        libro.setAnio(libroDto.getAnio());
        libro.setEjemplares(libroDto.getEjemplares());
        libro.setEjemplaresPrestados(libroDto.getEjemplaresPrestados());
        libro.setEjemplaresRestantes(libroDto.getEjemplaresRestantes());
        libro.setAutor(libroDto.getAutor());
        libro.setEditorial(libroDto.getEditorial());
        
        libroRepositorio.save(libro);
    }

    @Transactional(readOnly = true)
    public Libro obtenerPorId(Integer id) {
        return libroRepositorio.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Libro> obtenerTodos() {
        return libroRepositorio.findAll();
    }

    @Transactional
    public void eliminarPorId(Integer id) {
        
        libroRepositorio.deleteById(id);
    }
}
