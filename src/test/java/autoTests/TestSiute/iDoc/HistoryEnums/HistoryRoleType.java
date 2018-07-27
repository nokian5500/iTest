package autoTests.TestSiute.iDoc.HistoryEnums;

public enum HistoryRoleType {
    ACCEPTOR("підписанта"),
    VIEWER("переглядача"),
    VISOR("ознайомлюючого")
    ;
    private String sRole;
    private HistoryRoleType(String sRole){
        this.sRole = sRole;
    }

    public String getsRole() {
        return sRole;
    }
}
