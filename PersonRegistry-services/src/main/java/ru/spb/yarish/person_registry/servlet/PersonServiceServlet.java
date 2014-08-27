package ru.spb.yarish.person_registry.servlet;

import com.google.gson.Gson;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import ru.spb.yarish.person_registry.dto.Person;
import ru.spb.yarish.person_registry.service.PersonService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


@SlingServlet(paths = "/services/person", methods = "POST", metatype = true)
public class PersonServiceServlet extends SlingAllMethodsServlet {
    Gson gson = new Gson();
    @Reference
    private PersonService service;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        Person person = gson.fromJson(request.getReader(), Person.class);

        boolean result = service.createPerson(person, request.getResourceResolver());
        writeBooleanResponse(response, result);
    }

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        List<Person> persons = service.getPersons(request.getResourceResolver());
        String json = gson.toJson(persons);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    @Override
    protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("itemId");
        boolean result = service.deletePerson(id, request.getResourceResolver());
        writeBooleanResponse(response, result);
    }

    private void writeBooleanResponse(SlingHttpServletResponse response, boolean result) throws IOException {
        PrintWriter out = response.getWriter();
        //TODO: move rendering to client components
        out.println(result ? "done!" : "error");
        out.flush();
    }
}
