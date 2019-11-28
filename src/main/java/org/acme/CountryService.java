package org.acme;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Set;

@Path("/v2")
@RegisterRestClient
public interface CountryService {

    @GET
    @Path("/name/{name}")
    @Produces("application/json")
    Set<Country> getByName(@PathParam String name);
}