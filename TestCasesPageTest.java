package Selenium_project.Automation_selenium_project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestCasesPageTest {

    WebDriver driver = new ChromeDriver();
    String URL = "https://automationexercise.com";

    @BeforeTest
    public void openBrowser() {
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @Test(priority = 1)
    public void verifyHomePageVisible() {
        WebElement homeBanner = driver.findElement(By.xpath("//a[@href='/']"));
        Assert.assertTrue(homeBanner.isDisplayed(), "❌ Home page not visible");
        System.out.println("✅ Home page is visible");
    }

    @Test(priority = 2, dependsOnMethods = {"verifyHomePageVisible"})
    public void navigateToTestCasesPage() {
        // الضغط على زر Test Cases
        WebElement testCasesBtn = driver.findElement(By.xpath("//a[@href='/test_cases']"));
        testCasesBtn.click();

        // التحقق من أن العنوان يحتوي على "Test Cases"
        WebElement testCasesTitle = driver.findElement(By.xpath("//h2[contains(text(),'Test Cases')]"));
        Assert.assertTrue(testCasesTitle.isDisplayed(), "❌ Failed to navigate to Test Cases page");

        System.out.println("✅ Navigated to Test Cases page successfully");
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
        System.out.println("🔒 Browser closed");
    }
}
