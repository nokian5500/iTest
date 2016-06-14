package autoTests.pages.main;

import autoTests.ConfigurationVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Slame on 24.02.16.
 */
public class ModalDialog {
    WebDriver driver;
    ConfigurationVariables configVariables = ConfigurationVariables.getInstance();
    // WebElements

    @FindBy(xpath = "//div[@class='modal-header bg-success']")
    public WebElement titlePopUpMessages;

    @FindBy(xpath = "//div[@class='modal-body ng-scope']")
    public WebElement bodyPopUpMessages;

    public ModalDialog(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
