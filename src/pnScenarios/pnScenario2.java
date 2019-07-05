package pnScenarios;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class pnScenario2 {
    public static void main(String[] args) {

        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 15);

        driver.get("https://app.papernest.com/onboarding?anonymous=true");
        System.out.println("0. La page se charge");

        (new WebDriverWait(driver, 15)).until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                return d.findElement(By.id("animation-container"));
            }
        });
        System.out.println("1. La page est chargée");

        driver.findElement(By.cssSelector(".banner-container__agree")).click();
        System.out.println("2. cookies agreement");

        driver.findElement(By.xpath(("//img[contains(@src,'https://assets.papernest.com/dashboard/picto_newspaper.svg')]"))).click();
        System.out.println("3. Faire suivre... ");

        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)=concat('Demander gratuitement le changement d', \"'\", 'adresse')])[1]/following::span[1]")).click();
        System.out.println("4. Demander gratuitement... ");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Capital'])[1]/following::div[1]")).click();
        System.out.println("5. selection magazine... ");

        (new WebDriverWait(driver, 15)).until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                return d.findElement(By.id("newspaper.reference"));
            }
        });


        System.out.println("6. saisie numero abonné... ");
        driver.findElement(By.id("newspaper.reference")).sendKeys("484848");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".picto")));
        System.out.println("7. numero abonné saisie... ");
        driver.findElement(By.xpath("//button/span")).click();


        System.out.println("8. saisie adresse... ");
        (new WebDriverWait(driver, 15)).until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                return d.findElement(By.id("housing.address"));
            }
        });

        driver.findElement(By.id("housing.address")).sendKeys("92 rue réaumur");

        (new WebDriverWait(driver, 15)).until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                return d.findElement(By.linkText("92 Rue Réaumur 75002 Paris"));
            }
        });
        driver.findElement(By.linkText("92 Rue Réaumur 75002 Paris")).click();

        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Suivant'])[1]/following::span[1]")).click();

        String email = (new Timestamp(System.currentTimeMillis()).getTime()+"+test@papernest.com");
        driver.findElement(By.id("user.email")).sendKeys(email);
        driver.findElement(By.id("user.phone_number")).sendKeys("06 00 00 00 00");
        driver.findElement(By.id("user.first_name")).sendKeys("Test");
        driver.findElement(By.id("user.last_name")).sendKeys("Selenium");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[5]/div/ppn-question-layout/div[2]/ppn-input-text/div/div/i")));
        driver.findElement(By.xpath("//button/span")).click();
        System.out.println("9. coordonnées saisies ");




        driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='D'])[1]/following::span[18]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='D'])[1]/following::span[44]")).click();

        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Suivant'])[1]/following::span[1]")).click();

        System.out.println("10. date saisie");

        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Valider'])[1]/following::span[1]")).click();

        System.out.println("11. date validée");
        System.out.println(driver.findElement(By.cssSelector("h1.title")).getText());


        try {
            assertTrue(driver.findElement(By.cssSelector("h1.title")).getText().contains("Est-ce que tout est bon Test"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        driver.findElement(By.id("button_validate_newspaper")).click();
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Entrée'])[1]/following::h4[1]")));

        driver.findElement(By.xpath("//button[@id='_validaton_ar']/span")).click();
        driver.findElement(By.xpath("//ppn-progression/div/div[2]/div/span")).click();
        try {
            assertTrue(driver.findElement(By.xpath("//progression-table[@id='_progress_newspaper']/ppn-progression/div/div[2]/div/span")).getText().contains("Demande de changement d'adresse"));
        } catch (Error e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}