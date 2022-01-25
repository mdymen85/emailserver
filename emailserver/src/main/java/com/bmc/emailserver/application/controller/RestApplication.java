package com.bmc.emailserver.application.controller;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.bmc.emailserver.application.authentication.BearerTokenFilter;
import com.bmc.emailserver.application.exception.GenericExceptionHandler;
import com.bmc.emailserver.domain.mail.exception.IncorrectHostExceptionHandler;
import com.bmc.emailserver.domain.mail.exception.IncorrectParameterExceptionHandler;

@ApplicationPath("/api")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> sets = new HashSet<Class<?>>();
        sets.add(SendController.class);
        sets.add(BearerTokenFilter.class);
        sets.add(IncorrectParameterExceptionHandler.class);
        sets.add(GenericExceptionHandler.class);           
        sets.add(IncorrectHostExceptionHandler.class);                           
        return sets;
    }
    
}
