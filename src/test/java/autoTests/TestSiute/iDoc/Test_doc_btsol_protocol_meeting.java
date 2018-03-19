/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute.iDoc;

import autoTests.CustomMethods;

import static com.codeborne.selenide.Selenide.open;

import org.junit.Test;
/**
 *
 * @author User
 */
public class Test_doc_btsol_protocol_meeting  extends CustomMethods {
    @Test
    public void Test_doc_btsol_protocol_meeting() throws Exception {
        String sBP = "_doc_btsol_protocol_meeting";
        String email = "autotestbeta@gmail.com";

        openURLdashboard(getRegionUrl());
        
        //Авторизация
        AuthorizationBySetLoginPassword("IGOV_270907SVK", " ");
        clickButton("Увійти");
        navigateToggleMenu();
        createDocumentOrTask("Протокол оперативної наради");
//        templateSelect("");
        clickButton("Далi");
        pause(5000);
        
        //Создание
        getCurrentCalendar();
        
        setDocTitle("Автотест протокола");
        
        setDocContent("Автотест протокола");
        
        SetRegionFieldInputTypeDate("sDateMeeting", "19.03.2018");
        
        SetRegionFieldInputTypeLong("sAmtDecisions", "2");
        
        setSelect("_doc_btsol_protocol_meeting", "sTable_Chairperson", "Смоктій Вікторія Кирилівна");
        
        setParticipant(sBP, "sTable_listener", "sName_isExecute", "0", "Туренко Ольга Володимирівна");
        addRegionsTableRow("sTable_listener");
        setParticipant(sBP, "sTable_listener", "sName_isExecute", "1", "Смоктій Вікторія Кирилівна");
        
        setTaskContent("Запустить автотест протокола");
        
        setRegionTableCellsInputTypeString("sNumber", "0", "1");
        setRegionTableCellsInputTypeString("sNameFile", "0", "Тестовий додаток");
        setRegionTableCellsInputTypeFile(sBP,"sTableFile", "sFile", "0", "src/test/resources/files/test.jpg");
        
        /*Таблица Узгоджуючі*/
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "0", "Туренко Ольга Володимирівна");
        /*addRegionsTableRow("sTableAccept");
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "1", "Грек Одарка Олексіївна");
        addRegionsTableRow("sTableAccept");
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "2", "Столбова Анна Юріївна");*/
        
        /*Таблица Затверджуючий*/
        setApprover(sBP, "sTableAgree", "sName_Approver", "0", "Герман Август Васильович");
       
        //Добавляем на просмотр
        //clickButton("Інші дії");
        //addViewer("Літовченко Інна Вадимівна");
        //pause(2000);
        
        
        getOrderFromUrlCurrentPage();
        clickButtonCreate();
        pause(5000);
        clickButton("Ok");
        clickLink("Смоктій Вікторія Кирилівна");
        clickLink("Вийти");        

        //Подписываем (Согласование)
        
        //Первый подписант
        AuthorizationBySetLoginPassword("IGOV_200687TOV", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        pause(5000);
        clickButton("Інші дії");
        clickButton("Делегувати");
        addDelegate("Гуков Юрій Олександрович");
        pause(2000);
        clickLink("Туренко Ольга Володимирівна");
        clickLink("Вийти");
               
        //Второй подписант(С делегирования)
        AuthorizationBySetLoginPassword("IGOV_151071GUO", " ");
        clickButton("Увійти");
        setRegionFindOrderByNumberDocument();
        pause(5000);
        isExistButton("Пiдписати",true);
        isExistButton("Зберегти",true);
        isExistButton("Роздрукувати",true);
        isExistButton("Зауваження",true);
        isExistButton("Інші дії",true);
        clickButton("Інші дії");
        isExistButton("Додати на перегляд",true);
        isExistButton("Ознайомити",true);
        isExistButton("Додати підписанта",true);
        isExistButton("Делегувати",true);
        isExistButton("Відмовити",true);
        isExistButton("Підпис не потрібен",true);
        isExistButton("Додати завдання",false);
        isExistButton("Редагувати завдання",false);
        clickButtonSign();
        pause(5000);
        clickLink("Гуков Юрій Олександрович");
        clickLink("Вийти");
        
        //Подписываем (Утверждение)      
        AuthorizationBySetLoginPassword("IGOV_110771GAV", " ");
        clickButton("Увійти");
        pause(5000);
        setRegionFindOrderByNumberDocument();
        pause(5000);
        clickButtonSign();
        pause(5000);
        clickLink("Герман Август Васильович");
        clickLink("Вийти");
        
        //Ознакомление Автором
        AuthorizationBySetLoginPassword("IGOV_270907SVK", " ");
        clickButton("Увійти");
        pause(5000);
        setRegionFindOrderByNumberDocument();
        pause(5000);
        clickButton("Ознайомлений");
        pause(5000);
        clickLink("Смоктій Вікторія Кирилівна");
        clickLink("Вийти");
    }
}
        