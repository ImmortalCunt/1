package com.urbanladder.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(HomePage.class);

    @FindBy(css = "span.header-icon-link.user-profile-icon")
    private WebElement profileIcon;

    @FindBy(css = "a[data-gaaction='signup']")
    private WebElement signUpLink;

    @FindBy(id = "search")
    private WebElement searchBox;

    @FindBy(css = "button#search_button")
    private WebElement searchButton;

    @FindBy(css = "a.login-link")
    private WebElement loginLink;

    @FindBy(css = "a[href='/profile']")
    private WebElement profileLink;

    @FindBy(css = "a.logout")
    private WebElement logoutLink;

    @FindBy(css = "div.help-center")
    private WebElement helpDeskIcon;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickProfileIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(profileIcon)).click();
        logger.info("Clicked on profile icon");
    }

    public void clickSignUp() {
        wait.until(ExpectedConditions.elementToBeClickable(signUpLink)).click();
        logger.info("Clicked on sign up link");
    }

    public void searchProduct(String productName) {
        wait.until(ExpectedConditions.elementToBeClickable(searchBox)).sendKeys(productName);
        searchButton.click();
        logger.info("Searched for product: " + productName);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        logger.info("Clicked on login link");
    }

    public void navigateToProfile() {
        clickProfileIcon();
        wait.until(ExpectedConditions.elementToBeClickable(profileLink)).click();
        logger.info("Navigated to profile page");
    }

    public void logout() {
        clickProfileIcon();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
        logger.info("Clicked on logout link");
    }

    public String getHelpDeskMessage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", helpDeskIcon);
        wait.until(ExpectedConditions.elementToBeClickable(helpDeskIcon)).click();
        String message = driver.switchTo().alert().getText();
        logger.info("Retrieved help desk message: " + message);
        return message;
    }

    public void dismissHelpDeskAlert() {
        driver.switchTo().alert().dismiss();
        logger.info("Dismissed help desk alert");
    }
} 