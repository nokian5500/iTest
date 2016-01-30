package pages.main;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class ModalDialog extends BasePage {

    // WebElements

    @FindBy(xpath = "//div[@class='modal-header bg-success']")
    public WebElement titlePopUpMessages;

    @FindBy(xpath = "//div[@class='modal-body ng-scope']")
    public WebElement bodyPopUpMessages;

    public ModalDialog() {
        PageFactory.initElements(driver, this);
    }
}
