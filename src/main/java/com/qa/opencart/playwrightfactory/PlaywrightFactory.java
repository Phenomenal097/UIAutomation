package com.qa.opencart.playwrightfactory;

import com.microsoft.playwright.*;

import java.lang.classfile.instruction.SwitchCase;

public class PlaywrightFactory {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    public Page initBrowser(String browserName) {
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
}
