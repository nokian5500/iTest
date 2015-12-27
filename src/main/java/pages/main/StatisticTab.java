package pages.main;

import common.ApplicationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class StatisticTab extends BasePage {

    // Variables

    @FindBy(xpath = "//*[@class='table table-striped ng-scope']//th[2]/center[text()='Кількість наданих послуг']")
    public WebElement numberOfProvidedServicesColumn;

    @FindBy(xpath = "//*[@class='table table-striped ng-scope']//th[3]/center[contains(.,'Таймінг')]")
    public WebElement timingColumn;

    @FindBy(xpath = "//*[@class='table table-striped ng-scope']//tr/td[3]")
    public WebElement timingRow;

    @FindBy(xpath = "//*[@class='table table-striped ng-scope']//tr/td[1]")
    public WebElement regionRow;


    // Methods

    public StatisticTab() {
        PageFactory.initElements(driver, this);
    }
}