package com.compartamos.booking.pages;

import org.openqa.selenium.By;

/**
 * HomePage – Page Object para la pantalla principal de Booking.com.
 * Cubre la búsqueda de destino, fechas y configuración de huéspedes.
 */
public class HomePage extends BasePage {

    // ─── Locators ──────────────────────────────────────────────────────────────
    // El campo de búsqueda principal (destino)
    private final By searchDestinationField =
            By.xpath("//android.widget.EditText[@resource-id='com.booking:id/search_edittext' or contains(@hint,'destino') or contains(@hint,'destination')]");

    // Resultado de la sugerencia de búsqueda
    private final By firstSuggestionResult =
            By.xpath("//android.widget.TextView[@resource-id='com.booking:id/ss_suggestion_title']");

    // Campo de fechas
    private final By datePickerField =
            By.xpath("//*[@resource-id='com.booking:id/calendar_selection_container' or contains(@content-desc,'Feb')]");

    // Botón "Buscar"
    private final By searchButton =
            By.xpath("//*[@resource-id='com.booking:id/search_button' or @text='Search' or @text='Buscar']");

    // Ocupantes (Rooms, Adults, Children)
    private final By occupancyField =
            By.xpath("//*[@resource-id='com.booking:id/occupancy_picker_text' or contains(@text,'room') or contains(@text,'habitación')]");

    // ─── Acciones ──────────────────────────────────────────────────────────────

    /**
     * Ingresa el destino y selecciona la primera sugerencia.
     * @param destination ciudad destino, ej. "Cusco"
     */
    public HomePage searchDestination(String destination) {
        click(searchDestinationField);
        sendKeys(searchDestinationField, destination);
        // Espera a que aparezca la sugerencia y hace clic
        waitForVisible(firstSuggestionResult).click();
        return this;
    }

    /**
     * Selecciona las fechas en el calendario.
     * @param checkIn  día de entrada (1-28)
     * @param checkOut día de salida (1-28)
     * @param month    mes como texto, ej. "February 2023"
     */
    public HomePage selectDates(int checkIn, int checkOut, String month) {
        click(datePickerField);

        // Localizar día de check-in en el calendario
        By checkInDay = By.xpath(
            "//android.view.ViewGroup[@content-desc='" + month + "']"
            + "//android.widget.TextView[@text='" + checkIn + "']"
        );
        By checkOutDay = By.xpath(
            "//android.view.ViewGroup[@content-desc='" + month + "']"
            + "//android.widget.TextView[@text='" + checkOut + "']"
        );

        waitForClickable(checkInDay).click();
        waitForClickable(checkOutDay).click();

        // Confirmar selección
        By confirmButton = By.xpath("//*[@text='Select' or @text='Seleccionar' or @text='Done']");
        click(confirmButton);
        return this;
    }

    /**
     * Configura la ocupación: habitaciones, adultos y niños.
     */
    public HomePage setOccupancy(int rooms, int adults, int children, int childAge) {
        click(occupancyField);

        // ── Ajustar rooms ──
        adjustCounter("Rooms", rooms);

        // ── Ajustar adults ──
        adjustCounter("Adults", adults);

        // ── Ajustar children ──
        adjustCounter("Children", children);

        // ── Ingresar edad del niño ──
        if (children > 0) {
            By childAgeDropdown = By.xpath(
                "(//*[@resource-id='com.booking:id/child_age_select'])[1]"
            );
            waitForClickable(childAgeDropdown).click();
            By ageOption = By.xpath("//*[@text='" + childAge + "']");
            click(ageOption);
        }

        // Confirmar
        By doneButton = By.xpath("//*[@text='Done' or @text='Listo' or @text='OK']");
        click(doneButton);
        return this;
    }

    /**
     * Incrementa o decrementa un contador de ocupación hasta el valor deseado.
     */
    private void adjustCounter(String label, int targetValue) {
        By counter = By.xpath(
            "//android.widget.TextView[@text='" + label + "']"
            + "/following-sibling::android.widget.TextView[@resource-id='com.booking:id/counter_value']"
        );
        By increaseBtn = By.xpath(
            "//android.widget.TextView[@text='" + label + "']"
            + "/following-sibling::*[@resource-id='com.booking:id/increment_button']"
        );
        By decreaseBtn = By.xpath(
            "//android.widget.TextView[@text='" + label + "']"
            + "/following-sibling::*[@resource-id='com.booking:id/decrement_button']"
        );

        int current = Integer.parseInt(getText(counter));
        while (current < targetValue) {
            click(increaseBtn);
            current++;
        }
        while (current > targetValue) {
            click(decreaseBtn);
            current--;
        }
    }

    /**
     * Hace clic en el botón de búsqueda.
     */
    public SearchResultsPage clickSearch() {
        click(searchButton);
        return new SearchResultsPage();
    }

    // ─── Aserciones ────────────────────────────────────────────────────────────

    public boolean isSearchFieldDisplayed() {
        return isElementPresent(searchDestinationField);
    }

    public String getOccupancyText() {
        return getText(occupancyField);
    }
}