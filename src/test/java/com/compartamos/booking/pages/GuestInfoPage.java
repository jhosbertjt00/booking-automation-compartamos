package com.compartamos.booking.pages;

import org.openqa.selenium.By;

/**
 * GuestInfoPage – Formulario de datos del huésped (Fill in your info).
 */
public class GuestInfoPage extends BasePage {

    private final By firstNameField =
            By.xpath("//*[@resource-id='com.booking:id/first_name_edittext' or contains(@hint,'First name') or contains(@hint,'Nombre')]");

    private final By lastNameField =
            By.xpath("//*[@resource-id='com.booking:id/last_name_edittext' or contains(@hint,'Last name') or contains(@hint,'Apellido')]");

    private final By emailField =
            By.xpath("//*[@resource-id='com.booking:id/email_edittext' or contains(@hint,'Email')]");

    private final By countryField =
            By.xpath("//*[@resource-id='com.booking:id/country_picker' or contains(@hint,'Country')]");

    private final By phoneField =
            By.xpath("//*[@resource-id='com.booking:id/phone_edittext' or contains(@hint,'Phone') or contains(@hint,'Teléfono')]");

    private final By nextStepButton =
            By.xpath("//*[@resource-id='com.booking:id/action_button' or @text='Next step' or @text='Siguiente paso']");

    private final By totalPrice =
            By.xpath("//*[@resource-id='com.booking:id/price_summary']");

    // ─── Acciones ──────────────────────────────────────────────────────────────

    public GuestInfoPage fillFirstName(String name) {
        sendKeys(firstNameField, name);
        return this;
    }

    public GuestInfoPage fillLastName(String lastName) {
        sendKeys(lastNameField, lastName);
        return this;
    }

    public GuestInfoPage fillEmail(String email) {
        sendKeys(emailField, email);
        return this;
    }

    public GuestInfoPage fillPhone(String phone) {
        sendKeys(phoneField, phone);
        return this;
    }

    public GuestInfoPage selectPurpose(String purpose) {
        // purpose: "Leisure" o "Business"
        By purposeOption = By.xpath("//*[@text='" + purpose + "']");
        click(purposeOption);
        return this;
    }

    public BookingOverviewPage clickNextStep() {
        click(nextStepButton);
        return new BookingOverviewPage();
    }

    // ─── Aserciones ────────────────────────────────────────────────────────────

    public boolean isFirstNameFieldDisplayed() {
        return isElementPresent(firstNameField);
    }

    public boolean isEmailFieldDisplayed() {
        return isElementPresent(emailField);
    }

    public boolean isNextStepEnabled() {
        return waitForVisible(nextStepButton).isEnabled();
    }

    public String getTotalPrice() {
        return getText(totalPrice);
    }

    /**
     * Verifica que el campo email muestre error de validación cuando el formato es incorrecto.
     */
    public boolean hasEmailValidationError() {
        By emailError = By.xpath("//*[@resource-id='com.booking:id/email_error' or contains(@text,'valid email') or contains(@text,'email válido')]");
        return isElementPresent(emailError);
    }
}