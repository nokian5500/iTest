package autoTests.TestSiute;

public class HistoryEventType {
    public static final String CREATE_DOCUMENT = "Створення документу [sID_OrderURL]";
    public static final String EDIT_DOCUMENT = "[sName]([sNameReferent]) - документ відредаговано";
    public static final String STATUS_DOCUMENT = "Статус документа змінено - узгодження документу";
    public static final String ADD_COMMENT = "[sName]([sNameReferent]) Додано коментар: [sCommentary]";
    public static final String EDIT_COMMENT = "[sName]([sNameReferent]) Відредаговано коментар: [sCommentary]";
    public static final String DELETE_COMMENT = "[sName]([sNameReferent]) Видалено коментар: [sCommentary]";
    public static final String ADD_HUMAN = "[sName]([sNameReferent]). До документу [sID_OrderURL] запрошено [sNameHuman] у ролі: [sRole].";
    public static final String DOCUMENT_APPROVED = "[sName]([sNameReferent]) - документ узгоджено.";
    public static final String DELETE_HUMAN = "Учасника документа [sNameHuman] було видалено";
    public static final String FIRST_SEEN = "[sName]([sNameReferent]) - документ переглянутий вперше";
    public static final String STATUS_CANCEL ="[sName]([sNameReferent]) Відмова по документу № [sID_OrderURL].";
    public static final String ADD_REMARK = "[sName]([sNameReferent]) Винесено зауваження: [sComment]";
    public static final String EDIT_REMARK = "[sName]([sNameReferent]) Відредаговано зауваження: [sComment]";
    public static final String DELETE_REMARK = "[sName]([sNameReferent]) Видалено зауваження: [sComment]";
}
