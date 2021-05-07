package Object;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class AbstractObject  {

    public static AndroidDriver<AndroidElement> driver;

    public AbstractObject(AndroidDriver<AndroidElement> driver) {

        AbstractObject.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }
}
