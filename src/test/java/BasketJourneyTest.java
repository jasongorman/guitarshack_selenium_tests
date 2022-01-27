import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BasketJourneyTest {

    private static ChromeDriver driver;
    private static BasketDriver basket;

    @Test
    public void a_totalOfBasket() throws InterruptedException {
        basket.checkout();
        assertEquals(0.0, basket.getTotal(), 0.0);
    }

    @Test
    public void b_totalOfSingleItem() {
        basket.add("Epiphone");
        basket.checkout();
        assertEquals(399.95, basket.getTotal(), 0.0);
    }

    @Test
    public void c_totalOfItemWithQuantityTwo() {
        basket.add("Epiphone");
        basket.checkout();
        assertEquals(2, basket.getQuantity());
        assertEquals(799.9, basket.getTotal(), 0.0);
    }

    @Test
    public void d_totalOfTwoItems(){
        basket.add("Fender");
        basket.checkout();
        assertEquals(1378.9, basket.getTotal(), 0.0);
    }

    @BeforeClass
    public static void setUpChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/webdriver/chrome/chromedriver.exe");
        driver = new ChromeDriver();
        basket = new BasketDriver(driver);
        basket.home();
    }

    @AfterClass
    public static void teardown(){
        driver.quit();
    }
}
