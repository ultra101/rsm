package org.example.task2.pages;

import org.example.task2.model.Format;
import org.example.task2.model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ResultsPage extends BasePage {
    private final By resultsLocator = By.xpath("//div[@id='search']//div[@data-component-type='s-search-result']");
    private final By productTitleLocator = By.cssSelector("div[data-cy='title-recipe'] > h2 > a > span");
    private final String productNameSelector = "//div[@data-cy='title-recipe']//span[contains(text(),'%s')]";
    private final By productTypeLocator = By.cssSelector("div.puis-list-col-right div.puisg-col-inner > div.a-section a.a-text-bold");
    private final By priceLocator = By.xpath("parent::div/parent::div//a/span[@class='a-price']/span[@class='a-offscreen']");


    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<Product> getBookResults() {
        List<Product> books = new ArrayList<>();

        getResultElements().forEach(el -> {
            WebElement productTitleEl = el.findElement(productTitleLocator);
            String title = productTitleEl.getText();
            assertFalse(title.isEmpty());

            List<WebElement> productsPerTitle = el.findElements(productTypeLocator);
            Format bookType;
            Float itemPrice;

            if (!productsPerTitle.isEmpty()) {
                for (WebElement typeElement : productsPerTitle) {
                    String typeString = typeElement.getText();
                    bookType = Format.getByValue(typeString);
                    itemPrice = getPricePerProductType(typeElement);
                    books.add(new Product(title, bookType, itemPrice));
                }
            }
        });
        return books;
    }

    public ProductPage clickOnResultBy(String title, Format type) {
        By productNameLocator = By.xpath(String.format(productNameSelector, title));
        driver
                .findElement(productNameLocator)
                .findElement(By.xpath(String.format("//a[text()='%s']", type.value)))
                .click();

        return new ProductPage(driver);
    }

    private List<WebElement> getResultElements() {
        return wait(DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(resultsLocator));
    }

    private Float getPricePerProductType(WebElement element) {
        Float price = null;
        List<WebElement> priceElements = element.findElements(priceLocator);

        if (!priceElements.isEmpty()) {
            String stringPrice = priceElements.get(0).getAttribute("innerHTML").substring(1);
            price = Float.parseFloat(stringPrice);
        }
        return price;
    }
}
