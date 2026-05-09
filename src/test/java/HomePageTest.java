import com.microsoft.playwright.Page;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.playwrightfactory.PlaywrightFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest {
    Page page;
    HomePage homePage;
    PlaywrightFactory playwrightFactory;

    @BeforeClass
    public void setup() {
        playwrightFactory = new PlaywrightFactory();
        page = playwrightFactory.initBrowser("chromium");
        homePage = new HomePage(page);
    }

    @BeforeMethod
    public void navigateToHomePage() {
        page.navigate("https://naveenautomationlabs.com/opencart/");
    }

    @Test
    public void homePageTitleTest() {
        String title = page.title();
        Assert.assertEquals(title, "Your Store");
    }

    @Test
    public void homePageUrlTest() {
        String url = page.url();
        Assert.assertEquals(url, "https://naveenautomationlabs.com/opencart/");
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
