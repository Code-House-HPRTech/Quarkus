package com.hprtech.health;


import com.hprtech.restclient.JsonPlaceholderRestClient;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Liveness
public class LivenessHealthCheck implements HealthCheck {

    @RestClient
    JsonPlaceholderRestClient restClient;

    @Override
    public HealthCheckResponse call() {
        restClient.getAllPost();
        return HealthCheckResponse.named(" JsonPlaceHolder APIs Health").up().build();
    }
}
