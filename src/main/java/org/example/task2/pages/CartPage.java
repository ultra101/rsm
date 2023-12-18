package org.example.task2.pages;

import org.example.task2.model.Format;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    private final String itemSelector = "#activeCartViewForm div.sc-list-item";
    private final By itemLocator = By.cssSelector(itemSelector);

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getItemCount() {
        return wait(5).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemLocator)).size();
    }

    public List<String> getItemTitles() {
        List<WebElement> titleElements = driver.findElements(By.cssSelector("div.sc-list-item-content span.a-truncate-full"));
        List<String> titleList = new ArrayList<>();
        titleElements.forEach(element -> titleList.add(element.getAttribute("innerHTML")));

        return titleList;
    }

    public int getItemQuantityFor(String title) {
        String quantitySelector = String.format("//span[//span[contains(text(),'%s')]][@class='a-dropdown-prompt']", title);
        return Integer.parseInt(driver.findElement(By.xpath(quantitySelector)).getText());
    }

    public Float getItemPrice(String title) {
        String priceSelector = String
                .format("//div[//span[contains(text(),'%s')]][@class='sc-list-item-content']" +
                        "//span[contains(@class,'sc-product-price')]", title);
        String priceString = driver.findElement(By.xpath(priceSelector)).getText().substring(1);
        return Float.valueOf(priceString);
    }

    public Format getItemFormat(String title) {
        String formatSelector = String
                .format("//div[//span[contains(text(),'%s')]][@class='sc-list-item-content']" +
                        "//span[contains(@class,'sc-product-binding')]", title);
        String formatString = driver.findElement(By.xpath(formatSelector)).getText();
        return Format.getByValue(formatString);
    }
}