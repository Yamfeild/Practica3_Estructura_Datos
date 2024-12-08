package com.example.controls.dao;

import com.example.controls.dao.implement.AdapterDao;
import com.example.controls.tda.list.LinkedList;
import com.example.models.ProyectoEnergia;
import com.example.models.enumerador.TipoEnergia;

public class ProyectoEnergiaDao extends AdapterDao<ProyectoEnergia> {

    private ProyectoEnergia proyectoEnergia;
    private LinkedList listAll;

    public ProyectoEnergiaDao() {
        super(ProyectoEnergia.class);
    }

    public ProyectoEnergia getProyectoEnergia() {
        if (proyectoEnergia == null) {
            proyectoEnergia = new ProyectoEnergia();
        }
        return this.proyectoEnergia;
    }

    public void setProyectoEnergia(ProyectoEnergia proyectoEnergia) {
        this.proyectoEnergia = proyectoEnergia;
    }

    public LinkedList getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        proyectoEnergia.setId(id);
        this.persist(this.proyectoEnergia);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(getProyectoEnergia(), getProyectoEnergia().getId() - 1);
        this.listAll = listAll();
        return true;
    }

    public TipoEnergia getTipoEnergia(String tipo) {
        return TipoEnergia.valueOf(tipo);
    }

    public TipoEnergia[] getTipos() {
        return TipoEnergia.values();
    }

    public LinkedList<ProyectoEnergia> order_QuickSort(String attribute, Integer type) throws Exception {
        if (listAll != null) {
            listAll = listAll.order(attribute, type);
        }
        return listAll;
    }

    public LinkedList<ProyectoEnergia> order_MergeSort(String attribute, Integer type) throws Exception {
        if (listAll != null) {
            listAll = listAll.mergeSort(type).order(attribute, type);
        }
        return listAll;
    }

    public LinkedList<ProyectoEnergia> order_ShellSort(String attribute, Integer type) throws Exception {
        if (listAll != null) {
            listAll = listAll.shellSort(type).order(attribute, type);
        }
        return listAll;
    }

    public LinkedList<ProyectoEnergia> buscaLinealNombreProyecto(String texto) {
        LinkedList<ProyectoEnergia> resultados = new LinkedList<>();
        LinkedList<ProyectoEnergia> lista = listAll();

        if (!lista.isEmpty()) {
            ProyectoEnergia[] proyectos = lista.toArray();
            for (ProyectoEnergia proyecto : proyectos) {
                if (proyecto.getNombreProyecto().toLowerCase().startsWith(texto.toLowerCase())) {
                    resultados.add(proyecto);
                }
            }
        }
        return resultados;
    }

    public ProyectoEnergia buscaBinariaCodigoProyect(Integer codigoProyecto) throws Exception {
        if (codigoProyecto == null) {
            throw new IllegalArgumentException("El valor de inversión total no puede ser nulo.");
        }

        LinkedList<ProyectoEnergia> lista = getListAll();
        if (lista == null || lista.isEmpty()) {
            throw new Exception("La lista de proyectos está vacía. No se puede realizar la búsqueda binaria.");
        }

        LinkedList<ProyectoEnergia> sortList_order = order_QuickSort("codigoProyecto", 0); // Orden ascendente

        ProyectoEnergia[] proyectos = sortList_order.toArray();
        int inicio = 0, fin = proyectos.length - 1;

        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            ProyectoEnergia actual = proyectos[medio];

            Integer codigo_Proyecto = actual.getCodigoProyecto();
            if (codigo_Proyecto == null) {
                continue;
            }

            int comparacion = actual.getCodigoProyecto().compareTo(codigoProyecto);
            if (comparacion == 0) {
                return actual;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }

        return null;
    }

}
