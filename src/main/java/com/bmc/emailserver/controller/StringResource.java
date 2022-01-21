package com.bmc.emailserver.controller;

@javax.ws.rs.Path("exampleWithInjection")
@javax.enterprise.context.RequestScoped
public class StringResource {

    @javax.ws.rs.GET
    @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.TEXT_PLAIN)
    public String get() {
        return myData.getData();
    }

    @javax.inject.Inject
    private Data myData;
    
}