package com.example.lab1.events;

import org.springframework.context.ApplicationEvent;

public class AuthorChangedEvent extends ApplicationEvent {
    public AuthorChangedEvent(Object source) {
        super(source);
    }
}