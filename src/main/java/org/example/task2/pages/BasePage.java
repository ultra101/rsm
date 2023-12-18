package org.example.task2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    public final int DEFAULT_TIMEOUT = 5;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected Wait<WebDriver> wait(int duration) {
        return new WebDriverWait(driver, Duration.ofSeconds(duration));
    }
}
