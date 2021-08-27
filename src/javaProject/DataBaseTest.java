package javaProject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DataBaseTest {
	public static final Scanner scan = new Scanner(System.in);
	public static final int INSERT = 1, SEARCH = 2, DELETE = 3, UPDATE = 4, EXIT = 5;
	public static final Date today = new Date();//���ó�¥�� ��¹ޱ�
	public static final SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");//��¥����
	public static void main(String[] args) {
		boolean flag = false;
		int selcetNumber = 0;
		boolean numberInputContitue = false;
		
		while (!flag) {
			//�޴���� / ��ȣ����
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
				System.out.println("���ڹ����� �ʰ��Ͽ����ϴ�. �ٽ� �Է����ּ���.");
				break;
			}// end of switch
		}//end of while
		System.out.println("���α׷� ����");
	}//end of main
	
	//�л������Է�
	private static void studentInsert() {
		String s_num = null;
		String s_name = null;
		int s_java = 0;
		int s_android = 0;
		int s_kotlin = 0;
		
		while (true) {
			System.out.print("�й�(01) �Է� :");
			s_num = scan.nextLine();
			s_num = scan.nextLine();
			if (s_num.length() != 2) {
				System.out.println("�л���ȣ�� 2�ڸ��� �����ּ���");
				continue;
			} 
			s_num = format1.format(today) + s_num;
			boolean duplicate = DBController.duplicateCheck(s_num);
			if(duplicate) {
				System.out.println("�����ϴ� �л���ȣ �Դϴ�.");
				continue;
			}
			break;
		}
		while (true) {
			System.out.print("�̸��Է� :");
			s_name = scan.nextLine();
			if (s_name.length() == 0 && s_name.length() > 7) {
				System.out.println("���� �̸��� �Է����ּ���");
				continue;
			} 
			break;
			
		}//end of while
		while (true) {
			System.out.print("JAVA���� �Է� :");
			s_java = scan.nextInt();
			if (s_java < 0 && s_java > 100) {
				System.out.println("���� ������ �Է����ּ���");
				continue;
			} 
			break;
		}//end of while
		while (true) {
			System.out.print("ANDROID���� �Է� :");
			s_android = scan.nextInt();
			if (s_android < 0 && s_android > 100) {
				System.out.println("���� ������ �Է����ּ���");
				continue;
			} 
			break;
		}//end of while
		
		while (true) {
			System.out.print("KOTLIN���� �Է� :");
			s_kotlin = scan.nextInt();
			if (s_kotlin < 0 && s_kotlin > 100) {
				System.out.println("���� ������ �Է����ּ���");
				continue;
			} 
			break;
		}//end of while
		Student student = new Student(s_num, s_name, s_java, s_android, s_kotlin);
		int count = DBController.studentInsertTBL(student);

	}
	//�л������˻�
	private static void studentSearch() {
		boolean flag = false;
		int selcetNumber = 0;
		final int BASIC =1, ASC =2, DESC=3, EXIT =4;
		boolean numberInputContitue = false;
		
		while (!flag) {
			// �Ŵ���¹� ��ȣ����
			
			selcetNumber = displaySearchMenu();
			
			switch (selcetNumber) {
			case BASIC://�⺻
				DBController.studentBasicSearchTBL();
				break;
			case ASC:  //����
				DBController.studentASCSearchTBL();
				break;
			case DESC: //����
				DBController.studentDESCSearchTBL();
				break;
			case EXIT: //����
				flag = true;
				break;
			default:
				System.out.println("���ڹ����� �ʰ��Ͽ����ϴ�. �ٽ� �Է����ּ���.");
				break;
			}// end of switch
		}//end of while
	}
	//�л���������
	private static void studentDelete() {
		System.out.print("������ �л��� �л���ȣ�Է� :");
		String s_num = scan.nextLine();
		s_num = scan.nextLine();
		int count = DBController.studentDeleteTBL(s_num);
		
		if(count != 0) {
			System.out.println(s_num + "�л� ������ �����߽��ϴ�.");
		}else {
			System.out.println(s_num + "�л� ������ ������ �����߽��ϴ�.");
		}
	}
	//�л���������
	private static void studentUpdate() {
		int count = 0;
		
		System.out.print("������ �л��� �л���ȣ �Է� :");
		String s_num = scan.nextLine();
		s_num = scan.nextLine();
		
		System.out.print("������ �л��� JAVA���� �Է� :");
		int s_java = scan.nextInt();
		
		System.out.print("������ �л��� ANDROID���� �Է� :");
		int s_android = scan.nextInt();
		
		System.out.print("������ �л��� KOTLIN���� �Է� :");
		int s_kotlin = scan.nextInt();
		
		count = DBController.studentUpdateTBL(s_num, s_java, s_android, s_kotlin);
		if(count != 0) {
			System.out.println(s_num + "�л� ���� ������ �����߽��ϴ�.");
		}else {
			System.out.println(s_num + "�л� ���� ������ �����߽��ϴ�.");
		}
	}
	//��ȣ���ø޴�
	private static int displayMenu() {
		int selcetNumber = 0;
		boolean flag = false;
		
		while (!flag) {
			System.out.println("�ߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡ�");
			System.out.println("�ߡߡߡ� 1.�л������Է�  2.�л������˻�  3.�л���������  4.�л��������� 5.���� �ߡߡߡ�");
			System.out.println("�ߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡ�");
			System.out.print("��ȣ���� :");
			
			try {
				//��ȣ����
				selcetNumber = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("���ڸ� �Է����ּ���.");
				continue;
			} catch (Exception e) {
				System.out.println("�ٽ� ���ڸ� �Է����ּ���.");
				continue;
			}
			break;
		}//end of while
		return selcetNumber;

	}
	//�޴��˻�
	private static int displaySearchMenu() {
		int selcetNumber = 0;
		boolean flag = false;
		
		while (!flag) {
			System.out.println();
			System.out.println("�ߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡ�");
			System.out.println("�ߡߡߡߡߡ� 1.�����⺻����  2.����������������  3.����������������  4.���� �ߡߡߡߡߡ�");
			System.out.println("�ߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡ�");
			System.out.print("��ȣ���� :");
			
			try {
				// ��ȣ����
				selcetNumber = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("���ڸ� �Է����ּ���.");
				continue;
			} catch (Exception e) {
				System.out.println("�ٽ� ���ڸ� �Է����ּ���.");
				continue;
			}
			break;
		}//end of while
		return selcetNumber;
	}
}//end of class


