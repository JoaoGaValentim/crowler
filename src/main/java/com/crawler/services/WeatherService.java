package com.crawler.services;

import java.io.IOException;

import org.jsoup.Jsoup;

/**
 * <h1>WeatherService</h1>
 * <p>
 * This class service search weather temperature in an city
 * <br />
 * and return <strong>MAX</strong> and <strong>MIN</strong> in
 * Celcius unit of temperature.
 * 
 * @see #WeatherService()
 * @see #WeatherService(String)
 * 
 * @author João Gabriel
 * @version 0.1
 */
public class WeatherService extends ConentService {
    private final String URL = "https://www.wunderground.com/weather/br/";
    private final String title;
    private final String minTemperatureText;
    private final String maxTemperatureText;

    public WeatherService() throws IOException {
        setDocument(Jsoup.connect(String.format("%s/%s", URL, "sao-paulo")).get());
        title = getDocument().getElementsByTag("h1").select("span[_ngcontent-app-root-c4129345549]").first().text();
        minTemperatureText = sanitizeTemperature(getDocument().select(
                "div[_ngcontent-app-root-c2938902538]>span.lo")
                .first()
                .text());
        maxTemperatureText = sanitizeTemperature(getDocument().select(
                "div[_ngcontent-app-root-c2938902538]>div.current-temp")
                .first()
                .text());
    }

    public WeatherService(String locate) throws IOException {
        setDocument(Jsoup.connect(String.format("%s/%s", URL, locate.trim().toLowerCase())).get());
        title = getDocument().getElementsByTag("h1").select("span[_ngcontent-app-root-c4129345549]").first().text();
        minTemperatureText = sanitizeTemperature(getDocument().select(
                "div[_ngcontent-app-root-c2938902538]>span.lo")
                .first()
                .text());
        maxTemperatureText = sanitizeTemperature(getDocument().select(
                "div[_ngcontent-app-root-c2938902538]>div.current-temp")
                .first()
                .text());
    }

    private String sanitizeTemperature(String value) {
        if (value.contains("°F")) {
            value = value.replace("°F", "");
        }

        if (value.contains("°")) {
            value = value.replace("°", "");
        }

        return String.format("%.2f", fahrenheitToCelcius(value)).replace(".", ",") + " °C";
    }

    private boolean isNumber(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private double fahrenheitToCelcius(String value) {
        final double celcius = isNumber(value) ? (Double.parseDouble(value) - 32) * 0.5555555556 : 0.0;
        return isNumber(value) ? celcius : 0.0;
    }

    public String getTitle() {
        return title;
    }

    public String getMinTemperatureText() {
        return minTemperatureText;
    }

    public String getMaxTemperatureText() {
        return maxTemperatureText;
    }

    public void run() {
        System.out.println("========= " + title + " =========");
        System.out.println("MIN_TEMP = " + minTemperatureText);
        System.out.println("MAX_TEMP = " + maxTemperatureText);
        System.out.println("=================================");
    }
}
