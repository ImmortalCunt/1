package com.urbanladder.tests;

import com.urbanladder.base.BaseTest;
import com.urbanladder.pages.HomePage;
import com.urbanladder.pages.ProductPage;
import com.urbanladder.utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class UrbanLadderTest extends BaseTest {
    
    @Test(priority = 1)
    public void testUserRegistration() {
        HomePage homePage = new HomePage(driver);
        homePage.clickProfileIcon();
        homePage.clickSignUp();
        // Note: Registration implementation will depend on the actual registration form
        // which may change. You'll need to implement the actual registration steps.
        logger.info("Completed user registration test");
    }

    @Test(priority = 2)
    public void testUserLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.clickProfileIcon();
        homePage.clickLogin();
        // Note: Login implementation will depend on the actual login form
        // You'll need to implement the actual login steps
        homePage.navigateToProfile();
        // Verify profile details
        logger.info("Completed user login test");
    }

    @Test(priority = 3)
    public void testAddToWishlist() {
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);

        homePage.searchProduct("Recliner Chair");
        String headerText = productPage.getSearchHeaderText();
        Assert.assertTrue(headerText.contains("Recliner Chair"), "Search results header validation failed");

        Map<String, String> filters = new HashMap<>();
        filters.put("price", "20000-30000");
        filters.put("brand", "Urban Ladder");
        productPage.applyFilters(filters);

        // Add 3 items to wishlist
        // Note: Implementation will depend on actual website structure
        for (int i = 0; i < 3; i++) {
            productPage.addToWishlist();
        }
        logger.info("Completed add to wishlist test");
    }

    @Test(priority = 4)
    public void testOrderItem() {
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);

        // Navigate to Crockery units
        // Note: Implementation will depend on actual navigation structure

        Map<String, String> filters = new HashMap<>();
        filters.put("price", "10000-20000");
        filters.put("brand", "Urban Ladder");
        filters.put("material", "Wood");
        productPage.applyFilters(filters);

        // Add items to cart and proceed to checkout
        // Note: Implementation will depend on actual checkout process
        logger.info("Completed order item test");
    }

    @Test(priority = 5)
    public void testCompareItems() {
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);

        homePage.searchProduct("beds");
        String headerText = productPage.getSearchHeaderText();
        Assert.assertTrue(headerText.contains("Beds"), "Search results header validation failed");

        // Add two products to compare
        productPage.addToCompare();
        // Navigate to second product and add to compare
        productPage.addToCompare();

        productPage.navigateToComparePage();
        
        // Get specifications and write to Excel
        List<Map<String, String>> productsToCompare = new ArrayList<>();
        productsToCompare.add(productPage.getProductSpecifications());
        // Get specifications for second product
        productsToCompare.add(productPage.getProductSpecifications());

        ExcelUtils.writeComparisonToExcel("product_comparison.xlsx", productsToCompare);
        logger.info("Completed compare items test");
    }

    @Test(priority = 6)
    public void testIncompatibleComparison() {
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);

        homePage.searchProduct("Recliners");
        productPage.addToCompare();

        homePage.searchProduct("Mattresses");
        productPage.addToCompare();

        String errorMessage = productPage.getIncompatibleCompareMessage();
        Assert.assertEquals(errorMessage, "Can't compare recliners with mattresses",
            "Incompatible comparison message validation failed");
        logger.info("Completed incompatible comparison test");
    }

    @Test(priority = 7)
    public void testHelpDesk() {
        HomePage homePage = new HomePage(driver);
        String helpDeskMessage = homePage.getHelpDeskMessage();
        logger.info("Help desk message: " + helpDeskMessage);
        homePage.dismissHelpDeskAlert();
        logger.info("Completed help desk test");
    }

    @Test(priority = 8)
    public void testLogout() {
        HomePage homePage = new HomePage(driver);
        homePage.logout();
        // Verify logout by checking profile dropdown links
        logger.info("Completed logout test");
    }
} 