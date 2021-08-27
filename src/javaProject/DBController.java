package javaProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBController {
	public static boolean duplicateCheck(String s_num){
		List<Student> list = new ArrayList<Student>();
		Connection con = DBUtility.getConnection(); //DBUtility 연결 객체
		ResultSet rs = null; //결과값 부르는 객체
		String strQuery = "select * from student_score_tbl where s_num = ?";
		PreparedStatement preparedStatement = null; //SQL을 실행시키는 객체
		
		try {
			//SQL을 실행시키는 객체
			preparedStatement = con.prepareStatement(strQuery);
			preparedStatement.setString(1, s_num);
			rs = preparedStatement.executeQuery();//결과값 부르는 객체
			if(rs.next() != false) {
				return true;
			}
			
			while(rs.next()) {
				s_num = rs.getString(1); 
				String s_name = rs.getString(2);
				int s_java = rs.getInt(3);
				int s_android = rs.getInt(4);
				int s_kotlin = rs.getInt(5);
				int s_total = rs.getInt(6);
				double s_avg = rs.getDouble(7);
				String s_grade = rs.getString(8);
				Student student = new Student(s_num, s_name, s_java, s_android, s_kotlin, s_total, s_avg, s_grade);
				list.add(student);
				System.out.println(student.toString());
			}//end of while
			if(list == null) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}//end of finally
		return false;
	}
	
	public static int studentInsertTBL(Student student) {
		Connection con = DBUtility.getConnection();
		PreparedStatement preparedStatement = null;
		int count = 0;
		String insertQuery = "call pro_insertTBL(?,?,?,?,?)"; //SQL PROCEDURE 실행
		
		try {
			preparedStatement = con.prepareStatement(insertQuery);//SQL 쿼리문 실행객체
			preparedStatement.setString(1, student.getS_num()); //String s_num
			preparedStatement.setString(2, student.getS_name());//String s_name
			preparedStatement.setInt(3, student.getS_java());	//Int s_java
			preparedStatement.setInt(4, student.getS_android());//Int s_android
			preparedStatement.setInt(5, student.getS_kotlin()); //Int s_kotlin
			count = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}//end of finally
		return count;
	}
	
	public static void studentBasicSearchTBL() {
		Connection con = DBUtility.getConnection();
		ResultSet rs = null;
		String strQuery = "select * from student_score_tbl order by s_num asc";
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = con.prepareStatement(strQuery);
			rs = preparedStatement.executeQuery();
			
			System.out.println("▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼");
			System.out.println("  학생번호\t\t 이름\tJAVA\t KO\t AND\t총점\t 평균\t등급");
			System.out.println("▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");
			
			while(rs.next()) {
				String s_num = rs.getString(1);
				String s_name = rs.getString(2);
				int s_java = rs.getInt(3);
				int s_android = rs.getInt(4);
				int s_kotlin = rs.getInt(5);
				int s_total = rs.getInt(6);
				double s_avg = rs.getDouble(7);
				String s_grade = rs.getString(8);
				Student student = new Student(s_num, s_name, s_java, s_android, s_kotlin, s_total, s_avg, s_grade);
				System.out.println(student.toString());
			}//end of while
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}//end of finally
	}
	
	public static void studentASCSearchTBL() {
		Connection con = DBUtility.getConnection();
		ResultSet rs = null;
		String strQuery = "select * from student_score_tbl order by s_total asc";
		PreparedStatement preparedStatement =null;
		
		try {
			preparedStatement = con.prepareStatement(strQuery);
			rs = preparedStatement.executeQuery();
			
			System.out.println("▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼");
			System.out.println("  학생번호\t\t 이름\tJAVA\t KO\t AND\t총점\t 평균\t등급");
			System.out.println("▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");
			
			while(rs.next()) {
				String s_num = rs.getString(1);
				String s_name = rs.getString(2);
				int s_java = rs.getInt(3);
				int s_android = rs.getInt(4);
				int s_kotlin = rs.getInt(5);
				int s_total = rs.getInt(6);
				double s_avg = rs.getDouble(7);
				String s_grade = rs.getString(8);
				Student student = new Student(s_num, s_name, s_java, s_android, s_kotlin, s_total, s_avg, s_grade);
				System.out.println(student.toString());
			}//end of while
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}//end of finally
		
	}

	public static void studentDESCSearchTBL() {
		Connection con = DBUtility.getConnection();
		ResultSet rs = null;
		String strQuery = "select * from student_score_tbl order by s_total desc";
		PreparedStatement preparedStatement =null;
		
		try {
			preparedStatement=con.prepareStatement(strQuery);
			rs=preparedStatement.executeQuery();
			
			System.out.println("▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼");
			System.out.println("  학생번호\t\t 이름\tJAVA\t KO\t AND\t총점\t 평균\t등급");
			System.out.println("▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");
			
			while(rs.next()) {
				String s_num = rs.getString(1);
				String s_name = rs.getString(2);
				int s_java = rs.getInt(3);
				int s_android = rs.getInt(4);
				int s_kotlin = rs.getInt(5);
				int s_total = rs.getInt(6);
				double s_avg = rs.getDouble(7);
				String s_grade = rs.getString(8);
				Student student = new Student(s_num, s_name, s_java, s_android, s_kotlin, s_total, s_avg, s_grade);
				System.out.println(student.toString());
			}//end of while
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}//end of finally
		
	}

	public static int studentDeleteTBL(String s_num) {
		Connection con = DBUtility.getConnection();
		PreparedStatement preparedStatement = null;
		int count =0;
		String insertQuery = "delete from student_score_tbl where s_num = ?";
		try {
			preparedStatement = con.prepareStatement(insertQuery);
			preparedStatement.setString(1, s_num);
			count = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}//end of finally
		return count;
	}
	
	public static int studentUpdateTBL(String s_num, int s_java, int s_android, int s_kotlin) {
		Connection con = DBUtility.getConnection();
		PreparedStatement preparedStatement = null;
		int count = 0;
		String updateQuery = "call pro_updateTBL(?,?,?,?)";
		try {
			preparedStatement = con.prepareStatement(updateQuery);
			preparedStatement.setString(1, s_num);
			preparedStatement.setInt(2, s_java);
			preparedStatement.setInt(3, s_android);
			preparedStatement.setInt(4, s_kotlin);
			count = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}//end of finally
		return count;
	}
}