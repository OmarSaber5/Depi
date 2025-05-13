package com.automationexercise.tests;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.SignupPage;
import com.automationexercise.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterWithExistingEmailTest extends BaseTest {
    @Test
    public void testRegisterWithExistingEmail() {
        // 3. Verify that home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
        
        // 4. Click on 'Signup / Login' button
        homePage.clickSignupLogin();
        
        // 5. Verify 'New User Signup!' is visible
        SignupPage signupPage = new SignupPage(driver);
        Assert.assertTrue(signupPage.isNewUserSignupVisible(), "'New User Signup!' is not visible");
        
        // 6. Enter name and already registered email address
        String name = "Existing User";
        String email = ConfigReader.getProperty("existingEmail");
        signupPage.enterName(name);
        signupPage.enterEmail(email);
        
        // 7. Click 'Signup' button
        signupPage.clickSignup();
        
        // 8. Verify error 'Email Address already exist!' is visible
        String errorMessage = signupPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Email Address already exist!", 
            "Error message is not correct");
    }
}