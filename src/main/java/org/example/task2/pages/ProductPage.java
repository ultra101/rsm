package org.example.task2.pages;

import org.example.task2.model.Format;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return wait(DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(By.id("productTitle"))).getText();
    }

    public Format getSelectedFormat() {
        By selectedFormatLocator = By.cssSelector("#formats div.selected span.slot-title");
        String format = wait(DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(selectedFormatLocator))
                .getText();
        return Format.getByValue(format);
    }

    public Float getSelectedFormatPrice() {
        By selectedFormatPriceLocator = By.cssSelector("#formats div.selected span.slot-price");
        String format = driver.findElement(selectedFormatPriceLocator).getText().substring(1);
        return Float.parseFloat(format);
    }

    public ProductPage setQuantity(int quantity) {
        WebElement dropDown = driver.findElement(By.id("selectQuantity"));
        dropDown.click();
        wait(DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(dropDown.findElement(By.id("quantity"))))
                .sendKeys(String.valueOf(quantity));

        return this;
    }

    public void addProductToCart() {
        driver.findElement(By.id("add-to-cart-button")).click();
    }
}
