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

import com.example.controls.dao.services.InversionistaServices;
import com.example.controls.dao.services.ProyectoEnergiaServices;
import com.google.gson.Gson;

@Path("inversionista")
public class InversionistaApi {

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        HashMap map = new HashMap<>();
        InversionistaServices ps = new InversionistaServices();
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
        InversionistaServices ps = new InversionistaServices();
        try {
            ps.setInversionista(ps.get(id));
        } catch (Exception e) {
            // TODO: handle exception
        }
        map.put("msg", "Ok");
        map.put("data", ps.getInversionista());
        if (ps.getInversionista().getId() == null) {
            map.put("data", "No existe la inversionista con ese identificador");
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
            InversionistaServices ps = new InversionistaServices();
            ps.getInversionista().setApellido(map.get(("apellido")).toString());
            ps.getInversionista().setNombre(map.get(("nombre")).toString());
            ps.getInversionista().setTelefono(map.get(("telefono")).toString());
            ps.getInversionista().setIdentificacion(map.get("identificacion").toString());
            ps.getInversionista().setMontoInvertido(Float.parseFloat(map.get(("montoInvertido")).toString()));

            ps.save();
            res.put("msf", "OK");
            res.put("msg", "Inversionista registrada correctamente");
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
            InversionistaServices ps = new InversionistaServices();
            ps.setInversionista(ps.get(Integer.parseInt(map.get("id").toString())));
            ps.getInversionista().setApellido(map.get(("apellido")).toString());
            ps.getInversionista().setNombre(map.get(("nombre")).toString());
            ps.getInversionista().setTelefono(map.get(("telefono")).toString());
            ps.getInversionista().setMontoInvertido(Float.parseFloat(map.get(("montoInvertido")).toString()));

            //ps.getInversionista().setIdentificacion(map.get("identificacion").toString());
            ps.update();
            res.put("msf", "OK");
            res.put("msg", "Inversionista registrada correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en sav data " + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}
