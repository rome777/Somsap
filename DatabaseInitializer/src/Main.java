public class Main {
	public static void main(String[] args) {
		ManageDatabase md = ManageDatabase.getInstance();
		System.out.println("DROP TRIGGER Records_Seq_trigger: " +
				md.excuteStatement("DROP TRIGGER RECORDS_SEQ_TRIGGER"));
		System.out.println("DROP SEQUENCE Records_Seq: " +
				md.excuteStatement("DROP SEQUENCE RECORDS_SEQ"));
		System.out.println("DROP TABLE Records: " + md.deleteTable("Records"));
		System.out.println("DROP TABLE Sessions: " + md.deleteTable("Sessions"));
		System.out.println("DROP TABLE Users: " + md.deleteTable("Users"));
		System.out.println("DROP TABLE User_Types: " + md.deleteTable("User_Types"));
		System.out.println("DROP TABLE Comments: " + md.deleteTable("Comments"));
		System.out.println("DROP TABLE Board_Posts: " + md.deleteTable("Board_Posts"));
		System.out.println("DROP TABLE Board_Codes: " + md.deleteTable("Board_Codes"));
		System.out.println();
		System.out.println("CREATE TABLE UserTypes: " + md.createUserTypeTable());
		System.out.println("CREATE TABLE Users: " + md.createUsersTable());
		System.out.println("CREATE TABLE Sessions: " + md.createSessionsTable());
		System.out.println("CREATE TABLE Records: " + md.createRecordsTable());
		System.out.println("CREATE SEQUENCE Records_Seq: " + md.excuteStatement(
			"CREATE SEQUENCE Records_Seq " +
					"START WITH 1 " +
					"INCREMENT BY 1 "
		));
		System.out.println("CREATE OR REPLACE TRIGGER Records_Seq_trigger: " + md.excuteStatement(
			"CREATE OR REPLACE TRIGGER Records_Seq_trigger " +
					"BEFORE INSERT ON Records REFERENCING NEW AS NEW " +
					"FOR EACH ROW " +
					"BEGIN " +
						"SELECT Records_Seq.nextval INTO :NEW.Seq FROM dual; " +
					"END"
		));
		System.out.println("CREATE TABLE BoardCodes: " + md.createBoardCodesTable());
		System.out.println("CREATE TABLE BoardPosts: " + md.createBoardPostsTable());
		System.out.println("CREATE TABLE Comments: " + md.createCommentsTable());
		
		//UserType Insert
		System.out.println(
				"INSERT INTO User_Types (Type, Name, Is_Using, Created_Datetime, " +
						"Created_User_Email, Modified_Datetime, Modified_User_Email) " +
						"VALUES ('admin', '관리자', 1, SYSTIMESTAMP, 'somsap', SYSTIMESTAMP, 'somsap'): " +
			md.excuteStatement("INSERT INTO User_Types (Type, Name, Is_Using, Created_Datetime, " +
					"Created_User_Email, Modified_Datetime, Modified_User_Email) " +
					"VALUES ('admin', '관리자', 1, SYSTIMESTAMP, 'somsap', SYSTIMESTAMP, 'somsap')"));
	}
}
