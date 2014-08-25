package ru.spb.yarish.person_registry.taglib;

import com.cqblueprints.taglib.CqSimpleTagSupport;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import com.squeakysand.jsp.tagext.annotations.JspTag;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.Iterator;

@JspTag
public class TopNavTag extends CqSimpleTagSupport {
    private static final String ACTIVE_CLASS = "class='active'";

    @Override
    public void doTag() throws JspException, IOException {
        Page currentPage = getCurrentPage();
        ServletRequest request = getRequest();

        Page navRootPage = currentPage.getAbsoluteParent(2);
        if (navRootPage == null) {
            navRootPage = currentPage;
        }

        Iterator<Page> children = navRootPage.listChildren(new PageFilter(request));
        while (children.hasNext()) {
            String cssStyle = "";
            Page child = children.next();
            if (currentPage.equals(child)) cssStyle = ACTIVE_CLASS;
            getJspWriter().write("<li " + cssStyle + "><a href=\"" + child.getPath() + ".html\">" + child.getTitle() + "</a></li>");
        }
    }
}
