import java.sql.*;

public class ManageDatabase {
	
	private static ManageDatabase md;
	
	public static ManageDatabase getInstance() {
		//static 변수가 null 일 때는 객체를 생성
		if (md == null) {
			md = new ManageDatabase();
		}
		//static 변수 리턴
		return md;
	}
	
	//여러 메소드에서 공통으로 사용할 변수
	//데이터베이스 연결을 저장할 변수
	private Connection con;
	//Sql 실행을 위한 객체를 저장하기 위한 변수
	private PreparedStatement pstmt;
	private Statement stmt;
	//select 구문의 결과를 저장하기 위한 변수
	private ResultSet rs;
	
	//기본
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	private void connect() {
		try {
//			String dbUrl = "jdbc:oracle:thin:@211.183.2.253:1521:orcl"; //외부 접근 //8월 11일 현재 안됨.
			String dbUrl = "jdbc:oracle:thin:@192.168.0.108:1521:xe";   //내부 접근
			con = DriverManager.getConnection(dbUrl, "user01", "user01");
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.getStackTrace();
		}
	}
	
	private void close() {
		try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (pstmt != null) pstmt.close();
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}
	}
	
	public boolean excuteStatement(String query){
		boolean returnValue = false;
		try {
			connect();
			stmt = con.createStatement();
			returnValue = stmt.executeUpdate(query) ==
				(query.toUpperCase().startsWith("CREATE") || query.toUpperCase().startsWith("DROP") ? 0 : 1);
			//Create Drop은 0이면 성공, 실패하면 예외
			return returnValue;
		} catch (SQLException e) {
			System.out.print(e.getMessage());
			e.getStackTrace();
		} finally {
			close();
		}
		return returnValue;
	}
	
	public boolean deleteTable(String tableName){
		boolean returnValue = false;
		try {
			connect();
			String query =
				/*"DECLARE " +
				"I_CNT NUMBER; " +
				"BEGIN " +
					"SELECT COUNT(*) INTO I_CNT FROM ALL_TABLES WHERE TABLE_NAME = ?; " +
					"IF I_CNT > 0 THEN EXECUTE IMMEDIATE ?; END IF; " +
					//"DBMS_OUTPUT.PUT_LINE('삭제'); " +
				"END;"*/
				"DROP TABLE " + tableName.toUpperCase();
			stmt = con.createStatement();
			returnValue = stmt.executeUpdate(query) == 0;   //Create Drop은 0이면 성공, 실패하면 예외
			//TODO 지워졌는지 안지워졌는지 확인하는 방법 찾다가 보류함
			return returnValue;
		} catch (SQLException e) {
			System.out.print(e.getMessage());
			e.getStackTrace();
		} finally {
			close();
		}
		return returnValue;
	}
	
	//UserType
	public boolean createUserTypeTable(){
		boolean returnValue = false;
		try {
			connect();
			String query =
					"CREATE TABLE User_Types(" +
						"Type VARCHAR2(16) PRIMARY KEY," +
						"Name VARCHAR2(16) NOT NULL," +
						"Is_Using NUMBER(1) DEFAULT 1 NOT NULL," +
						"Created_Datetime TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL," +
						"Created_User_Email VARCHAR2(128) NOT NULL," +
						"Modified_Datetime TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL," +
						"Modified_User_Email VARCHAR2(128) NOT NULL" +
					")";
			stmt = con.createStatement();
			returnValue = stmt.executeUpdate(query) == 0;   //Create Drop은 0이면 성공, 실패하면 예외
		} catch (SQLException e) {
			System.out.print(e.getMessage());
			e.getStackTrace();
		} finally {
			close();
		}
		return returnValue;
	}
	
	//Users
	public boolean createUsersTable(){
		boolean returnValue = false;
		try {
			connect();
			String query =
				"CREATE TABLE Users(" +
					"Email VARCHAR2(128) PRIMARY KEY," +
					"Type VARCHAR2(16)," +
					"Nick_Name VARCHAR2(16) NOT NULL," +
					"Password VARCHAR2(16) NOT NULL," +
					"Is_Using NUMBER(1) DEFAULT 1 NOT NULL," +
					"Created_Datetime TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL," +
					"Created_User_Email VARCHAR2(128) NOT NULL," +
					"Modified_Datetime TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL," +
					"Modified_User_Email VARCHAR2(128) NOT NULL," +
					"CONSTRAINT FK_Users_UserTypes FOREIGN KEY(Type) " +
					"REFERENCES User_Types(Type)" +
				")";
			stmt = con.createStatement();
			returnValue = stmt.executeUpdate(query) == 0;   //Create Drop은 0이면 성공, 실패하면 예외
		} catch (SQLException e) {
			System.out.print(e.getMessage());
			e.getStackTrace();
		} finally {
			close();
		}
		return returnValue;
	}
	
	//Sessions
	public boolean createSessionsTable(){
		boolean returnValue = false;
		try {
			connect();
			String query =
					"CREATE TABLE Sessions(" +
						"Email VARCHAR2(128) PRIMARY KEY," +
						"Session_Id VARCHAR2(128)," +
						"Created_Datetime TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL," +
						"Created_User_Email VARCHAR2(128) NOT NULL," +
						"Modified_Datetime TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL," +
						"Modified_User_Email VARCHAR2(128) NOT NULL," +
						"CONSTRAINT FK_Session_Users FOREIGN KEY(Email)" +
						"REFERENCES Users(Email)" +
					")";
			stmt = con.createStatement();
			returnValue = stmt.executeUpdate(query) == 0;   //Create Drop은 0이면 성공, 실패하면 예외
		} catch (SQLException e) {
			System.out.print(e.getMessage());
			e.getStackTrace();
		} finally {
			close();
		}
		return returnValue;
	}
	
	//Records
	public boolean createRecordsTable(){
		boolean returnValue = false;
		try {
			connect();
			String query =
					"CREATE TABLE Records(" +
						"Seq NUMBER PRIMARY KEY," +
						"Created_Datetime TIMESTAMP DEFAULT SYSTIMESTAMP," +
						"Created_User_Email VARCHAR2(128)," +
						"Location VARCHAR2(128)," +
						"Before_Data VARCHAR2(1024)," +
						"After_Data VARCHAR2(1024)," +
						"CONSTRAINT FK_Records_Users FOREIGN KEY (Created_User_Email)" +
						"REFERENCES Users(Email)" +
					")";
			stmt = con.createStatement();
			returnValue = stmt.executeUpdate(query) == 0;   //Create Drop은 0이면 성공, 실패하면 예외
		} catch (SQLException e) {
			System.out.print(e.getMessage());
			e.getStackTrace();
		} finally {
			close();
		}
		return returnValue;
	}
	
	//BoardCodes
	public boolean createBoardCodesTable(){
		boolean returnValue = false;
		try {
			connect();
			String query =
					"CREATE TABLE Board_Codes(" +
							"Code VARCHAR2(16) PRIMARY KEY," +
							"Name VARCHAR2(16) NOT NULL," +
							"Is_Showing NUMBER(1) DEFAULT 1 NOT NULL," +
							"Created_Datetime TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL," +
							"Created_User_Email VARCHAR2(128) NOT NULL," +
							"Modified_Datetime TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL," +
							"Modified_User_Email VARCHAR2(128) NOT NULL" +
							")";
			stmt = con.createStatement();
			returnValue = stmt.executeUpdate(query) == 0;   //Create Drop은 0이면 성공, 실패하면 예외
		} catch (SQLException e) {
			System.out.print(e.getMessage());
			e.getStackTrace();
		} finally {
			close();
		}
		return returnValue;
	}
	
	//BoardPosts
	public boolean createBoardPostsTable(){
		boolean returnValue = false;
		try {
			connect();
			String query =
					"CREATE TABLE Board_Posts(" +
							"Board_Code VARCHAR2(16)," +
							"Seq NUMBER," +
							"Parent_Board_Seq NUMBER," +
							"Type VARCHAR2(16)," +
							"Title VARCHAR2(256)," +
							"Content CLOB," +
							"Attachment_Loction VARCHAR2(256)," +
							"Hits NUMBER DEFAULT 0 NOT NULL," +
							"Likes NUMBER DEFAULT 0 NOT NULL," +
							"Hates NUMBER DEFAULT 0 NOT NULL," +
							"Is_Showing NUMBER(1) DEFAULT 1 NOT NULL," +
							"Created_Datetime TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL," +
							"Created_User_Email VARCHAR2(128) NOT NULL," +
							"Modified_Datetime TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL," +
							"Modified_User_Email VARCHAR2(128) NOT NULL," +
							"CONSTRAINT PK_BoardPosts PRIMARY KEY (Board_Code, Seq)," +
							"CONSTRAINT FK_BoardPosts_BoardCodes FOREIGN KEY (Board_Code)" +
							"REFERENCES Board_Codes(Code)" +
							")";
			stmt = con.createStatement();
			returnValue = stmt.executeUpdate(query) == 0;   //Create Drop은 0이면 성공, 실패하면 예외
		} catch (SQLException e) {
			System.out.print(e.getMessage());
			e.getStackTrace();
		} finally {
			close();
		}
		return returnValue;
	}
	
	//Comments
	public boolean createCommentsTable(){
		boolean returnValue = false;
		try {
			connect();
			String query =
					"CREATE TABLE Comments(" +
							"Board_Code VARCHAR2(16)," +
							"Post_Seq NUMBER," +
							"Seq NUMBER," +
							"Parent_Comment_Seq NUMBER," +
							"Content VARCHAR2(1024) NOT NULL," +
							"Likes NUMBER DEFAULT 0 NOT NULL," +
							"Hates NUMBER DEFAULT 0 NOT NULL," +
							"Is_Showing NUMBER DEFAULT 1 NOT NULL," +
							"Created_Datetime TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL," +
							"Created_User_Email VARCHAR2(128) NOT NULL," +
							"Modified_Datetime TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL," +
							"Modified_User_Email VARCHAR2(128) NOT NULL," +
							"CONSTRAINT PK_Comments PRIMARY KEY (Board_Code, Post_Seq, Seq)" +
							")";
			stmt = con.createStatement();
			returnValue = stmt.executeUpdate(query) == 0;   //Create Drop은 0이면 성공, 실패하면 예외
		} catch (SQLException e) {
			System.out.print(e.getMessage());
			e.getStackTrace();
		} finally {
			close();
		}
		return returnValue;
	}
	
}