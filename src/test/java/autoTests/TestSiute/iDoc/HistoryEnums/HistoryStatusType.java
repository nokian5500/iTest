package autoTests.TestSiute.iDoc.HistoryEnums;

public enum HistoryStatusType {
    AGREE("затвердження"),
    ACCEPT("узгодження"),
    SEEN("ознайомлення"),
    CREATE("створення"),
    EXECUTE("виконання"),
    DIRECT("адресацiя"),
    EDIT("редагування"),
    WATCH("перегляд"),
    CONSIDERATION("розгляд"),
    FIRST_ACCEPT("попереднє погодження"),
    SECOND_AGREE("погодження"),
    SIGN("підписання"),
    CONSIDEATION_ADD("розгляд адресатом"),
    VISA("візування");

    private String sStatus;

    HistoryStatusType(String sStatus) {
        this.sStatus = sStatus;
    }
    public String getsStatus(){
        return sStatus;
    }

}
