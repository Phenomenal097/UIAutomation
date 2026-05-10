package com.qa.opencart.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LoginPage {
    Page page;
    private final Locator emailLabel;
    private final Locator passwordLabel;
    private final Locator loginBtn;
    private final Locator forgotPassworkLink;
    private final Locator loginHeader;
    private final Locator logout;

    LoginPage(Page page) {
        this.page = page;
        emailLabel = page.locator("//input[@id='input-email']");
        passwordLabel = page.locator("//input[@id='input-password']");
        loginBtn = page.locator("//input[@value='Login']");
        forgotPassworkLink = page.locator("//div[@class='form-group']/child::a[text()='Forgotten Password']");
        loginHeader = page.locator("//h2[text()='Returning Customer']");
        logout = page.locator("//div[@class='list-group']/child::a[text()='Logout']");
    }

    /**
     * Login to the app
     *
     * @param email Email of the user
     * @param password Password of the user
     */
    public void login(String email, String password) {
        loginHeader.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        emailLabel.fill(email);
        passwordLabel.fill(password);
        loginBtn.click();
    }

    /**
     * Logout of the app
     */
    public void logout() {
        logout.click();
    }

    /**
     * Verify if user is logged in
     * @return True if user is logged in successfully
     */
    public boolean isLoggedIn() {
        return logout.isVisible();
    }

    /**
     * Verify if 'Forgot Password' is visible
     *
     * @return true if visible
     */
    public boolean isForgotPassworkLinkVisible() {
        return forgotPassworkLink.isVisible();
    }
}
