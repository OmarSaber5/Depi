package com.automationexercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
    private WebDriver driver;

    @FindBy(css = "h2[data-qa='account-created'] b")
    private WebElement accountCreatedText;

    @FindBy(css = "a[data-qa='continue-button']")
    private WebElement continueButton;

    @FindBy(css = "h2[data-qa='account-deleted'] b")
    private WebElement accountDeletedText;

    @FindBy(css = "h2.title")
    private WebElement enterAccountInformationText;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isAccountCreatedVisible() {
        return accountCreatedText.isDisplayed();
    }

    public boolean isAccountDeletedVisible() {
        return accountDeletedText.isDisplayed();
    }

    public boolean isEnterAccountInformationVisible() {
        return enterAccountInformationText.isDisplayed();
    }

    public void clickContinue() {
        continueButton.click();
    }
}