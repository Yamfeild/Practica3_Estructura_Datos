package com.example.controls.dao.services;

import com.example.controls.dao.ProyectoEnergiaDao;
import com.example.controls.tda.list.LinkedList;
import com.example.models.ProyectoEnergia;
import com.example.models.enumerador.TipoEnergia;

public class ProyectoEnergiaServices {

    private ProyectoEnergiaDao obj;

    public ProyectoEnergiaServices() {
        obj = new ProyectoEnergiaDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public LinkedList listAll() {
        return obj.getListAll();
    }

    public ProyectoEnergia getProyectoEnergia() {
        return obj.getProyectoEnergia();
    }

    public void setProyectoEnergia(ProyectoEnergia proyectoEnergia) {
        obj.setProyectoEnergia(proyectoEnergia);
    }

    public TipoEnergia getTipoEnergia(String tipo) {
        return obj.getTipoEnergia(tipo);
    }

    public TipoEnergia[] getTipos() {
        return obj.getTipos();
    }

    public ProyectoEnergia get(Integer id) throws Exception {
        return obj.get(id);
    }
    // Métodos de ordenamiento

    public LinkedList<ProyectoEnergia> order_QuickSort(String attribute, Integer type) throws Exception {
        return obj.order_QuickSort(attribute, type);
    }

    public LinkedList<ProyectoEnergia> order_MergeSort(String attribute, Integer type) throws Exception {
        return obj.order_MergeSort(attribute, type);
    }

    public LinkedList<ProyectoEnergia> order_ShellSort(String attribute, Integer type) throws Exception {
        return obj.order_ShellSort(attribute, type);
    }

    // Métodos de búsqueda
    public LinkedList<ProyectoEnergia> buscaLinealNombreProyecto(String texto) {
        return obj.buscaLinealNombreProyecto(texto);
    }

    public ProyectoEnergia buscaBinariaCodigoProyect(Integer codigoProyecto) throws Exception {
        return obj.buscaBinariaCodigoProyect(codigoProyecto);
    }
}
