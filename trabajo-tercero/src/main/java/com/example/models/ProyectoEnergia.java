package com.example.models;

import com.example.models.enumerador.TipoEnergia;

public class ProyectoEnergia {

    private Integer id;
    private String nombreProyecto;
    private Float inversionTotal;
    private Integer codigoProyecto;
    private Integer tiempoVidaAnios;
    private TipoEnergia tipo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Float getinversionTotal() {
        return inversionTotal;
    }

    public void setinversionTotal(Float inversionTotal) {
        this.inversionTotal = inversionTotal;
    }

    public Integer getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(Integer codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    public Integer gettiempoVidaAnios() {
        return tiempoVidaAnios;
    }

    public void settiempoVidaAnios(Integer tiempoVidaAnios) {
        this.tiempoVidaAnios = tiempoVidaAnios;
    }

    public TipoEnergia getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnergia tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return id + " - " + nombreProyecto + " - " + inversionTotal;
    }

}
