package ru.spb.yarish.person_registry.taglib;

import com.cqblueprints.taglib.CqSimpleTagSupport;
import com.squeakysand.jsp.tagext.annotations.JspTag;
import com.squeakysand.jsp.tagext.annotations.JspTagAttribute;
import org.apache.sling.api.resource.ResourceResolver;
import ru.spb.yarish.person_registry.dto.Person;
import ru.spb.yarish.person_registry.service.PersonService;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.List;

@JspTag
public class GetPersonsTag extends CqSimpleTagSupport {
    private ResourceResolver resolver;

    @Override
    public void doTag() throws JspException, IOException {
        PersonService service = getService(PersonService.class);

        List<Person> persons = service.getPersons(resolver);
        setRequestAttribute("persons", persons);
    }

    public ResourceResolver getResolver() {
        return resolver;
    }

    @JspTagAttribute(required = true, rtexprvalue = true)
    public void setResolver(ResourceResolver resolver) {
        this.resolver = resolver;
    }
}
