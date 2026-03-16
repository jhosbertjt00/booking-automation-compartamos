package com.compartamos.booking.pages;

import org.openqa.selenium.By;

/**
 * BookingOverviewPage – Pantalla de resumen de la reserva antes del pago.
 */
public class BookingOverviewPage extends BasePage {

    private final By hotelName =
            By.xpath("//*[@resource-id='com.booking:id/hotel_name' or @resource-id='com.booking:id/property_name']");

    private final By checkInText =
            By.xpath("//*[@resource-id='com.booking:id/check_in_date_text']");

    private final By checkOutText =
            By.xpath("//*[@resource-id='com.booking:id/check_out_date_text']");

    private final By selectionSummary =
            By.xpath("//*[@resource-id='com.booking:id/selection_summary']");

    private final By totalAmount =
            By.xpath("//*[@resource-id='com.booking:id/total_amount' or contains(@text,'US$')]");

    private final By finalStepButton =
            By.xpath("//*[@resource-id='com.booking:id/final_step_button' or @text='Final step' or @text='Último paso']");

    private final By viewPriceBreakdown =
            By.xpath("//*[@text='View price breakdown' or @text='Ver desglose de precio']");

    // ─── Acciones ──────────────────────────────────────────────────────────────

    public PaymentPage clickFinalStep() {
        click(finalStepButton);
        return new PaymentPage();
    }

    // ─── Aserciones ────────────────────────────────────────────────────────────

    public String getHotelName() {
        return getText(hotelName);
    }

    public String getCheckInText() {
        return getText(checkInText);
    }

    public String getCheckOutText() {
        return getText(checkOutText);
    }

    public String getSelectionSummary() {
        return getText(selectionSummary);
    }

    public String getTotalAmount() {
        return getText(totalAmount);
    }

    public boolean isFinalStepButtonDisplayed() {
        return isElementPresent(finalStepButton);
    }

    public boolean isTotalAmountDisplayed() {
        return isElementPresent(totalAmount);
    }
}