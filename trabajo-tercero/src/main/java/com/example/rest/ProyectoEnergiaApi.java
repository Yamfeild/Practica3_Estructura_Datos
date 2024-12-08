package com.example.rest;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.controls.dao.services.ProyectoEnergiaServices;
import com.example.controls.tda.list.LinkedList;
import com.example.models.ProyectoEnergia;
import com.google.gson.Gson;

@Path("proyecto")
public class ProyectoEnergiaApi {

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        HashMap map = new HashMap<>();
        ProyectoEnergiaServices ps = new ProyectoEnergiaServices();
        map.put("msg", "Ok");
        map.put("data", ps.listAll().toArray());
        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[]{});
        }
        return Response.ok(map).build();
    }

    @Path("/listType")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType() {
        HashMap map = new HashMap<>();
        ProyectoEnergiaServices ps = new ProyectoEnergiaServices();
        map.put("msg", "Ok");
        map.put("data", ps.getTipos());
        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[]{});
        }
        return Response.ok(map).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") Integer id) {
        HashMap map = new HashMap<>();
        ProyectoEnergiaServices ps = new ProyectoEnergiaServices();
        try {
            ps.setProyectoEnergia(ps.get(id));
        } catch (Exception e) {
            // TODO: handle exception
        }
        map.put("msg", "Ok");
        map.put("data", ps.getProyectoEnergia());
        if (ps.getProyectoEnergia().getId() == null) {
            map.put("data", "No existe la persona con ese identificador");
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }

        return Response.ok(map).build();
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap map) {
        HashMap res = new HashMap<>();
        Gson g = new Gson();
        String a = g.toJson(map);
        System.out.println("**********" + a);
        try {
            ProyectoEnergiaServices ps = new ProyectoEnergiaServices();
            ps.getProyectoEnergia().setNombreProyecto(map.get(("nombreProyecto")).toString());
            ps.getProyectoEnergia().setinversionTotal(Float.parseFloat(map.get(("inversionTotal")).toString()));
            ps.getProyectoEnergia().setCodigoProyecto(Integer.parseInt(map.get(("codigoProyecto")).toString()));
            ps.getProyectoEnergia().settiempoVidaAnios(Integer.parseInt(map.get(("TiempoVidaAnios")).toString()));
            ps.getProyectoEnergia().setTipo(ps.getTipoEnergia(map.get("tipo").toString()));

            ps.save();
            res.put("msf", "OK");
            res.put("msg", "ProyectoEnergia registrada correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en sav data " + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap map) {
        HashMap res = new HashMap<>();
        try {
            ProyectoEnergiaServices ps = new ProyectoEnergiaServices();
            ps.setProyectoEnergia(ps.get(Integer.parseInt(map.get("id").toString())));
            ps.getProyectoEnergia().setNombreProyecto(map.get(("nombreProyecto")).toString());
            ps.getProyectoEnergia().setinversionTotal(Float.parseFloat(map.get(("inversionTotal")).toString()));
            ps.getProyectoEnergia().setCodigoProyecto(Integer.parseInt(map.get(("codigoProyecto")).toString()));
            ps.getProyectoEnergia().settiempoVidaAnios(Integer.parseInt(map.get(("TiempoVidaAnios")).toString()));
            ps.getProyectoEnergia().setTipo(ps.getTipoEnergia(map.get("tipo").toString()));

            //ps.getProyectoEnergia().setIdentificacion(map.get("identificacion").toString());
            ps.update();
            res.put("msf", "OK");
            res.put("msg", "ProyectoEnergia registrada correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en sav data " + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/order/{algorimo_sort}/{atributo_nom}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response order_Algoritmo(
            @PathParam("algorimo_sort") String algorithm,
            @PathParam("atributo_nom") String attribute,
            @PathParam("type") Integer type) {
        HashMap<String, Object> map = new HashMap<>();
        ProyectoEnergiaServices ps = new ProyectoEnergiaServices();

        LinkedList<ProyectoEnergia> proyectoList = ps.listAll();

        if (proyectoList.isEmpty()) {
            map.put("msg", "Error");
            map.put("data", "No hay datos para ordenar.");
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }

        try {

            LinkedList<ProyectoEnergia> sortedList = null;

            switch (algorithm.toLowerCase()) {
                case "quick":
                    sortedList = ps.order_QuickSort(attribute, type);
                    break;
                case "merge":
                    sortedList = ps.order_MergeSort(attribute, type);
                    break;
                case "shell":
                    sortedList = ps.order_ShellSort(attribute, type);
                    break;
                default:
                    map.put("msg", "Error");
                    map.put("data", "Algoritmo no válido. Usa 'quick', 'merge' o 'shell'.");
                    return Response.status(Status.BAD_REQUEST).entity(map).build();
            }

            map.put("msg", "OK");
            map.put("data", sortedList.toArray());
            return Response.ok(map).build();

        } catch (Exception e) {
            map.put("msg", "Error");
            map.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @Path("/search/{nombreProyecto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorNombre(@PathParam("nombreProyecto") String nombreProyecto) {
        HashMap<String, Object> map = new HashMap<>();
        ProyectoEnergiaServices ps = new ProyectoEnergiaServices();

        LinkedList<ProyectoEnergia> resultados = ps.buscaLinealNombreProyecto(nombreProyecto);

        if (resultados.isEmpty()) {
            map.put("msg", "Error");
            map.put("data", "No se encontraron proyectos con el nombre proporcionado.");
            return Response.status(Response.Status.NOT_FOUND).entity(map).build();
        }

        map.put("msg", "OK");
        map.put("data", resultados.toArray());
        return Response.ok(map).build();

    }

    @Path("/search/codigo/{codigoProyecto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCodigo(@PathParam("codigoProyecto") Integer codigoProyecto) {
        HashMap<String, Object> map = new HashMap<>();
        ProyectoEnergiaServices ps = new ProyectoEnergiaServices();

        try {

            ProyectoEnergia resultado = ps.buscaBinariaCodigoProyect(codigoProyecto);

            if (resultado == null) {
                map.put("msg", "Error");
                map.put("data", "No se encontró un proyecto con el codigo proporcionado.");
                return Response.status(Response.Status.NOT_FOUND).entity(map).build();
            }

            map.put("msg", "OK");
            map.put("data", resultado);
            return Response.ok(map).build();

        } catch (Exception e) {
            map.put("msg", "Error");
            map.put("data", "Ocurrió un error al realizar la búsqueda: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }
}
