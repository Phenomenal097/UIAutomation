package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;

public class HomeTest extends BaseTest {

    @BeforeMethod
    public void navigateToHomePage() {
        page.navigate(properties.getProperty("url"));
    }

    @Test
    public void homePageTitleTest() {
        String title = page.title();
        Assert.assertEquals(title, "Your Store");
    }

    @Test
    public void homePageUrlTest() {
        String url = page.url();
        Assert.assertEquals(url, properties.getProperty("url"));
    }

    @DataProvider
    public Object[][] searchDataProvider() {
        return new Object[][]{
                {"Macbook"},
                {"Iphone"},
                {"Ipad"}
        };
    }

    @Test(dataProvider = "searchDataProvider")
    public void validateSearchTest(String searchText) {
        homePage.sendSearchText(searchText);
        homePage.clickSearchButton();
        Assert.assertEquals(homePage.getSearchResultsHeaderText(), "Search - " + searchText);
    }

    @Test
    public void validateEmptyAddToCartTest() {
        homePage.clickAddToCartBtn();
        String cartEmptyText = homePage.getEmptyCartTextMsg();
        Assert.assertEquals(cartEmptyText, "Your shopping cart is empty!");
    }
}
