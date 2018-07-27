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

        openURLdashboard(getRegionUrl());

        login(LoginAuthor, " ");
        navigateToggleMenu();
        createDocumentOrTask("Службова записка");
        clickButton("Далi");
        pause(5000);

        setDocTitle("Тест подписантов");
        setDocContent("Тест служебной записки , подписанты");
        addViewer("");

    }
}
