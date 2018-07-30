package autoTests.TestSiute.iDoc.HistoryEnums;

public enum HistoryFilterType {
    CreateDoc("CreateDoc"),
    EditDoc("EditDoc"),
    ChangeDocStatus("ChangeDocStatus"),
    AddComment("AddComment"),
    EditComment("EditComment"),
    DeleteComment("DeleteComment"),
    AddHuman("AddHuman"),
    ApprovedDoc("ApprovedDoc"),
    DeleteHuman("DeleteHuman"),
    FirstSeen("FirstSeen"),
    DelegateDoc("DelegateDoc"),
    SigneDoc("SigneDoc"),
    CloseDoc("CloseDoc");
    private String sFilter;

    HistoryFilterType(String sFilter) {
        this.sFilter = sFilter;
    }

    public String getsFilter() {
        return sFilter;
    }
}
