package com.compartamos.booking.pages;

import org.openqa.selenium.By;

/**
 * PaymentPage – Pantalla de pago con tarjeta de crédito (Finish booking).
 */
public class PaymentPage extends BasePage {

    // ─── Locators – Credit Card Form ───────────────────────────────────────────
    private final By cardNumberField =
            By.xpath("//*[@resource-id='com.booking:id/card_number_edittext' or contains(@hint,'Card number') or contains(@hint,'Número de tarjeta')]");

    private final By holderNameField =
            By.xpath("//*[@resource-id='com.booking:id/holder_name_edittext' or contains(@hint,'Holder') or contains(@hint,'Titular')]");

    private final By expirationDateField =
            By.xpath("//*[@resource-id='com.booking:id/expiration_date_edittext' or contains(@hint,'MM/YY') or contains(@hint,'Expiry')]");

    private final By bookNowButton =
            By.xpath("//*[@resource-id='com.booking:id/book_now_button' or @text='Book now' or @text='Reservar ahora']");

    private final By totalAmount =
            By.xpath("//*[@resource-id='com.booking:id/total_price_text' or contains(@text,'US$4,423')]");

    // Error messages
    private final By cardNumberError =
            By.xpath("//*[@resource-id='com.booking:id/card_number_error' or contains(@text,'Card number is invalid') or contains(@text,'número de tarjeta es inválido')]");

    private final By expirationError =
            By.xpath("//*[@resource-id='com.booking:id/expiry_error' or contains(@text,'Expiry') or contains(@text,'vencimiento')]");

    private final By cardGuaranteeText =
            By.xpath("//*[contains(@text,'required to guarantee') or contains(@text,'garantía')]");

    // Payment methods
    private final By visaIcon =
            By.xpath("//*[@resource-id='com.booking:id/visa_icon' or contains(@content-desc,'Visa')]");

    private final By mastercardIcon =
            By.xpath("//*[@resource-id='com.booking:id/mastercard_icon' or contains(@content-desc,'Mastercard')]");

    // ─── Acciones ──────────────────────────────────────────────────────────────

    public PaymentPage enterCardNumber(String cardNumber) {
        sendKeys(cardNumberField, cardNumber);
        return this;
    }

    public PaymentPage enterHolderName(String name) {
        sendKeys(holderNameField, name);
        return this;
    }

    public PaymentPage enterExpirationDate(String expDate) {
        sendKeys(expirationDateField, expDate);
        return this;
    }

    /**
     * Completa todo el formulario de pago.
     */
    public PaymentPage fillPaymentForm(String cardNumber, String holderName, String expDate) {
        enterCardNumber(cardNumber);
        enterHolderName(holderName);
        enterExpirationDate(expDate);
        return this;
    }

    public void clickBookNow() {
        click(bookNowButton);
    }

    // ─── Aserciones ────────────────────────────────────────────────────────────

    public boolean isCardNumberErrorDisplayed() {
        return isElementPresent(cardNumberError);
    }

    public boolean isExpirationErrorDisplayed() {
        return isElementPresent(expirationError);
    }

    public boolean isCardGuaranteeTextDisplayed() {
        return isElementPresent(cardGuaranteeText);
    }

    public boolean isVisaIconDisplayed() {
        return isElementPresent(visaIcon);
    }

    public boolean isMastercardIconDisplayed() {
        return isElementPresent(mastercardIcon);
    }

    public String getTotalAmount() {
        return getText(totalAmount);
    }

    public boolean isBookNowButtonDisplayed() {
        return isElementPresent(bookNowButton);
    }

    public String getCardNumberFieldValue() {
        return waitForVisible(cardNumberField).getAttribute("text");
    }
}