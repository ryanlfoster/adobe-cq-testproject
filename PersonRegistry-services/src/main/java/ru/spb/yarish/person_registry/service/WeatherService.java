package ru.spb.yarish.person_registry.service;

import com.cdyne.ws.weatherws.Weather;
import com.cdyne.ws.weatherws.WeatherReturn;
import com.cdyne.ws.weatherws.WeatherSoap;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

@Component(immediate = true)
@Service(WeatherService.class)
public class WeatherService {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    private final String ENDPOINT_PROPERTY = "service.weather.endpoint";
    private String endpoint;

    public WeatherReturn getCityWeatherByZIP(String zip) {
        WeatherReturn result = null;
        log.debug("Endpoint is " + endpoint);
        WeatherSoap client = null;
        try {
            client = new Weather(new URL(endpoint)).getWeatherSoap();
            result = client.getCityWeatherByZIP(zip);
        } catch (MalformedURLException e) {
            log.error("Check properties. Is weather endpoint correct?", e);
        }

        return result;
    }

    @Activate
    protected final void activate(ComponentContext componentContext) {
        log.debug("WeatherService activating ...");
        endpoint = (String) componentContext.getProperties().get(ENDPOINT_PROPERTY);
    }
}
