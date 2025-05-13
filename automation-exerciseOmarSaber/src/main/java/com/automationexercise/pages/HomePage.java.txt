package com.automationexercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    @FindBy(css = "a[href='/login']")
    private WebElement signupLoginButton;

    @FindBy(css = "li a[href='/logout']")
    private WebElement logoutButton;

    @FindBy(css = "li a[href='/delete_account']")
    private WebElement deleteAccountButton;

    @FindBy(css = "li:nth-child(10) a")
    private WebElement loggedInAsUsername;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSignupLogin() {
        signupLoginButton.click();
    }

    public void clickLogout() {
        logoutButton.click();
    }

    public void clickDeleteAccount() {
        deleteAccountButton.click();
    }

    public boolean isHomePageVisible() {
        return driver.getTitle().equals("Automation Exercise");
    }

    public boolean isLoggedInAsUsernameVisible() {
        return loggedInAsUsername.isDisplayed();
    }
}