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

import com.example.controls.dao.services.DetalleProyectoServices;
import com.google.gson.Gson;

@Path("detalleproy")
public class DetalleProyectoApi {

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        HashMap map = new HashMap<>();
        DetalleProyectoServices ps = new DetalleProyectoServices();
        map.put("msg", "Ok");
        map.put("data", ps.listAll().toArray());
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
        DetalleProyectoServices ps = new DetalleProyectoServices();
        try {
            ps.setDetalleProyecto(ps.get(id));
        } catch (Exception e) {
            // TODO: handle exception
        }
        map.put("msg", "Ok");
        map.put("data", ps.getDetalleProyecto());
        if (ps.getDetalleProyecto().getId() == null) {
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
            DetalleProyectoServices ps = new DetalleProyectoServices();
            ps.getDetalleProyecto().setFechaInicio((map.get(("fechaInicio")).toString()));
            ps.getDetalleProyecto().setFechaFin((map.get(("fechaFin")).toString()));
            ps.getDetalleProyecto().setCapacidadGeneracionDiariaWatts(Float.parseFloat(map.get(("capacidadGeneracionDiariaWatts")).toString()));

            ps.save();
            res.put("msf", "OK");
            res.put("msg", "DetalleProyecto registrada correctamente");
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
            DetalleProyectoServices ps = new DetalleProyectoServices();
            ps.setDetalleProyecto(ps.get(Integer.parseInt(map.get("id").toString())));
            ps.getDetalleProyecto().setFechaInicio((map.get(("fechaInicio")).toString()));
            ps.getDetalleProyecto().setFechaFin((map.get(("fechaFin")).toString()));
            ps.getDetalleProyecto().setCapacidadGeneracionDiariaWatts(Float.parseFloat(map.get(("capacidadGeneracionDiariaWatts")).toString()));

            //ps.getDetalleProyecto().setIdentificacion(map.get("identificacion").toString());
            ps.update();
            res.put("msf", "OK");
            res.put("msg", "DetalleProyecto registrada correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en sav data " + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}
