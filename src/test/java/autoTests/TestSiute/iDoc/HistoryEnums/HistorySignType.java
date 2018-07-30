package autoTests.TestSiute.iDoc.HistoryEnums;

public enum HistorySignType {
    AGREE_DOC("затверджено"),
    ACCEPT_DOC("узгоджено"),
    SEEN_DOC("ознайомлено"),
    CREATE_DOC("створено"),
    EXECUTE_DOC("виконано"),
    DIRECT_DOC("дозволено"),
    EDIT_DOC("відредаговано"),
    WATCH_DOC("переглянуто"),
    CONSIDERATION_DOC("розглянуто"),
    FIRST_ACCEPT_DOC("попередньо погоджено"),
    SECOND_AGREE_DOC("погоджено"),
    SIGN_DOC("підписано"),
    CONSIDEATION_ADD_DOC("розглянуто адресатом"),
    VISA_DOC("завізовано"),
    CLOSE_DOC("закрито");
    private String sSign;

    private HistorySignType(String sSign) {
        this.sSign = sSign;
    }

    public String getsSign() {
        return sSign;
    }
}
