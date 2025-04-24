package com.urbanladder.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(ProductPage.class);

    @FindBy(css = "h1.search-title")
    private WebElement searchHeader;

    @FindBy(css = "button[data-id='add-to-wishlist']")
    private WebElement wishlistButton;

    @FindBy(css = "button.add-compare")
    private WebElement compareButton;

    @FindBy(css = "a.compare-link")
    private WebElement compareLink;

    @FindBy(css = "div.product-specs")
    private WebElement productSpecs;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getSearchHeaderText() {
        return wait.until(ExpectedConditions.visibilityOf(searchHeader)).getText();
    }

    public void addToWishlist() {
        wait.until(ExpectedConditions.elementToBeClickable(wishlistButton)).click();
        logger.info("Added product to wishlist");
    }

    public void addToCompare() {
        wait.until(ExpectedConditions.elementToBeClickable(compareButton)).click();
        logger.info("Added product to compare");
    }

    public void navigateToComparePage() {
        wait.until(ExpectedConditions.elementToBeClickable(compareLink)).click();
        logger.info("Navigated to compare page");
    }

    public Map<String, String> getProductSpecifications() {
        Map<String, String> specs = new HashMap<>();
        List<WebElement> specRows = productSpecs.findElements(By.cssSelector("tr"));
        
        for (WebElement row : specRows) {
            String key = row.findElement(By.cssSelector("td:first-child")).getText();
            String value = row.findElement(By.cssSelector("td:last-child")).getText();
            specs.put(key, value);
        }
        
        logger.info("Retrieved product specifications");
        return specs;
    }

    public void applyFilters(Map<String, String> filters) {
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            WebElement filterElement = driver.findElement(
                By.xpath(String.format("//div[contains(@class,'filter')]//label[contains(text(),'%s')]", filter.getValue()))
            );
            wait.until(ExpectedConditions.elementToBeClickable(filterElement)).click();
            logger.info("Applied filter: " + filter.getValue());
        }
    }

    public String getIncompatibleCompareMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("div.compare-error"))).getText();
    }
} 