package com.compartamos.booking.runner;

import org.junit.platform.suite.api.*;

/**
 * BookingTestRunner – Punto de entrada de Cucumber para ejecutar
 * todos los escenarios del flujo de reserva de Booking.com.
 *
 * Ejecutar con: mvn test
 * Filtrar tags:  mvn test -Dcucumber.filter.tags="@smoke"
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
    key   = "cucumber.plugin",
    value = "pretty, html:target/cucumber-reports/booking-report.html, "
          + "json:target/cucumber-reports/booking-report.json, "
          + "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
)
@ConfigurationParameter(
    key   = "cucumber.glue",
    value = "com.compartamos.booking.steps"
)
@ConfigurationParameter(
    key   = "cucumber.publish.quiet",
    value = "true"
)
public class BookingTestRunner {
    // Clase vacía: JUnit Platform Suite usa las anotaciones para configuración
}