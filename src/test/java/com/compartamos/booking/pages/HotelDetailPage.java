package com.compartamos.booking.pages;

import org.openqa.selenium.By;

/**
 * HotelDetailPage – Page Object para el detalle del hotel seleccionado.
 */
public class HotelDetailPage extends BasePage {

    private final By hotelName =
            By.xpath("//*[@resource-id='com.booking:id/hotel_name_text' or @resource-id='com.booking:id/property_name']");

    private final By hotelRating =
            By.xpath("//*[@resource-id='com.booking:id/review_score']");

    private final By selectRoomsButton =
            By.xpath("//*[@resource-id='com.booking:id/select_rooms' or @text='Select rooms' or @text='Seleccionar habitaciones']");

    private final By totalPrice =
            By.xpath("//*[@resource-id='com.booking:id/price_displayable']");

    private final By checkInDate =
            By.xpath("//*[@resource-id='com.booking:id/check_in_date']");

    private final By checkOutDate =
            By.xpath("//*[@resource-id='com.booking:id/check_out_date']");

    // ─── Acciones ──────────────────────────────────────────────────────────────

    public RoomSelectionPage clickSelectRooms() {
        click(selectRoomsButton);
        return new RoomSelectionPage();
    }

    // ─── Aserciones ────────────────────────────────────────────────────────────

    public String getHotelName() {
        return getText(hotelName);
    }

    public String getHotelRating() {
        return getText(hotelRating);
    }

    public String getTotalPrice() {
        return getText(totalPrice);
    }

    public String getCheckInDate() {
        return getText(checkInDate);
    }

    public String getCheckOutDate() {
        return getText(checkOutDate);
    }

    public boolean isSelectRoomsButtonDisplayed() {
        return isElementPresent(selectRoomsButton);
    }
}