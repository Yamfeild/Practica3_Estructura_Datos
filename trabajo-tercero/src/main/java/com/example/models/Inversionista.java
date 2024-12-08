package com.example.models;

public class Inversionista {

    private Integer id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String identificacion;
    private Float montoInvertido;

    public Float getMontoInvertido() {
        return this.montoInvertido;
    }

    public void setMontoInvertido(Float montoInvertido) {
        this.montoInvertido = montoInvertido;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

}
