package com.example.cocktails.test.integration;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.*;
import java.util.*;

public class CocktailsFullIT {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        String os = System.getProperty("os.name").toLowerCase();
        String geckoDriverPath;

        FirefoxOptions options = new FirefoxOptions();
        if (os.contains("win")) { // Windows
            geckoDriverPath = "drivers/geckodriver.exe";
            options.setBinary("C:/Program Files/Mozilla Firefox/firefox.exe");
        } else if (os.contains("mac")) { // MacOS
            geckoDriverPath = "drivers/geckodriver";
        } else {
            throw new RuntimeException("Unsupported operating system: " + os);
        }

        System.setProperty("webdriver.gecko.driver", geckoDriverPath);

        // options.addArguments("-headless");

        // Erstelle eine Instanz des FirefoxDrivers
        driver = new FirefoxDriver(options);
    }

    @AfterClass
    public static void tearDown() {
        // Schließe den WebDriver
        driver.quit();
    }

    @Test
    public void cocktails() {
        // Navigiere zur Webseite
        driver.get("http://localhost:8080/");

        // Warte, bis das Element "Cocktails" geladen ist (maximal 10 Sekunden)
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Cocktails")));

        // Klicke auf den Link "Cocktails"
        WebElement cocktailsLink = driver.findElement(By.linkText("Cocktails"));
        cocktailsLink.click();

        // Warte, bis die Liste der Cocktails geladen ist (maximal 10 Sekunden)
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.ng-scope")));

        // Überprüfe, ob die Liste der Cocktails nicht leer ist
        WebElement cocktailsList = driver.findElement(By.cssSelector("ul.ng-scope"));
        List<WebElement> elements = cocktailsList.findElements(By.tagName("li"));

        // Screenhot des Browsers erzeugen. Entweder als Datei oder als Base64-String für eine Datenbank
        // byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        // saveScreenshot(bytes);

        // String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

        Assert.assertEquals(69, elements.size());
    }

	private static void saveScreenshot(byte[] bytes) {
    	try {
        	Files.write(Paths.get("screenshot.png"), bytes);
    	} catch (IOException e) {
        	throw new RuntimeException(e);
    	}
	}

}
