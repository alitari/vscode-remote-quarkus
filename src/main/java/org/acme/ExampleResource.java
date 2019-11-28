package org.acme;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/hello")
public class ExampleResource {

    @Inject
    @RestClient
    CountryService countryService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello\n";
    }

    @GET
    @Path("/country/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Country> name(@PathParam String name) {
        return countryService.getByName(name);
    }
}