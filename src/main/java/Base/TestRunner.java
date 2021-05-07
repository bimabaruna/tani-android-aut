package Base;

import com.google.common.io.Files;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;



@CucumberOptions(

        glue = "TestStep",
        features = "src/test/java/",
        plugin = {
                "pretty",
                "json:target/cucumber.json",
        },
        tags = {"@search"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

    public String reportDirectory = "reports";
    public String reportFormat = "xml";
    public static AndroidDriver<AndroidElement> driver ;
    DesiredCapabilities dc = new DesiredCapabilities();

    public void setUp() throws IOException, InterruptedException {

        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName","test");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.tanihub.vaesdothrak");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        dc.setCapability("platformName","Android");
        dc.setCapability("automationName", "UiAutomator2");
        dc.setCapability("autoWebView", true);
        dc.setCapability("noReset", true);
        dc.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);

        driver.setLogLevel(Level.INFO);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



    }

    @Before
    public void logInfo(Scenario scenario){
        String scenarioName = scenario.getName();
        String[] arrayScenarioName = scenarioName.split("--");
        String scenarioName1 = arrayScenarioName[0];
        String scenarioName2 = arrayScenarioName[1];
        System.out.println("Scenario Name 1 for this test is -> " + scenarioName1);
        System.out.println("Scenario Name 2 for this test is -> " + scenarioName2);
    }
    @After
    public void embedScreenshot(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                String testName = scenario.getName();
                scenario.embed(screenshot, "image/png");
                scenario.write(testName);
            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();}
        }
    }


    @BeforeSuite
    public void setUpp() throws Exception {
        setUp();
    }


    @AfterMethod(alwaysRun = true)
    public void tearDownr(ITestResult result) throws IOException {
        if (!result.isSuccess()) {
            File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String failureImageFileName = result.getMethod().getDescription()
                    + new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) + ".png";
            File failureImageFile = new File(System.getProperty("user.dir") + "//target/cucumber-html-reports/screenshots/Fail " + failureImageFileName);
            failureImageFile.getParentFile().mkdir();
            failureImageFile.createNewFile();
            Files.copy(imageFile, failureImageFile);

        }
    }


    @AfterMethod()
    public void teardown()  {
        driver.closeApp();
        driver.launchApp();
    }

    @AfterSuite(alwaysRun=true)
    public void generateHTMLReports() {
        ReportConfig.generateCucumberReport();
    }

    @AfterSuite(alwaysRun = true)
    public void quit() throws IOException, InterruptedException {
        driver.closeApp();
        driver.quit();
    }
}
