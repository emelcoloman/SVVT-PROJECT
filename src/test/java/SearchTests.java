import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchTests extends BaseTest {

    @Test
    public void searchAndSelectGame() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.greenmangaming.com/");

        WebElement searchBox = driver.findElement(By.id("search-input"));
        searchBox.sendKeys("Resident Evil Requiem");
        searchBox.sendKeys(Keys.ENTER);

        wait.until(d -> d.findElement(By.cssSelector("li.ais-Hits-item")));

        driver.findElement(By.cssSelector("li.ais-Hits-item a")).click();

        driver.quit();
    }

    @Test
    public void searchNoResults() {
        driver.get("https://www.greenmangaming.com/");

        WebElement searchBox = driver.findElement(By.id("search-input"));
        searchBox.sendKeys("asdkjasdkjaksdjasd");
        searchBox.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement stats = wait.until(d -> {
            WebElement el = d.findElement(By.cssSelector("div.ais-Stats span.ais-Stats-text"));
            if (!el.getText().trim().isEmpty()) {
                return el;
            } else {
                return null;
            }
        });

        int numResults = Integer.parseInt(stats.getText().trim());
        Assertions.assertEquals(0, numResults, "Expected no search results, but found some.");
    }


    @Test
    public void searchSpecialCharacters() {
        driver.get("https://www.greenmangaming.com/");
        type(By.id("search-input"), "!@#$%^&*()");
        driver.findElement(By.id("search-input")).sendKeys(Keys.ENTER);
        waitFor(By.cssSelector("ol.ais-Hits-list"));
    }

}
