package com.example.controls.dao;

import com.example.controls.dao.implement.AdapterDao;
import com.example.controls.tda.list.LinkedList;
import com.example.models.Persona;
import com.example.models.enumerador.TipoEnergia;

public class PersonaDao extends AdapterDao<Persona> {

    private Persona persona;
    private LinkedList listAll;

    public PersonaDao() {
        super(Persona.class);
    }

    public Persona getPersona() {
        if (persona == null) {
            persona = new Persona();
        }
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public LinkedList getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        persona.setId(id);
        this.persist(this.persona);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(getPersona(), getPersona().getId() - 1);
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
