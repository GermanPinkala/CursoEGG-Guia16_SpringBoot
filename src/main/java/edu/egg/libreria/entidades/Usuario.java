package edu.egg.libreria.entidades;

import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "usuario", indexes = {@Index(name = "idx_user_email", columnList = "email")})
@SQLDelete(sql = "UPDATE usuario SET alta = false WHERE usuario_id = ?")
public class Usuario {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "usuario_id")
    private Integer id;

    @Column(name = "email", length = 60, unique = true, nullable = false)
    private String email;

    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;

    @Column(name = "alta", nullable = false)
    private boolean alta;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "rol", referencedColumnName = "rol_id", nullable = false)
    private Rol rol;

    public Usuario() {
        this.alta = true;
    }

    public Usuario(Integer id, String email, String contrasenia, boolean alta, Rol rol) {
        this.id = id;
        this.email = email;
        this.contrasenia = contrasenia;
        this.alta = alta;
        this.rol = rol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    @Override
    public String toString() {
        return "Usuario [alta=" + alta + ", contrasenia=" + contrasenia + ", email=" + email + ", id=" + id + "]";
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
