package com.example.controls.dao.services;

import com.example.controls.dao.InversionistaDao;
import com.example.controls.tda.list.LinkedList;
import com.example.models.Inversionista;
import com.example.models.enumerador.TipoEnergia;

public class InversionistaServices {

    private InversionistaDao obj;

    public InversionistaServices() {
        obj = new InversionistaDao();
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

    public Inversionista getInversionista() {
        return obj.getInversionista();
    }

    public void setInversionista(Inversionista persona) {
        obj.setInversionista(persona);
    }

    public TipoEnergia getTipoEnergia(String tipo) {
        return obj.getTipoEnergia(tipo);
    }

    public TipoEnergia[] getTipos() {
        return obj.getTipos();
    }

    public Inversionista get(Integer id) throws Exception {
        return obj.get(id);
    }
}
