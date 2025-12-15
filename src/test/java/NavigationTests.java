import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavigationTests extends BaseTest {

    @Test
    public void openDeals() {
        driver.get("https://www.greenmangaming.com/");
        click(By.linkText("HOT DEALS"));
        waitFor(By.cssSelector(".tabnav"));
    }

    @Test
    public void openBestSellers() {
        driver.get("https://www.greenmangaming.com/");
        click(By.linkText("COMING SOON"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".header-name"), "COMING"));
    }

    @Test
    public void openComingSoon() {
        driver.get("https://www.greenmangaming.com/");
        click(By.linkText("NEW"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".header-name"), "NEW"));
    }
}
