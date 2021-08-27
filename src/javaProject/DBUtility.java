package javaProject;

import java.io.FileReader;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtility {
	//DB에 연결하는 함수
	public static Connection getConnection() {
		Connection con = null;
		try {

			Properties properties = new Properties();
			String filePath = DBUtility.class.getResource("db.properties").getPath();
			
			filePath = URLDecoder.decode(filePath, "utf-8");
			properties.load(new FileReader(filePath));

			//db.properties에 담겨진 드라이버 속성 읽기
			String driver = properties.getProperty("DRIVER");
			//db.properties에 담겨진 주소를 포함한 일련문자 속성
			String url = properties.getProperty("URL");
			//db.properties에 담겨진 SQL userid 속성
			String userId = properties.getProperty("userID");
			//db.properties에 담겨진 SQL userPassword 속성
			String userPassword = properties.getProperty("userPassword");

			//jdbc 드라이버 활성화
			Class.forName(driver);
			
			//MySql 데이타베이스 연결
			con = DriverManager.getConnection(url, userId, userPassword);
			
		} catch (Exception e1) {
			System.out.println("입력 실패 하였습니다.");
			e1.printStackTrace();
		}
		System.out.println("입력 성공하였습니다.");
		
		return con;	
	}
}
