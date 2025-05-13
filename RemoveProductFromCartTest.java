package Selenium_project.Automation_selenium_project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class RemoveProductFromCartTest {

    WebDriver driver = new ChromeDriver();
    String URL = "https://automationexercise.com";

    @BeforeTest
    public void openBrowser() {
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @Test(priority = 1)
    public void verifyHomePageVisible() {
        WebElement homeLink = driver.findElement(By.xpath("//a[@href='/']"));
        Assert.assertTrue(homeLink.isDisplayed(), "❌ Home page not visible");
        System.out.println(" Home page is visible");
    }

    @Test(priority = 2, dependsOnMethods = {"verifyHomePageVisible"})
    public void addProductToCart() {
        driver.findElement(By.xpath("//a[@href='/products']")).click();

        driver.findElement(By.xpath("(//a[contains(text(),'Add to cart')])[1]")).click();

        driver.findElement(By.xpath("//u[text()='View Cart']")).click();

        System.out.println(" Product added to cart and navigated to cart page");
    }

    @Test(priority = 3, dependsOnMethods = {"addProductToCart"})
    public void verifyCartPageDisplayed() {
        WebElement cartTitle = driver.findElement(By.xpath("//section[@id='cart_items']"));
        Assert.assertTrue(cartTitle.isDisplayed(), "❌ Cart page not visible");
        System.out.println(" Cart page is visible");
    }

    @Test(priority = 4, dependsOnMethods = {"verifyCartPageDisplayed"})
    public void removeProductFromCart() throws InterruptedException {
        WebElement deleteBtn = driver.findElement(By.xpath("//a[@class='cart_quantity_delete']"));
        deleteBtn.click();

        Thread.sleep(2000);

        boolean isProductRemoved = driver.findElements(By.xpath("//tr[1]/td[@class='cart_description']")).isEmpty();
        Assert.assertTrue(isProductRemoved, "❌ Product was not removed from the cart");
        System.out.println(" Product removed from cart successfully");
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
        System.out.println(" Browser closed");
    }
}
