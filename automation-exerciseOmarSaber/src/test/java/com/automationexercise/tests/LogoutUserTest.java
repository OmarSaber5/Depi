package com.automationexercise.tests;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutUserTest extends BaseTest {
    @Test
    public void testLogoutUser() {
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
        
        // 9. Click 'Logout' button
        homePage.clickLogout();
        
        // 10. Verify that user is navigated to login page
        Assert.assertTrue(loginPage.isLoginToYourAccountVisible(), 
            "User is not navigated to login page after logout");
    }
}