package com.urbanladder.base;

import com.urbanladder.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected WebDriver driver;

    @Parameters({"browser"})
    @BeforeClass
    public void setUp(String browser) {
        logger.info("Starting test execution with browser: " + browser);
        WebDriverManager.setDriver(browser);
        driver = WebDriverManager.getDriver();
        
        // Set timeouts
        driver.manage().timeouts().implicitlyWait(
            java.time.Duration.ofSeconds(ConfigReader.getIntProperty("implicit.wait"))
        );
        driver.manage().timeouts().pageLoadTimeout(
            java.time.Duration.ofSeconds(ConfigReader.getIntProperty("page.load.timeout"))
        );
        
        driver.get(ConfigReader.getProperty("base.url"));
        logger.info("Navigated to Urban Ladder website");
    }

    @AfterClass
    public void tearDown() {
        logger.info("Completing test execution");
        WebDriverManager.quitDriver();
    }
} 