package autoTests.TestSiute;

public class HistoryEventType {
    public static final String CREATE_DOCUMENT = "Створення документу [sID_OrderURL]";
    public static final String EDIT_DOCUMENT = "[sName]([sNameReferent]) - документ відредаговано";
    public static final String STATUS_DOCUMENT = "Статус документа змінено - [sStatus]";
    public static final String ADD_COMMENT = "[sName]([sNameReferent]) Додано коментар: [sCommentary]";
    public static final String EDIT_COMMENT = "[sName]([sNameReferent]) Відредаговано коментар: [sCommentary]";
    public static final String DELETE_COMMENT = "[sName]([sNameReferent]) Видалено коментар: [sCommentary]";
    public static final String ADD_HUMAN = "[sName]([sNameReferent]). До документу [sID_OrderURL] запрошено [sNameHuman] у ролі: [sRole].";
    public static final String DOCUMENT_APPROVED = "[sName]([sNameReferent]) - документ узгоджено.";
    public static final String DELETE_HUMAN = "Учасника документа [sNameHuman] було видалено";
    public static final String FIRST_SEEN = "[sName]([sNameReferent]) - документ переглянутий вперше";
    public static final String DELEGATE = "[sName]([sNameReferent]) делеговано права на документ № [sID_OrderURL] [sNameDelegate]";
    public static final String SIGNE_DOCUMENT = "[sName]([sNameReferent]) - документ завізовано";
    public static final String CLOSE_DOCUMENT = "Документ перміщено до архіву";
}
