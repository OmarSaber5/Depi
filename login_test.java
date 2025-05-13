package Selenium_project.Automation_selenium_project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

public class login_test  {
    WebDriver driver = new ChromeDriver();
    String URL = "https://automationexercise.com";
    Actions actions;

    @BeforeTest
    public void openBrowser() {
        driver.manage().window().maximize();
        driver.get(URL);
        actions = new Actions(driver);
    }

    @Test(priority = 1)
    public void verifyHomePage() {
        WebElement homeLink = driver.findElement(By.xpath("//a[@href='/']"));
        Assert.assertTrue(homeLink.isDisplayed(), "Home page is not visible");
        System.out.println(" Home page verified");
    }

    @Test(priority = 2, dependsOnMethods = {"verifyHomePage"})
    public void openProductsPage() {
        driver.findElement(By.xpath("//a[@href='/products']")).click();
        System.out.println(" Navigated to Products page");
    }

    @Test(priority = 3, dependsOnMethods = {"openProductsPage"})
    public void addFirstProductToCart() {
        WebElement firstProduct = driver.findElement(By.xpath("(//div[@class='productinfo text-center'])[1]"));
        actions.moveToElement(firstProduct).perform();
        driver.findElement(By.xpath("(//a[contains(text(),'Add to cart')])[1]")).click();
        driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();
        System.out.println(" First product added to cart");
    }

    @Test(priority = 4, dependsOnMethods = {"addFirstProductToCart"})
    public void addSecondProductToCart() {
        WebElement secondProduct = driver.findElement(By.xpath("(//div[@class='productinfo text-center'])[2]"));
        actions.moveToElement(secondProduct).perform();
        driver.findElement(By.xpath("(//a[contains(text(),'Add to cart')])[3]")).click();
        driver.findElement(By.xpath("//u[text()='View Cart']")).click();
        System.out.println(" Second product added to cart and cart opened");
    }

    @Test(priority = 5, dependsOnMethods = {"addSecondProductToCart"})
    public void verifyCartContents() {
        WebElement product1 = driver.findElement(By.xpath("//tr[1]"));
        WebElement product2 = driver.findElement(By.xpath("//tr[2]"));
        Assert.assertTrue(product1.isDisplayed(), "❌ Product 1 not in cart");
        Assert.assertTrue(product2.isDisplayed(), "❌ Product 2 not in cart");

        String quantity1 = driver.findElement(By.xpath("//tr[1]/td[4]")).getText();
        String quantity2 = driver.findElement(By.xpath("//tr[2]/td[4]")).getText();

        Assert.assertTrue(Integer.parseInt(quantity1) > 0, "❌ Invalid quantity for product 1");
        Assert.assertTrue(Integer.parseInt(quantity2) > 0, "❌ Invalid quantity for product 2");

        System.out.println(" Both products verified in cart with valid quantities");
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
        System.out.println(" Browser closed");
    }
}
