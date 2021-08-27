package javaProject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DataBaseTest {
	public static final Scanner scan = new Scanner(System.in);
	public static final int INSERT = 1, SEARCH = 2, DELETE = 3, UPDATE = 4, EXIT = 5;
	public static final Date today = new Date();//오늘날짜로 출력받기
	public static final SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");//날짜형식
	public static void main(String[] args) {
		boolean flag = false;
		int selcetNumber = 0;
		boolean numberInputContitue = false;
		
		while (!flag) {
			//메뉴출력 / 번호선택
			selcetNumber = displayMenu();
			
			switch (selcetNumber) {
			case INSERT:
				studentInsert();
				break;
			case SEARCH:
				studentSearch();
				break;
			case DELETE:
				studentDelete();
				break;
			case UPDATE:
				studentUpdate();
				break;
			case EXIT:
				flag = true;
				break;
			default:
				System.out.println("숫자범위를 초과하였습니다. 다시 입력해주세요.");
				break;
			}// end of switch
		}//end of while
		System.out.println("프로그램 종료");
	}//end of main
	
	//학생정보입력
	private static void studentInsert() {
		String s_num = null;
		String s_name = null;
		int s_java = 0;
		int s_android = 0;
		int s_kotlin = 0;
		
		while (true) {
			System.out.print("학번(01) 입력 :");
			s_num = scan.nextLine();
			s_num = scan.nextLine();
			if (s_num.length() != 2) {
				System.out.println("학생번호를 2자리를 지켜주세요");
				continue;
			} 
			s_num = format1.format(today) + s_num;
			boolean duplicate = DBController.duplicateCheck(s_num);
			if(duplicate) {
				System.out.println("존재하는 학생번호 입니다.");
				continue;
			}
			break;
		}
		while (true) {
			System.out.print("이름입력 :");
			s_name = scan.nextLine();
			if (s_name.length() == 0 && s_name.length() > 7) {
				System.out.println("옳은 이름을 입력해주세요");
				continue;
			} 
			break;
			
		}//end of while
		while (true) {
			System.out.print("JAVA점수 입력 :");
			s_java = scan.nextInt();
			if (s_java < 0 && s_java > 100) {
				System.out.println("옳은 점수를 입력해주세요");
				continue;
			} 
			break;
		}//end of while
		while (true) {
			System.out.print("ANDROID점수 입력 :");
			s_android = scan.nextInt();
			if (s_android < 0 && s_android > 100) {
				System.out.println("옳은 점수를 입력해주세요");
				continue;
			} 
			break;
		}//end of while
		
		while (true) {
			System.out.print("KOTLIN점수 입력 :");
			s_kotlin = scan.nextInt();
			if (s_kotlin < 0 && s_kotlin > 100) {
				System.out.println("옳은 점수를 입력해주세요");
				continue;
			} 
			break;
		}//end of while
		Student student = new Student(s_num, s_name, s_java, s_android, s_kotlin);
		int count = DBController.studentInsertTBL(student);

	}
	//학생정보검색
	private static void studentSearch() {
		boolean flag = false;
		int selcetNumber = 0;
		final int BASIC =1, ASC =2, DESC=3, EXIT =4;
		boolean numberInputContitue = false;
		
		while (!flag) {
			// 매뉴출력및 번호선택
			
			selcetNumber = displaySearchMenu();
			
			switch (selcetNumber) {
			case BASIC://기본
				DBController.studentBasicSearchTBL();
				break;
			case ASC:  //오름
				DBController.studentASCSearchTBL();
				break;
			case DESC: //내림
				DBController.studentDESCSearchTBL();
				break;
			case EXIT: //종료
				flag = true;
				break;
			default:
				System.out.println("숫자범위를 초과하였습니다. 다시 입력해주세요.");
				break;
			}// end of switch
		}//end of while
	}
	//학생정보삭제
	private static void studentDelete() {
		System.out.print("삭제할 학생의 학생번호입력 :");
		String s_num = scan.nextLine();
		s_num = scan.nextLine();
		int count = DBController.studentDeleteTBL(s_num);
		
		if(count != 0) {
			System.out.println(s_num + "학생 정보를 삭제했습니다.");
		}else {
			System.out.println(s_num + "학생 정보를 삭제를 실패했습니다.");
		}
	}
	//학생정보수정
	private static void studentUpdate() {
		int count = 0;
		
		System.out.print("수정할 학생의 학생번호 입력 :");
		String s_num = scan.nextLine();
		s_num = scan.nextLine();
		
		System.out.print("수정할 학생의 JAVA성적 입력 :");
		int s_java = scan.nextInt();
		
		System.out.print("수정할 학생의 ANDROID성적 입력 :");
		int s_android = scan.nextInt();
		
		System.out.print("수정할 학생의 KOTLIN성적 입력 :");
		int s_kotlin = scan.nextInt();
		
		count = DBController.studentUpdateTBL(s_num, s_java, s_android, s_kotlin);
		if(count != 0) {
			System.out.println(s_num + "학생 점수 수정에 성공했습니다.");
		}else {
			System.out.println(s_num + "학생 점수 수정에 실패했습니다.");
		}
	}
	//번호선택메뉴
	private static int displayMenu() {
		int selcetNumber = 0;
		boolean flag = false;
		
		while (!flag) {
			System.out.println("◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆");
			System.out.println("◆◆◆◆ 1.학생정보입력  2.학생정보검색  3.학생정보삭제  4.학생정보수정 5.종료 ◆◆◆◆");
			System.out.println("◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆");
			System.out.print("번호선택 :");
			
			try {
				//번호선택
				selcetNumber = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				continue;
			} catch (Exception e) {
				System.out.println("다시 숫자만 입력해주세요.");
				continue;
			}
			break;
		}//end of while
		return selcetNumber;

	}
	//메뉴검색
	private static int displaySearchMenu() {
		int selcetNumber = 0;
		boolean flag = false;
		
		while (!flag) {
			System.out.println();
			System.out.println("◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆");
			System.out.println("◆◆◆◆◆◆ 1.성적기본정렬  2.성적오름차순정렬  3.성적내림차순정렬  4.종료 ◆◆◆◆◆◆");
			System.out.println("◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆");
			System.out.print("번호선택 :");
			
			try {
				// 번호선택
				selcetNumber = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				continue;
			} catch (Exception e) {
				System.out.println("다시 숫자만 입력해주세요.");
				continue;
			}
			break;
		}//end of while
		return selcetNumber;
	}
}//end of class


