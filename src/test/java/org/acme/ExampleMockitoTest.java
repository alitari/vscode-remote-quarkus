package org.acme;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ExampleMockitoTest {

    @Mock
    CountryService countryService;

    @InjectMocks
    ExampleResource exampleResource;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mockservice() {

        Set<Country> expectedCountries = new HashSet<>();
        Country country = new Country();
        country.name = "France";
        expectedCountries.add(country);

        when(countryService.getByName("France")).thenReturn(expectedCountries);
         Set<Country> actualCountries = exampleResource.name("France");
         assertThat(actualCountries).isSameAs(expectedCountries);
    }

}