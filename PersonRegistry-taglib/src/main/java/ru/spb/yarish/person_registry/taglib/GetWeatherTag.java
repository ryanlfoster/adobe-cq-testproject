package ru.spb.yarish.person_registry.taglib;

import com.cdyne.ws.weatherws.WeatherReturn;
import com.cqblueprints.taglib.CqSimpleTagSupport;
import com.squeakysand.jsp.tagext.annotations.JspTag;
import com.squeakysand.jsp.tagext.annotations.JspTagAttribute;
import ru.spb.yarish.person_registry.service.WeatherService;
import javax.servlet.jsp.JspException;
import java.io.IOException;

@JspTag
public class GetWeatherTag extends CqSimpleTagSupport {
    private String zipCode;

    @Override
    public void doTag() throws JspException, IOException {
        WeatherService service = getService(WeatherService.class);
        WeatherReturn result = service.getCityWeatherByZIP(zipCode);

        setRequestAttribute("weather", result);
    }

    @JspTagAttribute(required = true, rtexprvalue = true)
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }
}