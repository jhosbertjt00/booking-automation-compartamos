package com.compartamos.booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * SearchResultsPage – Page Object para la lista de resultados de alojamiento.
 */
public class SearchResultsPage extends BasePage {

    // ─── Locators ──────────────────────────────────────────────────────────────
    private final By resultsList =
            By.xpath("//*[@resource-id='com.booking:id/hotel_list_recyclerview']//android.view.ViewGroup[@clickable='true']");

    private final By resultsCount =
            By.xpath("//*[@resource-id='com.booking:id/phrase_overview_search_name' or contains(@text,'properties found')]");

    private final By sortButton =
            By.xpath("//*[@text='Sort' or @content-desc='Sort']");

    private final By filterButton =
            By.xpath("//*[@text='Filter' or @content-desc='Filter']");

    // ─── Acciones ──────────────────────────────────────────────────────────────

    /**
     * Selecciona el resultado por posición (1-indexed).
     * Para el desafío: seleccionar el item 2.
     */
    public HotelDetailPage selectResultByIndex(int index) {
        List<WebElement> results = wait.until(
            d -> {
                List<WebElement> els = d.findElements(resultsList);
                return els.size() >= index ? els : null;
            }
        );
        results.get(index - 1).click();
        return new HotelDetailPage();
    }

    /**
     * Retorna el texto del contador de propiedades encontradas.
     */
    public String getResultsCountText() {
        return getText(resultsCount);
    }

    /**
     * Verifica que los resultados correspondan a la búsqueda.
     */
    public boolean hasResults() {
        return isElementPresent(resultsList);
    }

    public boolean isSortButtonDisplayed() {
        return isElementPresent(sortButton);
    }

    public boolean isFilterButtonDisplayed() {
        return isElementPresent(filterButton);
    }
}