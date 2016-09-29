/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import converter.IJSONConverter;
import converter.JSONConverter;
import entity.Person;
import exception.PersonNotFoundException;
import exception.ValidationErrorException;
import facade.Facade;
import facade.IFacade;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author xboxm
 */
@Path("person")
public class PersonResource {

    private final IFacade facade = new Facade();
    private final IJSONConverter jsonC = new JSONConverter();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {
        facade.addEntityManagerFactory(Persistence.createEntityManagerFactory("persistenceunit"));
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
        return "Reached api/person";
    }

    @GET
    @Path("{id : \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonById(@PathParam("id") int id) throws PersonNotFoundException {
        try {
            Person person = facade.getPerson(id);
            return jsonC.PersonToJson(person);
        } catch (NoResultException ex) {
            throw new PersonNotFoundException("No person with provided id found");
        }
    }

    @GET
    @Path("{all}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPeople() {
        List<Person> people = facade.getPeople();
        return jsonC.ListToJson(people);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addPerson(String jsonPerson) throws ValidationErrorException {
        Person person = jsonC.JsonToPerson(jsonPerson);
        
        if (person.getFname().isEmpty() || person.getLname().isEmpty()) {
            throw new ValidationErrorException("Missing first name or last name");
        }
        
        Person p = facade.addPerson(person);
        return jsonC.PersonToJson(p);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String editPerson(String jsonPerson) throws PersonNotFoundException, ValidationErrorException {

        try {
            Person person = jsonC.JsonToPerson(jsonPerson);
            if (person.getFname().isEmpty() || person.getLname().isEmpty()) {
                throw new ValidationErrorException("First name or last name is missing");
            }
            Person p = facade.editPerson(jsonC.JsonToPerson(jsonPerson));
            return jsonC.PersonToJson(p);
        } catch (NoResultException ex) {
            throw new PersonNotFoundException("No person with provided id found");
        }

    }

    @DELETE
    @Path("{id}")
    //@Produces({ MediaType.TEXT_PLAIN})
    public void deletePerson(@PathParam("id") int id) throws PersonNotFoundException {
        try {
        Person p = facade.getPerson(id);
        if (p == null) {
            throw new PersonNotFoundException("Could not delete. No person wiht provided id exists");
        }
        facade.deletePerson(id);
        } catch (NoResultException ex) {
            throw new PersonNotFoundException("No person with provided id found");
        }
    }

}
