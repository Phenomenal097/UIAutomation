package com.qa.opencart.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class HomePage {
    Page page;
    private final Locator searchBox;
    private final Locator searchButton;
    private final Locator searchResultsHeader;
    private final Locator addToCartBtn;
    private final Locator emptyCartTextMsg;
    private final Locator addToCartBtnText;
    private final Locator featuredItemsHeader;

    public HomePage(Page page) {
        this.page = page;
        searchBox = page.locator("//input[@class='form-control input-lg']");
        searchButton = page.locator("//button[@class='btn btn-default btn-lg']");
        searchResultsHeader = page.locator("//div[@id='content']/h1");
        addToCartBtn = page.locator("//div[@id='cart']");
        emptyCartTextMsg = page.locator("//p[text()='Your shopping cart is empty!']");
        addToCartBtnText = page.locator("//span[@id='cart-total']");
        featuredItemsHeader = page.locator("//h3[text()='Featured']");
    }

    /**
     * Send search data
     *
     * @param searchText text to search in search box
     */
    public void sendSearchText(String searchText) {
        searchBox.fill(searchText);
    }

    /**
     * Click search button
     */
    public void clickSearchButton() {
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    /**
     * Get search result header text
     *
     * @return search text as header
     */
    public String getSearchResultsHeaderText() {
        return searchResultsHeader.innerText();
    }

    /**
     * Click 'Add to cart' button
     */
    public void clickAddToCartBtn() {
        addToCartBtn.click();
    }

    /**
     * Get empty cart text
     *
     * @return empty cart text
     */
    public String getEmptyCartTextMsg() {
        emptyCartTextMsg.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return emptyCartTextMsg.innerText();
    }

    /**
     * Get 'Add to cart' text
     *
     * @return 'Add to cart' btn text
     */
    public String getAddToCartBtnText() {
        return addToCartBtnText.innerText();
    }

    /**
     * Get featured items header
     *
     * @return featured items header
     */
    public String getFeaturedItemsHeader() {
        return featuredItemsHeader.innerText();
    }
}
