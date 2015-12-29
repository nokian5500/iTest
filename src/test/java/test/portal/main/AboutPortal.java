package test.portal.main;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import test.TestBase;

public class AboutPortal extends TestBase {

    @Test (priority = 10)
    public void textOnAboutPortalPage() {
        app.navHelper.openAboutPortalPage();
        assertEquals(app.aboutPortalPage.infoBlockOne.getText(), "На цьому порталі зібрано послуги, які державні органи України надають громадянам та бізнесу. Частина послуг надається через Інтернет (такі послуги позначені іконкою), інша частина послуг все ще очікує на своє впровадження в електронній формі.");
        assertEquals(app.aboutPortalPage.infoBlockTwo.getText(), "Цей портал зроблено волонтерською командою iGov в рамках боротьби з корупцією в Україні та вдосконалення бізнес-процесів в наших державних органах.");
        assertEquals(app.aboutPortalPage.infoBlockThree.getText(), "Велика подяка ІТ-волонтерам, які взяли та беруть участь у розробці Порталу:");
        assertEquals(app.aboutPortalPage.infoBlockFour.getText(), "Якщо ви маєте досвід у розробці ІТ-продуктів та бажаєте приєднатися до команди ІТ-волонтерів, запрошуємо Вас заповнити цю форму.");
    }
}