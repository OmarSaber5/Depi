package Selenium_project.Automation_selenium_project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class SearchProductTest {

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

        System.out.println("✅ Navigated to All Products page");
    }

    @Test(priority = 3, dependsOnMethods = "openProductsPage")
    public void searchForProduct() {
        // Step 6: البحث عن منتج
        WebElement searchInput = driver.findElement(By.id("search_product"));
        WebElement searchButton = driver.findElement(By.id("submit_search"));

        searchInput.sendKeys("Tshirt");
        searchButton.click();

        // Step 7: تحقق من ظهور عنوان SEARCHED PRODUCTS
        WebElement searchedTitle = driver.findElement(By.xpath("//h2[contains(text(),'Searched Products')]"));
        Assert.assertTrue(searchedTitle.isDisplayed(), "❌ 'Searched Products' title is not visible");
        System.out.println("✅ 'Searched Products' section is visible");

        // Step 8: تحقق من أن المنتجات الظاهرة متعلقة بالبحث
        List<WebElement> searchedProducts = driver.findElements(By.xpath("//div[@class='features_items']//div[@class='productinfo text-center']"));
        Assert.assertTrue(searchedProducts.size() > 0, "❌ No search results found");

        System.out.println("✅ Search returned " + searchedProducts.size() + " product(s)");
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
        System.out.println("🔒 Browser closed");
    }
}
