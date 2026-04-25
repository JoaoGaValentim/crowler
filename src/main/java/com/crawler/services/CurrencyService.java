package com.crawler.services;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import com.crawler.exceptions.DocumentNotFoundException;
import com.crawler.exceptions.ElementNotFoundException;

public class CurrencyService extends ConentService {
    private Element currencyInput;
    private final String URL = "https://www.melhorcambio.com/dolar-hoje";

    public CurrencyService() throws IOException {
        setDocument(Jsoup.connect(URL).get());
        currencyInput = getDocument().select("#comercial").getFirst();
        setId(currencyInput.attr("id"));
        setCssClass(currencyInput.attr("class"));
        setElementName(currencyInput.attr("name"));
        setText(currencyInput.attr("value"));
    }

    private Element getInputData() {
        if (getDocument() == null) {
            throw new DocumentNotFoundException();
        }

        return getDocument().select("#comercial").getFirst();
    }

    public double getDollarToBRL() {
        if (currencyInput == null) {
            throw new ElementNotFoundException();
        }

        String value = getInputData().attr("value").replace(",", ".");
        return Double.valueOf(value);
    }

    public void run() {
        System.out.println("======== DÓLAR HOJE ========");
        System.out.println("USD em BRL hoje é R$ " + getDollarToBRL());
        System.out.println("=============================");
    }
}
