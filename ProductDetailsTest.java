package Selenium_project.Automation_selenium_project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class ProductDetailsTest {

    WebDriver driver = new ChromeDriver();
    String URL = "https://automationexercise.com";

    @BeforeTest
    public void openBrowser() {
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @Test(priority = 1)
    public void verifyHomePageVisible() {
        WebElement homeElement = driver.findElement(By.xpath("//a[@href='/']"));
        Assert.assertTrue(homeElement.isDisplayed(), "❌ Home page is not visible");
        System.out.println("✅ Home page is visible");
    }

    @Test(priority = 2, dependsOnMethods = "verifyHomePageVisible")
    public void openProductsPage() {
        driver.findElement(By.xpath("//a[@href='/products']")).click();

        WebElement allProductsTitle = driver.findElement(By.xpath("//h2[contains(text(),'All Products')]"));
        Assert.assertTrue(allProductsTitle.isDisplayed(), "❌ Not navigated to All Products page");

        List<WebElement> productList = driver.findElements(By.className("product-image-wrapper"));
        Assert.assertTrue(productList.size() > 0, "❌ Products list is not visible");

        System.out.println("✅ All Products page and product list are visible");
    }

    @Test(priority = 3, dependsOnMethods = "openProductsPage")
    public void openFirstProductDetail() {
        WebElement viewProductBtn = driver.findElement(By.xpath("(//a[contains(text(),'View Product')])[1]"));
        viewProductBtn.click();

        WebElement productInfoSection = driver.findElement(By.xpath("//div[@class='product-information']"));
        Assert.assertTrue(productInfoSection.isDisplayed(), "❌ Product detail page not visible");

        System.out.println("✅ Navigated to product detail page");
    }

    @Test(priority = 4, dependsOnMethods = "openFirstProductDetail")
    public void verifyProductDetailsVisible() {
        WebElement name = driver.findElement(By.xpath("//div[@class='product-information']//h2"));
        WebElement category = driver.findElement(By.xpath("//div[@class='product-information']//p[contains(text(),'Category')]"));
        WebElement price = driver.findElement(By.xpath("//div[@class='product-information']//span/span"));
        WebElement availability = driver.findElement(By.xpath("//div[@class='product-information']//p[contains(text(),'Availability')]"));
        WebElement condition = driver.findElement(By.xpath("//div[@class='product-information']//p[contains(text(),'Condition')]"));
        WebElement brand = driver.findElement(By.xpath("//div[@class='product-information']//p[contains(text(),'Brand')]"));

        Assert.assertTrue(name.isDisplayed(), "❌ Product name not visible");
        Assert.assertTrue(category.isDisplayed(), "❌ Category not visible");
        Assert.assertTrue(price.isDisplayed(), "❌ Price not visible");
        Assert.assertTrue(availability.isDisplayed(), "❌ Availability not visible");
        Assert.assertTrue(condition.isDisplayed(), "❌ Condition not visible");
        Assert.assertTrue(brand.isDisplayed(), "❌ Brand not visible");

        System.out.println("✅ All product details are visible:");
        System.out.println("   Name: " + name.getText());
        System.out.println("   " + category.getText());
        System.out.println("   Price: " + price.getText());
        System.out.println("   " + availability.getText());
        System.out.println("   " + condition.getText());
        System.out.println("   " + brand.getText());
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
        System.out.println("🔒 Browser closed");
    }
}
