package com.bmc.emailserver.controller;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.bmc.emailserver.authentication.BearerTokenFilter;
import com.bmc.emailserver.authentication.controller.LoginController;
import com.bmc.emailserver.mail.service.SendMailService;

@ApplicationPath("/api")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> sets = new HashSet<Class<?>>();
        sets.add(SendController.class);
     //   sets.add(SendMailService.class);
     //   sets.add(BearerTokenFilter.class);
        return sets;
    }
	
}
