package edu.egg.libreria.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.egg.libreria.entidades.Editorial;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, Integer> {
    @Query("SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre")
    Optional<Editorial> searchByNameParam(@Param("nombre") String nombre);

    @Query("SELECT e FROM Editorial e WHERE e.nombre LIKE ?1")
    Optional<Editorial> searchByName(String nombre);

    @Query(value = "SELECT * FROM editorial WHERE nombre = ?1", nativeQuery = true)
    Optional<Editorial> searchByNameNativeQuery(String nombre);

    Optional<Editorial> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    @Query(value = "SELECT count(*) FROM libro WHERE editorial_id = ?1 AND alta = 1", nativeQuery = true)
    Integer referenciasEnLibro(Integer id);
}
