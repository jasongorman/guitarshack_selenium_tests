import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class BasketTest {

    private ChromeDriver driver;
    private BasketDriver basket;

    @Test
    public void totalOfEmptyBasket() throws InterruptedException {
        basket.checkout();
        double total = basket.getTotal();
        assertEquals(0, basket.getItemCount());
        assertEquals(0.0, total, 0.0);
    }


    @Test
    public void totalOfSingleItem() throws InterruptedException {
        basket.add("Epiphone");
        basket.checkout();
        assertEquals(1, basket.getItemCount());
        assertEquals(1, basket.getQuantity("Epiphone"));
        assertEquals(399.95, basket.getTotal(), 0.0);
    }

    @Test
    public void totalOfTwoItems() throws InterruptedException {
        basket.add("Epiphone");
        basket.add("Fender");
        basket.checkout();
        assertEquals(2, basket.getItemCount());
        assertEquals(978.95, basket.getTotal(), 0.0);
    }

    @Test
    public void totalOfItemWithQuantityOfTwo() throws InterruptedException {
        basket.add("Epiphone");
        basket.add("Epiphone");
        basket.checkout();
        assertEquals(1, basket.getItemCount());
        assertEquals(2, basket.getQuantity("Epiphone"));
        assertEquals(799.9, basket.getTotal(), 0.0);
    }

    @Test
    public void addingProductToBasketReducesStock(){
        basket.add("Epiphone");
        assertEquals(4, basket.getProductStock("Epiphone"));
    }

    @Test
    public void cannotAddOutOfStockProductToBasket(){
        assertFalse(basket.addIsEnabled("Ibanez Tube Screamer"));
    }

    @Before
    public void setUpChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/webdriver/chrome/chromedriver.exe");
        driver = new ChromeDriver();
        basket = new BasketDriver(driver);
        basket.home();
    }

    @After
    public void teardown(){
        driver.quit();
    }
}
