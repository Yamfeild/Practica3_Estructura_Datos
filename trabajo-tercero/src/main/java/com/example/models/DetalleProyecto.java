package com.example.models;

public class DetalleProyecto {

    private Integer id;
    private String fechaInicio;
    private String fechaFin;
    private Float capacidadGeneracionDiariaWatts;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Float getCapacidadGeneracionDiariaWatts() {
        return this.capacidadGeneracionDiariaWatts;
    }

    public void setCapacidadGeneracionDiariaWatts(Float capacidadGeneracionDiariaWatts) {
        this.capacidadGeneracionDiariaWatts = capacidadGeneracionDiariaWatts;
    }

}
