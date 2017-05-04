package com.excilys.computerdatabase;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by excilys on 28/04/17.
 */
/*public class SeleniumTest {
    private static WebDriver driver;
    private final String homeDir = "LocalHost:8080/ComputerDatabase";
    private WebElement element;
    private static JavascriptExecutor js;


    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.firefox.driver", "lib/firefox");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }

    @Test
    public void testNavigateToDashboard() {
        driver.get(homeDir);
        driver.findElement(By.id("goToDashborad")).click();
        element = driver.findElement(By.id("dashboard"));
        Assert.assertNotNull(element);
    }
}
*/
