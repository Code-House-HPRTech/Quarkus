package com.hprtech.health;


import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Readiness
public class ReadinessHealthCheck implements HealthCheck {

    @Inject
    DataSource dataSource;

    @Override
    public HealthCheckResponse call() {

        try {
            Connection connection = dataSource.getConnection();
            if (connection.isValid(1)) {
                return HealthCheckResponse
                        .named("Database Readiness Health")
                        .up()
                        .build();
            } else {
                return HealthCheckResponse
                        .named("Database Readiness Health")
                        .down()
                        .build();
            }

        } catch (SQLException e) {
            return HealthCheckResponse
                    .named("Database Readiness Health")
                    .down()
                    .build();
        }
    }
}
