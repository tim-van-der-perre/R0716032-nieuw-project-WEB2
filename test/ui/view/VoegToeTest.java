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

public class VoegToeTest {
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
    public void Test_Leeg_formulier_submitten_blijft_op_formulier(){
        WebElement knop = driver.findElement(By.id("knopform"));
        knop.submit();
        assertEquals("vul dit in om een reservatie te plaatsen", driver.findElement(By.tagName("h2")).getText());
    }
    @Test
    public void Test_lege_naam_blijft_op_formulier(){
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


    }@Test
    public void Test_lege_hoeveelheid_mensen_blijft_op_formulier(){
        WebElement naam = driver.findElement(By.name("naam"));
        naam.clear();
        naam.sendKeys("tim");

        WebElement hoeveelMensen = driver.findElement(By.name("hoeveelMensen"));
        hoeveelMensen.clear();
        hoeveelMensen.sendKeys("");

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
        assertTrue(heeftWebelementenMetTekst(lis, "hoeveelheid mensen is leeg"));

    }@Test
    public void Test_lege_lengte_van_verblijf_blijft_op_formulier() {
        WebElement naam = driver.findElement(By.name("naam"));
        naam.clear();
        naam.sendKeys("tim");

        WebElement hoeveelMensen = driver.findElement(By.name("hoeveelMensen"));
        hoeveelMensen.clear();
        hoeveelMensen.sendKeys("1");

        WebElement verblijfLengte = driver.findElement(By.name("verblijfLengte"));
        verblijfLengte.clear();
        verblijfLengte.sendKeys("");

        WebElement datum = driver.findElement(By.name("startDatum"));
        datum.clear();
        datum.sendKeys("26-11-2020");

        WebElement knop = driver.findElement(By.id("knopform"));
        knop.submit();

        assertEquals("vul dit in om een reservatie te plaatsen", driver.findElement(By.tagName("h2")).getText());

        ArrayList<WebElement>lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(heeftWebelementenMetTekst(lis, "lengte van verblijf is leeg"));


    }@Test
    public void Test_lege_datum_blijft_op_formulier(){
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
        datum.sendKeys("");

        WebElement knop = driver.findElement(By.id("knopform"));
        knop.submit();

        assertEquals("vul dit in om een reservatie te plaatsen", driver.findElement(By.tagName("h2")).getText());

        ArrayList<WebElement>lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(heeftWebelementenMetTekst(lis, "Datum is leeg"));

    }

    @Test
    public void Test_Correcte_invulvelden_gaan_naar_ovezicht(){
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

    }

    @Test
    public void Test_Correcte_invulvelden_voegen_contact_toe(){
        WebElement naam = driver.findElement(By.name("naam"));
        naam.clear();
        naam.sendKeys("test");

        WebElement hoeveelMensen = driver.findElement(By.name("hoeveelMensen"));
        hoeveelMensen.clear();
        hoeveelMensen.sendKeys("1");

        WebElement verblijfLengte = driver.findElement(By.name("verblijfLengte"));
        verblijfLengte.clear();
        verblijfLengte.sendKeys("2");

        WebElement datum = driver.findElement(By.name("startDatum"));
        datum.clear();
        datum.sendKeys("26-11-2025");

        WebElement knop = driver.findElement(By.id("knopform"));
        knop.submit();

        assertEquals("Overzicht van alle bezoekers", driver.findElement(By.tagName("h2")).getText());

        ArrayList<WebElement>tds = (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
        assertTrue(heeftWebelementenMetTekst(tds, "test"));


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
