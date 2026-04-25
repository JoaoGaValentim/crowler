package com.crawler.exceptions;

public class DocumentNotFoundException extends RuntimeException {
    public DocumentNotFoundException() {
        super("DocumentNotFoundException: This page don't found, try replace");
    }
}
