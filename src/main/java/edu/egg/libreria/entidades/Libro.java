package edu.egg.libreria.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "libro", indexes = {@Index(name = "idx_titulo", columnList = "titulo")})
@SQLDelete(sql = "UPDATE libro SET alta = false WHERE libro_id = ?")
@Where(clause = "alta = true")
public class Libro {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "libro_id")
    private Integer id;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name="titulo", length=50, nullable=false)
    private String titulo;

    @Column(name="anio", columnDefinition="YEAR", nullable=false)
    private Integer anio;

    @Column(name="ejemplares", nullable=false)
    private Integer ejemplares;

    @Column(name="ejemplares_prestados", nullable=false)
    private Integer ejemplaresPrestados;

    @Column(name="ejemplares_restantes", nullable=false)
    private Integer ejemplaresRestantes;

    @Column(name="alta", nullable=false)
    private Boolean alta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id", referencedColumnName = "autor_id", nullable = false)
    private Autor autor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "editorial_id", referencedColumnName = "editorial_id", nullable = false)
    private Editorial editorial;
    
    public Libro() {
        this.alta = true;
    }

    public Libro(Integer id, String isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
            Integer ejemplaresRestantes, Boolean alta, Autor autor, Editorial editorial) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.ejemplaresPrestados = ejemplaresPrestados;
        this.ejemplaresRestantes = ejemplaresRestantes;
        this.alta = alta;
        this.autor = autor;
        this.editorial = editorial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Integer getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

    public void setEjemplaresPrestados(Integer ejemplaresPrestados) {
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

    public Integer getEjemplaresRestantes() {
        return ejemplaresRestantes;
    }

    public void setEjemplaresRestantes(Integer ejemplaresRestantes) {
        this.ejemplaresRestantes = ejemplaresRestantes;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "Libro [alta=" + alta + ", anio=" + anio + ", autor=" + autor + ", editorial=" + editorial
                + ", ejemplares=" + ejemplares + ", ejemplaresPrestados=" + ejemplaresPrestados
                + ", ejemplaresRestantes=" + ejemplaresRestantes + ", id=" + id + ", isbn=" + isbn + ", titulo="
                + titulo + "]";
    }

    
}
