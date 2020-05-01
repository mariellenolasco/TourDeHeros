package com.revature.controller;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;

import com.revature.dao.Repository;
import com.revature.model.Hero;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;
import java.util.UUID;

@Path("/hero")
public class HeroController {
    @POST
    @Path("/newhero")
    @Consumes("application/json")
    public Response addNewHero (Hero hero) {
        if(hero == null)
            return Response
                .status(400)
                .entity("Please add hero details")
                .build();
        if(hero.getName() == null)
            return Response
                    .status(400)
                    .entity("Please enter hero name")
                    .build();
        if(hero.getSuperPower() == null)
            return Response
                    .status(400)
                    .entity("Please enter hero superpower")
                    .build();
        Repository repo = new Repository();
        if (repo.makeNewHero(hero) > 0)
            return Response
                    .status(201)
                    .build();
        else return Response.status(500)
                .entity("Hero was not made")
                .build();
    }
    @GET
    public Response getAllHeros(){
        ObjectMapper mapper = new ObjectMapper();
        Repository repo = new Repository();
        try {
            String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(repo.getAllHeros());
            return Response
                    .status(Response.Status.OK)
                    .entity(response)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .build();
    }
        @GET
        @Path("/{id}")
        public Response getHero(@PathParam("id") UUID id){
            ObjectMapper mapper = new ObjectMapper();
            Repository repo = new Repository();
            try {
                String response = mapper
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(repo.getHero(id));
                return Response
                        .status(200)
                        .entity(response)
                        .build();
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Response
                    .status(500)
                    .build();

        }

}
