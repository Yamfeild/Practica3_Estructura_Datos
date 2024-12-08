package com.example.controls.dao;

import com.example.controls.dao.implement.AdapterDao;
import com.example.controls.tda.list.LinkedList;
import com.example.models.DetalleProyecto;
import com.example.models.enumerador.TipoEnergia;

public class DetalleProyectoDao extends AdapterDao<DetalleProyecto> {

    private DetalleProyecto detalleProyecto;
    private LinkedList listAll;

    public DetalleProyectoDao() {
        super(DetalleProyecto.class);
    }

    public DetalleProyecto getDetalleProyecto() {
        if (detalleProyecto == null) {
            detalleProyecto = new DetalleProyecto();
        }
        return this.detalleProyecto;
    }

    public void setDetalleProyecto(DetalleProyecto detalleProyecto) {
        this.detalleProyecto = detalleProyecto;
    }

    public LinkedList getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        detalleProyecto.setId(id);
        this.persist(this.detalleProyecto);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(getDetalleProyecto(), getDetalleProyecto().getId() - 1);
        this.listAll = listAll();
        return true;
    }

    public TipoEnergia getTipoEnergia(String tipo) {
        return TipoEnergia.valueOf(tipo);
    }

    public TipoEnergia[] getTipos() {
        return TipoEnergia.values();
    }

}
