package com.automationexercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    private WebDriver driver;

    @FindBy(css = "div.signup-form h2")
    private WebElement newUserSignupText;

    @FindBy(css = "input[data-qa='signup-name']")
    private WebElement nameInput;

    @FindBy(css = "input[data-qa='signup-email']")
    private WebElement emailInput;

    @FindBy(css = "button[data-qa='signup-button']")
    private WebElement signupButton;

    @FindBy(css = "form[action='/signup'] p")
    private WebElement errorMessage;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isNewUserSignupVisible() {
        return newUserSignupText.isDisplayed();
    }

    public void enterName(String name) {
        nameInput.sendKeys(name);
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void clickSignup() {
        signupButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}