package com.example.controls.dao.services;

import com.example.controls.dao.DetalleProyectoDao;
import com.example.controls.tda.list.LinkedList;
import com.example.models.DetalleProyecto;
import com.example.models.enumerador.TipoEnergia;

public class DetalleProyectoServices {

    private DetalleProyectoDao obj;

    public DetalleProyectoServices() {
        obj = new DetalleProyectoDao();
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

    public DetalleProyecto getDetalleProyecto() {
        return obj.getDetalleProyecto();
    }

    public void setDetalleProyecto(DetalleProyecto detalleProyecto) {
        obj.setDetalleProyecto(detalleProyecto);
    }

    public TipoEnergia getTipoEnergia(String tipo) {
        return obj.getTipoEnergia(tipo);
    }

    public TipoEnergia[] getTipos() {
        return obj.getTipos();
    }

    public DetalleProyecto get(Integer id) throws Exception {
        return obj.get(id);
    }
}
