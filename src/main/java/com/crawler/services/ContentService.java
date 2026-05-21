package com.crawler.services;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.crawler.exceptions.DocumentNotFoundException;
import com.crawler.exceptions.ElementNotFoundException;

abstract public class ContentService implements Service {
    private Document document;
    private String id;
    private String cssClass;
    private String elementName;
    private String text;

    public ConentService() {
        this("", "", "", "");
    }

    public ConentService(String id, String cssClass, String elementName, String text) {
        this.id = id;
        this.cssClass = cssClass;
        this.elementName = elementName;
        this.text = text;
    }

    protected Document getDocument() {
        if (document == null) {
            throw new DocumentNotFoundException();
        }

        return document;
    }

    protected String getId() {
        return id;
    }

    protected String getCssClass() {
        return cssClass;
    }

    protected String getElementName() {
        return elementName;
    }

    protected String getText() {
        return text;
    }

    protected void setDocument(Document document) {
        this.document = document;
    }

    protected void setId(String id) {
        this.id = id;
    }

    protected void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    protected void setElementName(String elementName) {
        this.elementName = elementName;
    }

    protected void setText(String text) {
        this.text = text;
    }

    protected Element fetchElementByHTMLPath(String htmlPath) {
        Element element = getDocument().selectFirst(htmlPath);

        if (element == null) {
            throw new ElementNotFoundException();
        }

        return element;
    }
}
