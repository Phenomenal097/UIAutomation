import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class PlaywrightBasicsTest {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChromiumSandbox(false));
        BrowserContext browserContext = browser.newContext();

        //Adding trace through context
        browserContext.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));

        Page page = browserContext.newPage();
        page.navigate("https://google.com");
        String url = page.url();
        String title = page.title();
        System.out.println("The url is " + url);
        System.out.println("The title is " + title);

        //Stop tracing through context
        browserContext.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")));

        browser.close();
        playwright.close();
    }
}
