package ru.spb.yarish.person_registry.taglib;

import com.cqblueprints.taglib.CqSimpleTagSupport;
import com.squeakysand.jsp.tagext.annotations.JspTag;
import com.squeakysand.jsp.tagext.annotations.JspTagAttribute;
import ru.spb.yarish.person_registry.dto.Person;
import ru.spb.yarish.person_registry.service.PersonService;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.List;

@JspTag
public class GetPersonsTag extends CqSimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        PersonService service = getService(PersonService.class);

        List<Person> persons = service.getPersons();
        setRequestAttribute("persons", persons);
    }
}
