package org.example.task2;


import org.example.task2.driver.DriverManager;
import org.example.task2.model.Format;
import org.example.task2.model.Product;
import org.example.task2.pages.CartPage;
import org.example.task2.pages.HomePage;
import org.example.task2.pages.ProductPage;
import org.example.task2.pages.ResultsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingCartTest {
    private final DriverManager driverManager = new DriverManager();
    private WebDriver driver;


    @BeforeEach
    public void init() {
        driver = driverManager.getDriver();

    }

    @Test
    public void addItemToBasketTest() {
        HomePage homePage = new HomePage(driver).get();
        assertEquals(driver.getCurrentUrl(), "https://www.amazon.com/");

        String expectedTitle = "Harry Potter and the Cursed Child, Parts One and Two";
        Float expectedPrice = 10.37f;
        Format expectedFormat = Format.PAPERBACK;

        homePage.getNavPage().searchFor(expectedTitle);
        ResultsPage resultsPage = new ResultsPage(driver);
        Product book = resultsPage
                .getBookResults()
                .get(0);

        String actualTitle = book.getTitle();
        assertTrue(actualTitle.toLowerCase().contains(expectedTitle.toLowerCase()));

        Format actualFormat = book.getFormat();
        assertEquals(expectedFormat, actualFormat);

        Float actualPrice = book.getPrice();
        assertEquals(expectedPrice, actualPrice);

        ProductPage productPage = resultsPage.clickOnResultBy(expectedTitle, Format.PAPERBACK);
        actualTitle = productPage.getTitle();
        assertTrue(actualTitle.toLowerCase().contains(expectedTitle.toLowerCase()));

        actualFormat = productPage.getSelectedFormat();
        assertEquals(expectedFormat, actualFormat);

        actualPrice = productPage.getSelectedFormatPrice();
        assertEquals(expectedPrice, actualPrice);

        productPage.setQuantity(2);
        productPage.addProductToCart();

        CartPage cartPage = homePage.getNavPage().openCart();
        int actualNumberOfItems = cartPage.getItemCount();
        assertEquals(1, actualNumberOfItems);

        actualTitle = cartPage.getItemTitles().get(0);
        assertTrue(actualTitle.toLowerCase().contains(expectedTitle.toLowerCase()));

        int actualQuantity = cartPage.getItemQuantityFor(expectedTitle);
        assertEquals(2, actualQuantity);

        actualPrice = cartPage.getItemPrice(expectedTitle);
        assertEquals(expectedPrice, actualPrice);

        actualFormat = cartPage.getItemFormat(expectedTitle);
        assertEquals(expectedFormat, actualFormat);
    }

    @AfterEach
    public void tearDown() {
        driverManager.quitDriver();
    }
}
