package ru.spb.yarish.person_registry.servlet;

import com.google.gson.Gson;
import org.apache.felix.scr.annotations.*;
import ru.spb.yarish.person_registry.dto.Person;
import ru.spb.yarish.person_registry.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.ws.rs.*;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Component
@Service(Object.class)
@Property(name = "javax.ws.rs", boolValue = true)
//@Path("/resource/person")
public class PersonResource {
    Gson gson = new Gson();
    @Reference
    private PersonService service;

//    @GET
//    @Path("/list")
//    protected Response getPersons(@Context HttpServletRequest request, @Context HttpServletResponse response) {
//        List<Person> persons = service.getPersons();
//        return Response.ok(gson.toJson(persons)).build();
//    }
//
//    @POST
//    @Path("/add")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.TEXT_HTML)
//    public Response addPerson(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
//        Person person = gson.fromJson(request.getReader(), Person.class);
//        boolean result = service.createPerson(person);
//        return Response.ok(result ? "done!" : "error").build();
//    }
}
