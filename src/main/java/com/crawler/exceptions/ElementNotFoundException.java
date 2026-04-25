package com.crawler.exceptions;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException() {
        super("ElementNotFoundException: You selected the unreal element.");
    }
}
