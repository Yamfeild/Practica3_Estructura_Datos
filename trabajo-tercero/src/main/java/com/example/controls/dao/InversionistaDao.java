package com.example.controls.dao;

import com.example.controls.dao.implement.AdapterDao;
import com.example.controls.tda.list.LinkedList;
import com.example.models.Inversionista;
import com.example.models.enumerador.TipoEnergia;

public class InversionistaDao extends AdapterDao<Inversionista> {

    private Inversionista inversionista;
    private LinkedList listAll;

    public InversionistaDao() {
        super(Inversionista.class);
    }

    public Inversionista getInversionista() {
        if (inversionista == null) {
            inversionista = new Inversionista();
        }
        return this.inversionista;
    }

    public void setInversionista(Inversionista inversionista) {
        this.inversionista = inversionista;
    }

    public LinkedList getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        inversionista.setId(id);
        this.persist(this.inversionista);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(getInversionista(), getInversionista().getId() - 1);
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
