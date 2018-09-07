package autoTests.TestSiute.iDoc;

import static com.codeborne.selenide.Selenide.open;
import autoTests.CustomMethods;
import org.junit.Test;

public class Test_doc_btsol_vertical_sz__part_3_2_signer extends CustomMethods {
    @Test
    public void Test_part_3_2_signer() throws Exception {
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

        //logins beta for wiev and delete
        String LoginCollective1 = "IGOV_160582SOD";
        String NameCollective1 = "Смоктій Оксана Данилівна";
        String LoginCollective2 = "IGOV_310780BVV";
        String NameCollective2 = "Белявцев Володимир Володимирович";
        String LoginCollective3 = "IGOV_301082BOY";
        String NameCollective3 = "Бондарь Ольга Євгенієвна";
        String LoginCollective4 = "IGOV_100982SOV";
        String NameCollective4 = "Смірнова Олена Володимирівна";
        String LoginCollective5 = "IGOV_210961SMU";
        String NameCollective5 = "Соколова Марина Юріївна";
        String LoginCollective6 = "IGOV_311288BUD";
        String NameCollective6 = "Біла Юлія Данилівна";
        String LoginCollective7 = "IGOV_180277SMV";
        String NameCollective7 = "Свідрань Максим Володимирович";

        openURLdashboard(getRegionUrl());

        //авторизация Автора
        login(LoginAuthor, " ");
        navigateToggleMenu();
        createDocumentOrTask("Службова записка");
        clickButton("Далi");
        pause(5000);

        setDocTitle("Тест подписантов");
        setDocContent("Тест служебной записки , подписанты");

        clickButtonCreate();
       // checkScrollForEmptyFields();
        //добавить подписантов (в таблицу)
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "0", NameCollective1);
        addRegionsTableRow("sTableAccept");
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "1", NameCollective2);
        addRegionsTableRow("sTableAccept");
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "2", Name1);

        setApprover(sBP, "sTableAgree", "sName_Approver", "0", Name2);

        setDirect(sBP, "sTableDirect", "sName_Direct", "0", Name3);

        getOrderFromUrlCurrentPage();
        clickButtonCreate();
        pause(4000);
        logout();

        //заходим под подписантом Name1
        login(Login1, " ");
        setRegionFindOrderByNumberDocument();

        //добавить на просмотр
        clickButton("Інші дії");
        addViewer(NameCollective4);
        addFIO(NameCollective5);
        pause(2000);
        closeParticipant();
        //добавить на ознакомление
        clickButton("Інші дії");
        addVisor(Name4);
        pause(2000);
        //addFIO(Name5);
        closeParticipant();
        //добавить на подпись
        clickButton("Інші дії");
        addAcceptor(Name6);
        //addFIO(Name7);
        closeParticipant();
        //Делегирование себе
//        clickButton("Інші дії");
//        clickButton("Делегувати");
//        addDelegate(Name1);


        //Делегирование
//        clickButton("Інші дії");
//        clickButton("Делегувати");
//        addDelegate(Name5);
//        pause(3000);
        //Удалить пописантов с кнопок

//        removeParticipant(1, true);
//        removeParticipant(1, true);
//        removeParticipant(1, true);
//        removeParticipant(1, true);
//        removeParticipant(1, true);
        logout();

        //авторизация подписанта с кнопки Name6
        login(Login6, " ");
        setRegionFindOrderByNumberDocument();
        pause(3000);
        addComment("Тест замечания");
        closeDoc();
        setRegionFindOrderByNumberDocument();
        pause(3000);
        editComment("Тест редактирования замечания");
        pause(1000);
        deleteComment();
        pause(1000);
        addComment("Тест замечания 2");
        logout();





        //авторизация Автора
        login(LoginAuthor, " ");
        setRegionFindOrderByNumberDocument();
        clickButton("Редагувати");
        pause(5000);
//        removeRowFromTable("sTableAccept", 0, true);
//        removeRowFromTable("sTableAccept", 1, true);
//        removeRowFromTable("sTableAccept", 2, true);




    }

}
