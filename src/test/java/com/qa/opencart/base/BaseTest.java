package com.qa.opencart.base;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.playwrightfactory.PlaywrightFactory;
import org.testng.annotations.*;

import java.nio.file.Paths;
import java.util.Properties;

public class BaseTest {
    protected PlaywrightFactory playwrightFactory;
    protected Page page;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected Properties properties;

    @Parameters("browser")
    @BeforeClass
    public void setup(String browserName) {
        playwrightFactory = new PlaywrightFactory();
        properties = playwrightFactory.setConfig();
        if(browserName != null) {
            properties.setProperty("browser", browserName);
        }
        page = playwrightFactory.initBrowser(properties);
        homePage = new HomePage(page);

        BrowserContext context = page.context();
        context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        BrowserContext context = page.context();
        context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("Trace.zip")));
    }

    @AfterSuite
    public void tearDown() {
        page.context().browser().close();
    }
}
