package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VoegToeValidatieTest {
    private WebDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\timva\\Documents\\UCLL\\belangrijke files\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/R0716032_nieuw_project_war_exploded/voegToe.jsp");
    }

    @After
    public void clean() {
       driver.close();
    }


    @Test
    public void formulier_wordt_opnieuw_getoond_met_error_messages_als_alle_velden_leeg_zijn(){
        WebElement naam = driver.findElement(By.name("naam"));
        naam.clear();
        naam.sendKeys("");

        WebElement hoeveelMensen = driver.findElement(By.name("hoeveelMensen"));
        hoeveelMensen.clear();
        hoeveelMensen.sendKeys("");

        WebElement verblijfLengte = driver.findElement(By.name("verblijfLengte"));
        verblijfLengte.clear();
        verblijfLengte.sendKeys("");

        WebElement datum = driver.findElement(By.name("startDatum"));
        datum.clear();
        datum.sendKeys("");

        WebElement knop = driver.findElement(By.id("knopform"));
        knop.submit();

        assertEquals("vul dit in om een reservatie te plaatsen", driver.findElement(By.tagName("h2")).getText());
        ArrayList<WebElement>lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(heeftWebelementenMetTekst(lis, "Naam is leeg"));
        assertTrue(heeftWebelementenMetTekst(lis, "hoeveelheid mensen is leeg"));
        assertTrue(heeftWebelementenMetTekst(lis, "lengte van verblijf is leeg"));
        assertTrue(heeftWebelementenMetTekst(lis, "Datum is leeg"));
    }

    @Test
    public void Test_formulier_wordt_opnieuw_getoond_met_error_message_als_naam_leeg_is(){
        WebElement naam = driver.findElement(By.name("naam"));
        naam.clear();
        naam.sendKeys("");

        WebElement hoeveelMensen = driver.findElement(By.name("hoeveelMensen"));
        hoeveelMensen.clear();
        hoeveelMensen.sendKeys("1");

        WebElement verblijfLengte = driver.findElement(By.name("verblijfLengte"));
        verblijfLengte.clear();
        verblijfLengte.sendKeys("2");

        WebElement datum = driver.findElement(By.name("startDatum"));
        datum.clear();
        datum.sendKeys("26-11-2020");

        WebElement knop = driver.findElement(By.id("knopform"));
        knop.submit();

        assertEquals("vul dit in om een reservatie te plaatsen", driver.findElement(By.tagName("h2")).getText());

        ArrayList<WebElement>lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(heeftWebelementenMetTekst(lis, "Naam is leeg"));

    }

    @Test
    public void Test_reservatie_toevoegen_met_al_gereserveerde_datum_geeft_errormessage(){
        WebElement naam = driver.findElement(By.name("naam"));
        naam.clear();
        naam.sendKeys("tim");

        WebElement hoeveelMensen = driver.findElement(By.name("hoeveelMensen"));
        hoeveelMensen.clear();
        hoeveelMensen.sendKeys("1");

        WebElement verblijfLengte = driver.findElement(By.name("verblijfLengte"));
        verblijfLengte.clear();
        verblijfLengte.sendKeys("2");

        WebElement datum = driver.findElement(By.name("startDatum"));
        datum.clear();
        datum.sendKeys("26-11-2020");

        WebElement knop = driver.findElement(By.id("knopform"));
        knop.submit();

        assertEquals("Overzicht van alle bezoekers", driver.findElement(By.tagName("h2")).getText());


        WebElement navigatieVoegToeKnop = driver.findElement(By.linkText("Voeg Toe"));
        navigatieVoegToeKnop.click();
        assertEquals("vul dit in om een reservatie te plaatsen", driver.findElement(By.tagName("h2")).getText());

        WebElement naam2 = driver.findElement(By.name("naam"));
        naam2.clear();
        naam2.sendKeys("tim");

        WebElement hoeveelMensen2 = driver.findElement(By.name("hoeveelMensen"));
        hoeveelMensen2.clear();
        hoeveelMensen2.sendKeys("1");

        WebElement verblijfLengte2 = driver.findElement(By.name("verblijfLengte"));
        verblijfLengte2.clear();
        verblijfLengte2.sendKeys("2");

        WebElement datum2 = driver.findElement(By.name("startDatum"));
        datum2.clear();
        datum2.sendKeys("26-11-2020");

        WebElement knop2 = driver.findElement(By.id("knopform"));
        knop2.submit();

        assertEquals("vul dit in om een reservatie te plaatsen", driver.findElement(By.tagName("h2")).getText());
        ArrayList<WebElement>lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(heeftWebelementenMetTekst(lis, "datum van deze bezoeker overlapt met bestaande bezoeker"));

    }

    private boolean heeftWebelementenMetTekst(ArrayList<WebElement> elements, String text){
        for (WebElement w: elements){
            if (w.getText().equals(text)){
                return true;
            }
        }
        return false;
    }

}
