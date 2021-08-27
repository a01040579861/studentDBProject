package javaProject;

import java.io.FileReader;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtility {
	//DB�� �����ϴ� �Լ�
	public static Connection getConnection() {
		Connection con = null;
		try {

			Properties properties = new Properties();
			String filePath = DBUtility.class.getResource("db.properties").getPath();
			
			filePath = URLDecoder.decode(filePath, "utf-8");
			properties.load(new FileReader(filePath));

			//db.properties�� ����� ����̹� �Ӽ� �б�
			String driver = properties.getProperty("DRIVER");
			//db.properties�� ����� �ּҸ� ������ �Ϸù��� �Ӽ�
			String url = properties.getProperty("URL");
			//db.properties�� ����� SQL userid �Ӽ�
			String userId = properties.getProperty("userID");
			//db.properties�� ����� SQL userPassword �Ӽ�
			String userPassword = properties.getProperty("userPassword");

			//jdbc ����̹� Ȱ��ȭ
			Class.forName(driver);
			
			//MySql ����Ÿ���̽� ����
			con = DriverManager.getConnection(url, userId, userPassword);
			
		} catch (Exception e1) {
			System.out.println("�Է� ���� �Ͽ����ϴ�.");
			e1.printStackTrace();
		}
		System.out.println("�Է� �����Ͽ����ϴ�.");
		
		return con;	
	}
}
