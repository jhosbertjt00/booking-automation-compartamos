package com.compartamos.booking.pages;

import org.openqa.selenium.By;

/**
 * RoomSelectionPage – Selección de habitación y reserva de la primera opción.
 */
public class RoomSelectionPage extends BasePage {

    private final By firstReserveButton =
            By.xpath("(//*[@resource-id='com.booking:id/reserve_button' or @text='Reserve this option' or @text='Reservar esta opción'])[1]");

    private final By roomName =
            By.xpath("(//*[@resource-id='com.booking:id/room_name'])[1]");

    private final By roomPrice =
            By.xpath("(//*[@resource-id='com.booking:id/room_price'])[1]");

    // ─── Acciones ──────────────────────────────────────────────────────────────

    public GuestInfoPage reserveFirstOption() {
        click(firstReserveButton);
        return new GuestInfoPage();
    }

    // ─── Aserciones ────────────────────────────────────────────────────────────

    public String getRoomName() {
        return getText(roomName);
    }

    public String getRoomPrice() {
        return getText(roomPrice);
    }

    public boolean isReserveButtonDisplayed() {
        return isElementPresent(firstReserveButton);
    }
}