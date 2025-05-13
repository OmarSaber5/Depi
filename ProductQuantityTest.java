package Selenium_project.Automation_selenium_project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ProductQuantityTest {

    WebDriver driver = new ChromeDriver();
    String URL = "https://automationexercise.com";

    @BeforeTest
    public void openBrowser() {
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @Test(priority = 1)
    public void verifyHomePageIsVisible() {
        WebElement homeBanner = driver.findElement(By.xpath("//a[@href='/']"));
        Assert.assertTrue(homeBanner.isDisplayed(), "❌ Home page not visible");
        System.out.println(" Home page is visible");
    }

    @Test(priority = 2, dependsOnMethods = {"verifyHomePageIsVisible"})
    public void openProductDetails() {
        WebElement viewProduct = driver.findElement(By.xpath("(//a[@href='/product_details/1'])[1]"));
        viewProduct.click();
        System.out.println(" Clicked on View Product");
    }

    @Test(priority = 3, dependsOnMethods = {"openProductDetails"})
    public void verifyProductDetailAndAddQuantity() {
        WebElement productInfo = driver.findElement(By.xpath("//div[@class='product-information']"));
        Assert.assertTrue(productInfo.isDisplayed(), "❌ Product detail not visible");

        WebElement quantityBox = driver.findElement(By.xpath("//input[@id='quantity']"));
        quantityBox.clear();
        quantityBox.sendKeys("4");
        System.out.println(" Quantity set to 4");

        driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]")).click();

        driver.findElement(By.xpath("//u[text()='View Cart']")).click();
        System.out.println(" Product added to cart and cart opened");
    }

    @Test(priority = 4, dependsOnMethods = {"verifyProductDetailAndAddQuantity"})
    public void verifyCartQuantity() {
        WebElement productRow = driver.findElement(By.xpath("//tr[1]"));
        WebElement quantity = productRow.findElement(By.xpath("./td[4]"));

        String qtyText = quantity.getText().trim();
        Assert.assertEquals(qtyText, "4", "❌ Quantity in cart is not 4");

        System.out.println(" Product quantity in cart is 4");
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
        System.out.println(" Browser closed");
    }
}
