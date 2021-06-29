package com.sofka.DemoRESTful.dto;

public class EmpleadoDTO {
    private String id;
    private String nombre;
    private String rol;

    public EmpleadoDTO() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
}