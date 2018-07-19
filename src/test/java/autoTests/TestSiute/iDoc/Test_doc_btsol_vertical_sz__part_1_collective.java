package autoTests.TestSiute.iDoc;

import autoTests.CustomMethods;

import static com.codeborne.selenide.Selenide.open;
import org.junit.Test;

public class Test_doc_btsol_vertical_sz__part_1_collective extends CustomMethods {
    @Test
    public void Test_doc_btsol_vertical_sz_collective() throws Exception {
        String sBP = "_doc_btsol_vertical_sz";
        String email = "autotestbeta@gmail.com";
        
        //logins beta
        String LoginCollective1 = "IGOV_160582SOD";
        String NameCollective1 = "Смоктій Оксана Данилівна";
        String LoginCollective2 = "IGOV_310780BVV";
        String NameCollective2 = "Белявцев Володимир Володимирович";
        String LoginCollective4 = "IGOV_100982SOV";
        String NameCollective4 = "Смірнова Олена Володимирівна";
        String LoginCollective5 = "IGOV_210961SMU";
        String NameCollective5 = "Соколова Марина Юріївна";
        String LoginCollective6 = "IGOV_311288BUD";
        String NameCollective6 = "Біла Юлія Данилівна";
        String LoginCollective7 = "IGOV_180277SMV";
        String NameCollective7 = "Свідрань Максим Володимирович";
        String LoginCollective8 = "IGOV_301082BOY";
        String NameCollective8 = "Бондарь Ольга Євгенієвна";
        
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

        setDocTitle("Автотест колективної роботи");
        setDocContent("Текст службової записки при колективній роботі");
        //loadFileToHTML("Файл ХТМЛ", "src/test/resources/files/test.jpg");
        setRegionTableCellsInputTypeString("sNumber", "0", "1");
        setRegionTableCellsInputTypeString("sNameFile", "0", "Тестовий додаток");
        setRegionTableCellsInputTypeFile(sBP,"sTableFile", "sFile", "0", "src/test/resources/files/test.jpg");
        
        
        setAcceptor(sBP, "sTableAccept", "sName_Acceptor", "0", NameCollective1);  
        setApprover(sBP, "sTableAgree", "sName_Approver", "0", NameCollective2);        
        setDirect(sBP, "sTableDirect", "sName_Direct", "0", NameCollective4);
        
        getOrderFromUrlCurrentPage();
        clickButtonCreate();
        pause(4000);
        logout();
        pause(2000);
        
        openURLdashboard(getCollectiveURL());
        
        login(LoginCollective1, " ");
        setRegionFindOrderByNumberDocument();
        checkAttachments(1);
        downloadAttach("test.jpg");
        addAcceptor(Name1);
        addVisor(Name2);
        addViewer(Name3);
        removeParticipant(1, true);
        removeParticipant(2, true);
        removeParticipant(3, true);
        
    }
}
