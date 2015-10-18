package io.swagger.sample.controllers;

import io.swagger.inflector.models.RequestContext;
import io.swagger.inflector.models.ResponseContext;
import io.swagger.sample.helpers.PetHelper;
import io.swagger.sample.models.Pet;
import javax.ws.rs.core.Response.Status;


public class SampleController {
    public ResponseContext getPetById(RequestContext request, int id) {
     
    	
        Pet pet=PetHelper.getPetById(id);
        return new ResponseContext()
                .status(Status.OK)
                .entity(pet);
    }
}