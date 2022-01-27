import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasketDriver {

    private final WebDriver driver;

    public BasketDriver(WebDriver driver){
        this.driver = driver;
    }

    public int getQuantity() {
        WebElement quantityTableCell = driver.findElement(By.id("quantity"));
        int quantity = Integer.parseInt(quantityTableCell.getText());
        return quantity;
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
        driver.get("http://localhost:5000");
    }

    private void clickButton(String id) {
        WebElement addToBasketButton = driver.findElement(By.id(id));
        addToBasketButton.click();
    }

    private void clickLink(String linkText) {
        WebElement productsLink = driver.findElement(By.partialLinkText(linkText));
        productsLink.click();
    }
}
