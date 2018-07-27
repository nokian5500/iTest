package autoTests.TestSiute.iDoc;

import static com.codeborne.selenide.Selenide.open;
import autoTests.CustomMethods;
import org.junit.Test;

public class Test_doc_btsol_vertical_sz__part_2_urgent extends CustomMethods {
    @Test
    public void Test_part_2_urgent() throws Exception {
        String sBP = "_doc_btsol_vertical_sz";
        String email = "autotestbeta@gmail.com";

        //logins beta-autotest
        String LoginAuthor = "ZCPK_310767TVV";
        String NameAuthor = "Терентьєв Володимир Володимирович";
        String Login1 = "ZCPK_020379CDP";
        String Name1 = "Чмихал Дмитро Павлович";
        String Login2 = "ZCPK_150960POV";
        String Name2 = "Пітула Олександр Володимирович";
        String Login3 = "ZCPK_050991BSO";
        String Name3 = "Будай Coломія Олексіївна";
        String Login4 = "ZCPK_280562DGI";
        String Name4 = "Долінська Галина Йосипівна";
        String Login5 = "ZCPK_220185NSV";
        String Name5 = "Норов Станіслав Валентинович";
        String Login6 = "ZCPK_230161DYR";
        String Name6 = "Давидчак Ярослав Романович";
        String Login7 = "ZCPK_031260SVM";
        String Name7 = "Стефанів Василь Михайлович";


        openURLdashboard(getRegionUrl());

        login(LoginAuthor, " ");
        navigateToggleMenu();
        createDocumentOrTask("Службова записка");
        clickButton("Далi");
        pause(5000);

        setDocTitle("Тест экстренности");
        setDocContent("Тест служебной записки , екстренность");
        setRegionTableCellsInputTypeString("sNumber", "0", "1");
        setRegionTableCellsInputTypeString("sNameFile", "0", "додаток");
        setRegionTableCellsInputTypeFile(sBP,"sTableFile", "sFile", "0", "src/test/resources/files/test.jpg");

        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "0", Name1);
        addRegionsTableRow("sTableAccept");
        setParticipant(sBP, "sTableAccept","sName_Acceptor", "1", Name2);

        setApprover(sBP, "sTableAgree", "sName_Approver", "0", Name3);

        setDirect(sBP, "sTableDirect", "sName_Direct", "0", Name4);

        getOrderFromUrlCurrentPage();
        clickButtonCreate();
        pause(3000);

        setRegionFindOrderByNumberDocument();
        clickUrgentStatusAllButton();
        pause(5000);
        logout();

        //заходим под первым подписантом
        login(Login1, " ");
        pause(5000);
        checkUrgentDoc();
        pause(2000);
        setRegionFindOrderByNumberDocument();
        addComment("Тест замечания екстренности");
        pause(5000);
        clickLink("Переглянутi ");
        isLastDoc();
        pause(2000);
        logout();

        //заходим автором
        login(LoginAuthor, " ");
        pause(5000);
        checkUrgentDoc();
        setRegionFindOrderByNumberDocument();
        clickButton("Редагувати");
        pause(5000);
        setDocTitle(" + Отредактировано!");
        clickButtonSign();
        pause(2000);
        logout();

        //Заходим 2ым исполнителем
        login(Login2, " ");
        pause(5000);
        checkUrgentDoc();
        setRegionFindOrderByNumberDocument();
        clickButton("Інші дії");
        clickButton("Делегувати");
        addDelegate(Name5);
        pause(2000);
        clickLink("Переглянутi ");
        isLastDoc();
        pause(2000);
        logout();

        //со стороны делеганта
        login(Login5, " ");
        pause(2000);
        clickLink("Нерозглянутi ");
        isLastDoc();
        pause(2000);
    }
}
