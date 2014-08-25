package ru.spb.yarish.person_registry.taglib;

import com.cqblueprints.taglib.CqSimpleTagSupport;
import com.day.cq.widget.HtmlLibraryManager;

import javax.servlet.jsp.JspException;
import java.io.IOException;

public class SmartClientLibraryIncluder extends CqSimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        HtmlLibraryManager service = getService(HtmlLibraryManager.class);

    }
}
