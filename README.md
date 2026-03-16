# Booking.com Mobile Automation - Compartamos Banco

## Desafío II - Automatización de Pruebas Funcionales

Framework de automatización móvil para la app de Booking.com
usando Appium + Java + Cucumber (BDD) + Page Object Model.

## Stack Técnico
- Java 11+
- Appium 2.x + UiAutomator2
- Cucumber 7 (Gherkin BDD)
- JUnit 5
- Allure Reports

## Prerequisitos
- Android Studio + AVD (Pixel 6 API 32)
- Appium Server 2.x corriendo en puerto 4723
- Booking.com instalada en el emulador

## Ejecutar Tests
```bash
# Todos los tests
mvn clean test

# Solo smoke tests
mvn test -Dcucumber.filter.tags="@smoke"

# Ver reporte Allure
npx allure serve target/allure-results
```

## Estructura del Proyecto
```
src/test/java/com/compartamos/booking/
├── pages/       # Page Objects (POM)
├── steps/       # Step Definitions Cucumber
├── runner/      # Test Runner JUnit 5
└── utils/       # DriverManager Appium
