package edu.egg.libreria.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;


import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "rol", indexes = { @Index(name = "idx_nombre", columnList = "nombre") })
public class Rol {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "rol_id")
    private Integer id;

    @Column(name = "nombre", length = 30, nullable = false)
    private String nombre;

    public Rol() {
    }

    public Rol(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Rol [id=" + id + ", nombre=" + nombre + "]";
    }

}
