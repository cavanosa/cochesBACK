package com.concesionario.cochesbackend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(unique = true)
    private String nombre;
    @OneToMany(mappedBy = "marca", fetch = FetchType.EAGER)
    private Set<Modelo> modelos = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(Set<Modelo> modelos) {
        this.modelos = modelos;
    }
}
