package pe.edu.upc.demosi.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(
        name = "roles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"usuario_id", "rol"})
        }
)
public class Rol implements Serializable {//serializable es una interfaz que permite convertir un objeto en una secuencia de bytes para
                                         // poder almacenarlo o transmitirlo a través de la red. Esto es útil para guardar el estado de un objeto y poder recuperarlo más tarde.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;
    @Column(name = "nombreRol", length = 45, nullable = false)
    private String nombreRol;
    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY) //fetch = FetchType.LAZY significa que la relación se cargará de manera perezosa, es decir, solo cuando se acceda a ella por primera vez. Esto puede mejorar el rendimiento al evitar cargar datos innecesarios.
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Rol() {
    }

    public Rol(Long idRol, String nombreRol, String descripcion) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.descripcion = descripcion;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
