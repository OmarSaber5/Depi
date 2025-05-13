package com.automationexercise.tests;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginUserTest extends BaseTest {
    @Test
    public void testLoginWithCorrectCredentials() {
        // 3. Verify that home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
        
        // 4. Click on 'Signup / Login' button
        homePage.clickSignupLogin();
        
        // 5. Verify 'Login to your account' is visible
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginToYourAccountVisible(), 
            "'Login to your account' is not visible");
        
        // 6. Enter correct email address and password
        String email = ConfigReader.getProperty("validEmail");
        String password = ConfigReader.getProperty("validPassword");
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        
        // 7. Click 'login' button
        loginPage.clickLogin();
        
        // 8. Verify that 'Logged in as username' is visible
        Assert.assertTrue(homePage.isLoggedInAsUsernameVisible(), 
            "'Logged in as username' is not visible");
        
        // 9. Click 'Delete Account' button
        homePage.clickDeleteAccount();
        
        // 10. Verify that 'ACCOUNT DELETED!' is visible
        AccountPage accountPage = new AccountPage(driver);
        Assert.assertTrue(accountPage.isAccountDeletedVisible(), 
            "'ACCOUNT DELETED!' is not visible");
    }
    
    @Test
    public void testLoginWithIncorrectCredentials() {
        // 3. Verify that home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
        
        // 4. Click on 'Signup / Login' button
        homePage.clickSignupLogin();
        
        // 5. Verify 'Login to your account' is visible
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginToYourAccountVisible(), 
            "'Login to your account' is not visible");
        
        // 6. Enter incorrect email address and password
        String email = ConfigReader.getProperty("invalidEmail");
        String password = ConfigReader.getProperty("invalidPassword");
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        
        // 7. Click 'login' button
        loginPage.clickLogin();
        
        // 8. Verify error 'Your email or password is incorrect!' is visible
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Your email or password is incorrect!", 
            "Error message is not correct");
    }
}