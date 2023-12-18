package org.example.task2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavigationBarPage extends BasePage {
    public NavigationBarPage(WebDriver driver) {
        super(driver);
    }

    public void searchFor(String searchTerm) {
        By searchBarLocator = By.xpath("//input[@id='twotabsearchtextbox' or @id='nav-bb-search']");
        WebElement searchInput = wait(DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(searchBarLocator));
        searchInput.click();
        searchInput.sendKeys(searchTerm);
        searchInput.submit();
    }

    public CartPage openCart() {
        driver.findElement(By.id("nav-cart")).click();
        return new CartPage(driver);
    }
}
