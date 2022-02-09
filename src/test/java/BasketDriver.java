import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasketDriver {

    private final WebDriver driver;
    private final String baseUrl;

    public BasketDriver(WebDriver driver){
        this.driver = driver;
        //baseUrl = "https://guitarshackbasket.azurewebsites.net/";
        baseUrl = "http://localhost:5000/";
    }

    public int getQuantity(String product) {
        WebElement quantityTableCell = driver.findElement(
                By.xpath(".//*[@id='items']/tbody/tr[contains(td[0], " + product + ")]/td[@id='quantity']"));
        return Integer.parseInt(quantityTableCell.getText());
    }

    public double getTotal() {
        WebElement totalText = driver.findElement(By.id("total"));
        return Double.parseDouble(totalText.getAttribute("value"));
    }


    public void add(String productDescription) {
        clickLink("Products");
        clickLink(productDescription);
        clickButton("add_to_basket");
    }

    public void checkout() {
        clickLink("Basket");
    }

    public void home() {
        driver.get(baseUrl);
    }

    private void clickButton(String id) {
        WebElement addToBasketButton = driver.findElement(By.id(id));
        addToBasketButton.click();
    }

    private void clickLink(String linkText) {
        WebElement productsLink = driver.findElement(By.partialLinkText(linkText));
        productsLink.click();
    }

    public int getProductStock(String description) {
        clickLink("Products");
        clickLink(description);
        WebElement productStock = driver.findElement((By.id("stock")));
        return Integer.parseInt(productStock.getText());
    }

    public boolean addIsEnabled(String product) {
        clickLink("Products");
        clickLink(product);
        WebElement addToBasket = driver.findElement(By.id("add_to_basket"));
        return addToBasket.isEnabled();
    }

    public int getItemCount() {
        List<WebElement> items = driver.findElements(By.xpath(".//*[@id='items']/tbody/tr"));
        return items.size();
    }
}
