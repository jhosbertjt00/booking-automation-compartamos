# ============================================================
# Feature: Booking.com – Flujo completo de reserva de alojamiento
# Desafío II – Compartamos Banco
# Herramienta: Appium + Cucumber (BDD)
# ============================================================

Feature: Reserva de alojamiento en Booking.com – Ciudad de Cusco

  Background:
    Given que la aplicación Booking.com está abierta en el dispositivo

  # ============================================================
  # HAPPY PATH – Flujo completo exitoso
  # ============================================================

  @TC-BOOK-001 @happy_path @smoke @e2e
  Scenario: Búsqueda exitosa de alojamiento en Cusco con parámetros válidos
    When busco el destino "Cusco"
    Then el campo de destino debe mostrar "Cusco"
    And deben aparecer sugerencias de búsqueda relacionadas con "Cusco"

  @TC-BOOK-002 @happy_path @smoke @e2e
  Scenario: Flujo completo de reserva – Happy Path
    When busco el destino "Cusco"
    And selecciono las fechas de estadía del 14 al 28 de febrero de 2023
    And configuro 1 habitación, 2 adultos y 1 niño de 5 años
    And presiono el botón Buscar
    Then la página de resultados debe mostrarse correctamente
    And los resultados deben corresponder a "Cusco"
    And debe mostrarse el número total de propiedades encontradas
    When selecciono el segundo resultado de la lista
    Then debo ver el detalle del hotel seleccionado
    And el nombre del hotel debe estar visible
    And las fechas check-in "Tue, Feb 14" y check-out "Tue, Feb 28" deben estar correctas
    When hago clic en seleccionar habitaciones
    Then la pantalla de selección de habitación debe mostrarse
    And el botón "Reservar esta opción" debe estar disponible
    When reservo la primera opción de habitación
    Then debo ver el formulario de datos del huésped
    And los campos nombre, apellido, email y teléfono deben estar visibles
    When lleno los datos con nombre "Jose", apellido "Hurtado", email "josehurtado@mail.com", teléfono "930731660"
    And selecciono propósito del viaje "Leisure"
    And hago clic en Siguiente paso
    Then debo ver el resumen de la reserva (Booking Overview)
    And el nombre del hotel debe coincidir con el seleccionado
    And las fechas de check-in y check-out deben ser correctas
    And el monto total debe estar visible
    When hago clic en Último paso (Final step)
    Then debo ver la pantalla de pago con tarjeta de crédito
    And los iconos de Visa y Mastercard deben estar visibles
    And el campo número de tarjeta debe estar disponible
    And el campo titular debe estar disponible
    And el campo fecha de vencimiento debe estar disponible
    And debe aparecer el mensaje de garantía de reserva
    When ingreso número de tarjeta "4111111111111111", titular "Jose Hurtado", vencimiento "12/26"
    Then el botón Book Now debe estar habilitado
    And el monto final debe mostrarse correctamente

  # ============================================================
  # HAPPY PATH – Casos adicionales conformes
  # ============================================================

  @TC-BOOK-003 @happy_path
  Scenario: Verificar configuración de ocupación en la pantalla de búsqueda
    When configuro 1 habitación, 2 adultos y 1 niño de 5 años
    Then el resumen de ocupación debe mostrar "1 room · 2 adults · 1 child"

  @TC-BOOK-004 @happy_path
  Scenario: Verificar que los resultados de búsqueda muestran propiedades en Cusco
    When busco el destino "Cusco"
    And selecciono las fechas de estadía del 14 al 28 de febrero de 2023
    And configuro 1 habitación, 2 adultos y 1 niño de 5 años
    And presiono el botón Buscar
    Then la página de resultados debe mostrarse correctamente
    And los botones de Ordenar y Filtrar deben estar visibles

  @TC-BOOK-005 @happy_path
  Scenario: Verificar datos del formulario de huésped con campos válidos
    Given que llegué al formulario de datos del huésped
    When lleno los datos con nombre "Jose", apellido "Hurtado", email "josehurtado@mail.com", teléfono "930731660"
    Then el botón Siguiente paso debe estar habilitado

  @TC-BOOK-006 @happy_path
  Scenario: Verificar resumen de reserva antes del pago
    Given que llegué al resumen de la reserva
    Then el nombre del hotel debe coincidir con el seleccionado
    And las fechas de check-in "Tue Feb 14 2023" deben mostrarse
    And las fechas de check-out "Tue Feb 28 2023" deben mostrarse
    And el resumen debe indicar "14 nights, 1 room for 2 adults, 1 child"
    And el monto total "US$4,423" debe estar visible

  @TC-BOOK-007 @happy_path
  Scenario: Pantalla de pago muestra opciones de tarjeta correctamente
    Given que llegué a la pantalla de pago
    Then los iconos de Visa y Mastercard deben estar visibles
    And el campo número de tarjeta debe estar disponible
    And el campo titular debe estar disponible
    And debe aparecer el mensaje de garantía de reserva

  # ============================================================
  # UNHAPPY PATH – Casos no conformes (validaciones negativas)
  # ============================================================

  @TC-BOOK-008 @unhappy_path
  Scenario: Búsqueda sin ingresar destino muestra error o no permite continuar
    When presiono el botón Buscar sin ingresar destino
    Then el sistema no debe navegar a resultados
    And debe mostrarse un mensaje de campo obligatorio o el campo debe resaltarse

  @TC-BOOK-009 @unhappy_path
  Scenario: Ingreso de número de tarjeta inválido muestra mensaje de error
    Given que llegué a la pantalla de pago
    When ingreso número de tarjeta inválido "4555788765443333"
    And ingreso titular "Jose Hurtado"
    And ingreso fecha de vencimiento "02/25"
    Then debe mostrarse el mensaje "Card number is invalid"

  @TC-BOOK-010 @unhappy_path
  Scenario: Fecha de vencimiento de tarjeta en formato incorrecto muestra error
    Given que llegué a la pantalla de pago
    When ingreso número de tarjeta válido "4111111111111111"
    And ingreso titular "Jose Hurtado"
    And ingreso fecha de vencimiento en formato incorrecto "99/99"
    Then debe mostrarse un error de fecha de vencimiento

  @TC-BOOK-011 @unhappy_path
  Scenario: Formulario de huésped con email inválido no permite avanzar
    Given que llegué al formulario de datos del huésped
    When lleno los datos con nombre "Jose", apellido "Hurtado", email "emailinvalido", teléfono "930731660"
    And hago clic en Siguiente paso
    Then debe mostrarse el error de formato de email

  @TC-BOOK-012 @unhappy_path
  Scenario: Búsqueda con destino inexistente muestra mensaje de no resultados
    When busco el destino "XYZ_CIUDAD_INEXISTENTE_123"
    And selecciono las fechas de estadía del 14 al 28 de febrero de 2023
    And configuro 1 habitación, 2 adultos y 1 niño de 5 años
    And presiono el botón Buscar
    Then debe mostrarse un mensaje indicando que no hay propiedades disponibles

  @TC-BOOK-013 @unhappy_path
  Scenario: Intento de reserva con tarjeta sin número muestra error
    Given que llegué a la pantalla de pago
    When dejo el número de tarjeta vacío
    And hago clic en Book Now
    Then el botón Book Now no debe procesar la reserva
    And debe mostrarse el indicador de campo requerido en número de tarjeta
