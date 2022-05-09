package edu.egg.libreria.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.egg.libreria.entidades.Autor;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, Integer> {
    @Query("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre")
    Optional<Autor> searchByNameParam(@Param("nombre") String nombre);

    @Query("SELECT a FROM Autor a WHERE a.nombre LIKE ?1")
    Optional<Autor> searchByName(String nombre);

    @Query(value = "SELECT * FROM autor WHERE nombre = ?1", nativeQuery = true)
    Optional<Autor> searchByNameNativeQuery(String nombre);

    Optional<Autor> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    @Query(value = "SELECT count(*) FROM libro WHERE autor_id = ?1 AND alta = 1", nativeQuery = true)
    Integer referenciasEnLibro(Integer id);
}
