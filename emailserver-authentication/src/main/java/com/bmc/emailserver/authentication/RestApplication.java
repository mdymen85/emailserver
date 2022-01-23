package com.bmc.emailserver.authentication;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.bmc.emailserver.authentication.controller.LoginController;


@ApplicationPath("/api")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> sets = new HashSet<Class<?>>();
        sets.add(LoginController.class);
        return sets;
    }
	
}
