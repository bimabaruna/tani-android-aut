package TestStep;

import Base.TestRunner;
import io.appium.java_client.android.AndroidKeyCode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class HomePage extends BaseStep {

    @Given("User choose Area from pop up")
    public void userChooseAreaFromPopUp() {
        implicitWait(10);
        try {
            if (objHome.chooseAreaPopUp.isDisplayed()){
                objHome.jabodetabek.click();
                objHome.confirmArea.click();
            }

        }catch (Exception e){

        }

    }

    @And("User search product {string}")
    public void userSearchProduct(String value) {
        bannerCheck();
        objHome.searchBar.click();
        objHome.searchField.sendKeys(value);
        TestRunner.driver.pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);
        implicitWait(10);

    }
}
