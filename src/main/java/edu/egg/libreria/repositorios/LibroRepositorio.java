package edu.egg.libreria.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.egg.libreria.entidades.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Integer> {
    @Query("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo")
    Optional<Libro> searchByNameParam(@Param("titulo") String titulo);

    @Query("SELECT l FROM Libro l WHERE l.titulo LIKE ?1")
    Optional<Libro> searchByName(String titulo);

    @Query(value = "SELECT * FROM libro WHERE titulo = ?1", nativeQuery = true)
    Optional<Libro> searchByNameNativeQuery(String titulo);

    Optional<Libro> findByTitulo(String titulo);

    boolean existsByTitulo(String titulo);

    boolean existsByIsbn(String isbn);

    @Query(value = "SELECT * FROM libro WHERE isbn = ?1", nativeQuery = true)
    Optional<Libro> searchByIsbnNativeQuery(String isbn);
}
