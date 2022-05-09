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
@Table(name="editorial", indexes = {@Index(name = "idx_nombre", columnList = "nombre")})
@SQLDelete(sql = "UPDATE editorial SET alta = false WHERE editorial_id = ?")
@Where(clause = "alta = true")
public class Editorial {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="editorial_id")
    private Integer id;

    @Column(name="nombre", length=50, nullable=false)
    private String nombre;
    
    @Column(name="alta", nullable=false)
    private Boolean alta;

    public Editorial() {
        this.alta = true;
    }

    public Editorial(Integer id, String nombre, Boolean alta) {
        this.id = id;
        this.nombre = nombre;
        this.alta = alta;
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

    @Override
    public String toString() {
        return "Editorial [alta=" + alta + ", id=" + id + ", nombre=" + nombre + "]";
    }

    
}
