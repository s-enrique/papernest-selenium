package pnScenarios;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pnscenario1  {
    public static void main(String[] args) {

        WebDriver driver = new FirefoxDriver();

        

        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        WebElement element = driver.findElement(By.name("q"));

        element.sendKeys("toto!");
        element.submit();


        System.out.println("Page title is: " + driver.getTitle());
        

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("toto!");
            }
        });


        System.out.println("Page title is: " + driver.getTitle());
        

        driver.quit();
    }
}