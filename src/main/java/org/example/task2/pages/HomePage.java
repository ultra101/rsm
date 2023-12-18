package org.example.task2.pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private NavigationBarPage navPage;

    public HomePage(WebDriver driver) {
        super(driver);
        navPage = new NavigationBarPage(this.driver);
    }

    public HomePage get() {
        driver.get("https://www.amazon.com/");

        return this;
    }

    public NavigationBarPage getNavPage() {
        return navPage;
    }

}
