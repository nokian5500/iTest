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
    CancelDoc("CancelDoc"),
    AddRemark("AddRemark"),
    EditRemark("EditRemark"),
    DeleteRemark("DeleteRemark"),
    DelegateDoc("DelegateDoc"),
    SigneDoc("SigneDoc"),
    CloseDoc("CloseDoc"),
    RemoveHuman("RemoveHuman"),
    RemoveSign("RemoveSign");
    private String sFilter;

    HistoryFilterType(String sFilter) {
        this.sFilter = sFilter;
    }

    public String getsFilter() {
        return sFilter;
    }
}
