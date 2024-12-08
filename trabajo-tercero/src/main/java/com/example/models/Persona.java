package com.example.models;

import com.example.models.enumerador.TipoEnergia;

public class Persona {

    private Integer id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private TipoEnergia tipo;
    private String identificacion;

    public Persona() {
    }

    public Persona(Integer id, String nombre, String apellido, String direccion, String telefono,
            TipoEnergia tipo, String identificacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipo = tipo;
        this.identificacion = identificacion;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoEnergia getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnergia tipo) {
        this.tipo = tipo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

}
