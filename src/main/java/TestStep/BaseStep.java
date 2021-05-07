package TestStep;
import Base.TestRunner;
import Object.*;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseStep  {

    public static HomeObject objHome = new HomeObject(TestRunner.driver);

    public void implicitWait(Integer time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
        }
    }

    public void bannerCheck (){
        try {
            if (objHome.banner.isDisplayed()){
                objHome.bannerOk.click();
            }
        }catch (Exception e){

        }
    }
}
