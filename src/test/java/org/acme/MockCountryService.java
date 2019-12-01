package org.acme;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.test.Mock;

@Mock
@ApplicationScoped
@RestClient
public class MockCountryService implements CountryService {

    @Override
    public Set<Country> getByName(String name) {
        Set<Country> countries = new HashSet<>();
        Country country = new Country();
        country.name = name;
        countries.add(country);
        return countries;
    }

    
}