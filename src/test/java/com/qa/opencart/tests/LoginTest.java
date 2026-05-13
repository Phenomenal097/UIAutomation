package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void navigateToLoginPage() {
        page.navigate(properties.getProperty("url"));
        loginPage = homePage.navigateToLoginPage();
    }

    @Test
    public void navigateToLoginPageTest() {
        Assert.assertTrue(loginPage.isForgotPassworkLinkVisible());
    }

    @Test
    public void loginTest() {
        loginPage.login(properties.getProperty("username"), properties.getProperty("password"));
        Assert.assertTrue(loginPage.isLoggedIn());
        loginPage.logout();
    }

}
