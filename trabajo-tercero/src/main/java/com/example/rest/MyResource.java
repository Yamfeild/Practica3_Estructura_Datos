package com.example.rest;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.controls.dao.services.PersonaServices;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
        HashMap mapa = new HashMap<>();
        //PersonaDao pd = new PersonaDao();
        PersonaServices pd = new PersonaServices();
        String aux = "";
        try {
            pd.getPersona().setApellido("Quevedo");
            pd.getPersona().setNombre("pablo");
            pd.save();
            aux = "La lista esta vasia" + pd.listAll().isEmpty();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        mapa.put("msg", "Ok");
        mapa.put("data", "test" + aux);
        return Response.ok(mapa).build();
    }
}
