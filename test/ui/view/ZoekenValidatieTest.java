package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class ZoekenValidatieTest {
    private WebDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\timva\\Documents\\UCLL\\belangrijke files\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/R0716032_nieuw_project_war_exploded/zoek.jsp");
    }

    @After
    public void clean() {
        driver.close();
    }


    @Test
    public void Test_Leeg_formulier_submitten_blijft_op_zoeken(){
        WebElement knop = driver.findElement(By.id("knopform"));
        knop.submit();
        assertEquals("bezoeker opzoeken", driver.findElement(By.tagName("h2")).getText());
    }

    @Test
    public void Test_ongeldige_naam_submitten_gaat_naar_helaas(){
        WebElement zoekbalkje = driver.findElement(By.name("bezoekersNaam"));
        zoekbalkje.sendKeys("ongeldigenaam");
        WebElement knop = driver.findElement(By.id("knopform"));
        knop.submit();
        assertEquals("Helaas!", driver.findElement(By.tagName("h2")).getText());
    }
    @Test
    public void Test_geldige_naam_submitten_toont_info(){
        WebElement zoekbalkje = driver.findElement(By.name("bezoekersNaam"));
        zoekbalkje.sendKeys("tim");
        WebElement knop = driver.findElement(By.id("knopform"));
        knop.submit();
        assertEquals("Gevonden!", driver.findElement(By.tagName("h2")).getText());
    }
}
