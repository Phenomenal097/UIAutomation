package com.qa.opencart.playwrightfactory;

import com.microsoft.playwright.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory {
    Properties properties;

    public static ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<BrowserContext> browserContextThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    //custom getter methods for thread local
    public static Playwright getPlaywright() {
        return playwrightThreadLocal.get();
    }

    public static Browser getBrowser() {
        return browserThreadLocal.get();
    }

    public static BrowserContext getBrowserContext() {
        return browserContextThreadLocal.get();
    }

    public static Page getPage() {
        return pageThreadLocal.get();
    }

    public Page initBrowser(Properties properties) {
        String browserName = properties.getProperty("browser");
        boolean isHeadless = Boolean.parseBoolean(properties.getProperty("headless", "true"));
        System.out.println("Initializing Playwright Browser " + browserName);
        playwrightThreadLocal.set(Playwright.create());

        switch(browserName.toLowerCase()) {

            case "chromium":
                browserThreadLocal.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless)));
                break;

            case "firefox":
                browserThreadLocal.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless)));
                break;

            case "safari":
                browserThreadLocal.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless)));
                break;

            case "chrome":
                browserThreadLocal.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(isHeadless)));
                break;

            default:
                throw new IllegalArgumentException("Invalid browser name: " + browserName);
        }

        browserContextThreadLocal.set(getBrowser().newContext());
        pageThreadLocal.set(getBrowserContext().newPage());
        return getPage();
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
