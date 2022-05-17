package edu.egg.libreria.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="autor", indexes = {@Index(name = "idx_nombre", columnList = "nombre")})
@SQLDelete(sql = "UPDATE autor SET alta = false WHERE autor_id = ?")
@Where(clause = "alta = true")
public class Autor {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="autor_id")
    private Integer id;

    @Column(name="nombre", length=50, nullable=false)
    private String nombre;

    @Column(name="alta", nullable=false)
    private Boolean alta;

    @Column(name="imagen")
    private String imagen;
    
    public Autor() {
        this.alta = true;
    }

    public Autor(Integer id, String nombre, Boolean alta, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.alta = alta;
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Autor [alta=" + alta + ", id=" + id + ", nombre=" + nombre + "]";
    }

}
