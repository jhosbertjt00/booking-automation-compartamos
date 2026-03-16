package com.compartamos.booking.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * DriverManager – Singleton thread-safe para gestionar el AndroidDriver de Appium.
 * Configura UiAutomator2Options apuntando a la app de Booking.com en Android.
 */
public class DriverManager {

    private static final ThreadLocal<AndroidDriver> driverThread = new ThreadLocal<>();

    private static final String APPIUM_SERVER = "http://127.0.0.1:4723";
    private static final String BOOKING_PACKAGE = "com.booking";
    private static final String BOOKING_ACTIVITY = "com.booking.home.HomeActivity";

    private DriverManager() { }

    /**
     * Inicializa el driver con las capacidades configuradas.
     * Requiere que Appium Server esté corriendo y el dispositivo/emulador conectado.
     */
    public static void initDriver() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setPlatformVersion("12.0")          // Ajustar según emulador
                .setDeviceName("Pixel_6_API_32")     // Ajustar según AVD
                .setAppPackage(BOOKING_PACKAGE)
                .setAppActivity(BOOKING_ACTIVITY)
                .setNoReset(true)                    // Preservar estado de sesión
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(Duration.ofSeconds(60));

        try {
            AndroidDriver driver = new AndroidDriver(new URL(APPIUM_SERVER), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driverThread.set(driver);
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL de Appium Server inválida: " + APPIUM_SERVER, e);
        }
    }

    public static AndroidDriver getDriver() {
        if (driverThread.get() == null) {
            throw new IllegalStateException("Driver no inicializado. Llame a initDriver() primero.");
        }
        return driverThread.get();
    }

    public static void quitDriver() {
        if (driverThread.get() != null) {
            driverThread.get().quit();
            driverThread.remove();
        }
    }
}