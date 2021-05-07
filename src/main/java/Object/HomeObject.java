package Object;
import Object.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomeObject extends AbstractObject {
    public HomeObject(AndroidDriver<AndroidElement>driver){
        super(driver);
    }

    @AndroidFindBy (xpath = "//*[@content-desc='bt_region_id_1']")
    public AndroidElement jabodetabek;

    @AndroidFindBy (xpath = "//*[@text='Konfirmasi']")
    public AndroidElement confirmArea;

    @AndroidFindBy (xpath = "//*[@text='Pilih Area Pengiriman']")
    public AndroidElement chooseAreaPopUp;

    @AndroidFindBy (xpath = "//*[@content-desc='img_popup']")
    public AndroidElement banner;

    @AndroidFindBy (xpath = "//*[@content-desc='bt_container_touch']")
    public AndroidElement bannerOk;

    @AndroidFindBy (xpath = "//*[@content-desc='btnSearch']")
    public AndroidElement searchBar;

    @AndroidFindBy (xpath = "//*[@content-desc='tf_search_key']")
    public AndroidElement searchField;




}
