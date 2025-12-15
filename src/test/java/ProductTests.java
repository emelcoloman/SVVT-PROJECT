import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductTests extends BaseTest {

    @Test
    public void openFirstProduct() {
        driver.get("https://www.greenmangaming.com/");

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.id("privacy_pref_optin"))).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.col-xs-12.col-sm-4.module"))).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/games/carmen-sandiego-pc/"));

        Assertions.assertEquals(
                "https://www.greenmangaming.com/games/carmen-sandiego-pc/",
                driver.getCurrentUrl(),
                "The product page URL is incorrect!"
        );
    }


    @Test
    public void productHasPrice() {
        driver.get("https://www.greenmangaming.com/");
        click(By.cssSelector(".product-list-item a"));
        waitFor(By.cssSelector(".price"));
    }

    @Test
    public void productHasPlatformIcons() {
        driver.get("https://www.greenmangaming.com/");
        click(By.cssSelector(".product-list-item a"));
        waitFor(By.cssSelector(".game-platforms, .platform-icons"));
    }

    @Test
    public void productHasRequirementsTab() {
        driver.get("https://www.greenmangaming.com/");
        click(By.cssSelector(".product-list-item a"));
        click(By.cssSelector("a[href='#requirements']"));
        waitFor(By.cssSelector("#requirements"));
    }

    @Test
    public void addToWishlist() {
        driver.get("https://www.greenmangaming.com/");
        click(By.cssSelector(".product-list-item a"));

        // wishlist icon varies
        click(By.cssSelector("button[data-gtm-id='wishlist']"));
        waitFor(By.cssSelector(".wishlist-added, .in-wishlist"));
    }
}
