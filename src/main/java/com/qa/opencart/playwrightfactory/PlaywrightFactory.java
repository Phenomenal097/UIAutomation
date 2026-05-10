package com.qa.opencart.playwrightfactory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties properties;

    public Page initBrowser(Properties properties) {
        String browserName = properties.getProperty("browser");
        System.out.println("Initializing Playwright Browser " + browserName);
        playwright = Playwright.create();

        switch(browserName.toLowerCase()) {

            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;

            case "firefox":
                browser =  playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;

            case "safari":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;

            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                break;

            default:
                System.out.println("Invalid Browser Name " + browserName);
                break;
        }

        browserContext = browser.newContext();
        page = browserContext.newPage();
        return page;
    }

    public Properties setConfig() {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config/config.properties");
            properties = new Properties();
            properties.load(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
