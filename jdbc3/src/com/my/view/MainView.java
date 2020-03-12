package com.my.view;

import java.util.Scanner;

import com.my.controller.CustomerController;
import com.my.vo.Customer;

public class MainView {
	private static CustomerController controller = CustomerController.getInstance();
	private static Scanner sc = new Scanner(System.in);

	public static void printMenu() {
		System.out.println("==������==");
		System.out.println("�۾�����");
		System.out.println("1. ����, 2. �α���, 3. ���̵�� �˻�, 4. �̸����ΰ˻�, 5. ��ü�˻�, 9. ����");

	}

	public static void login() {
		System.out.println("==�α��� �۾�==");
		System.out.print("ID : ");
		String id = sc.next();
		System.out.print("Password : ");
		String pwd = sc.next();
		controller.login(id, pwd);
	}
	
	public static void register() {
		System.out.print("==��� �۾�==");
		System.out.print("ID : ");
		String id = sc.next();
		System.out.print("Password : ");
		String pwd = sc.next();
		System.out.print("name : ");
		String name = sc.next();
		System.out.print("gender(M/F) : ");
		String gender = sc.next();
		controller.register(new Customer(id, pwd, name, gender, null, 0));
	}
	
	public static void findById() {
		System.out.print("==���̵� �˻�==");
		System.out.print("ID : ");
		String id = sc.next();
		controller.findById(id);
	}
	
	public static void findByName() {
		System.out.print("==�̸� �˻�==");
		System.out.print("word : ");
		String word = sc.next();
		controller.findByName(word);
	}
	
	public static void findAll() {
		controller.findAll();
	}
	
	public static void update() {
		System.out.println("==���� �۾�==");
		System.out.print("ID : ");
		String id = sc.next();
		System.out.print("Password : ");
		String pwd = sc.next();
		System.out.print("name : ");
		String name = sc.next();
		System.out.print("gender(M/F) : ");
		String gender = sc.next();
		controller.update(new Customer(id, pwd, name, gender, null, 0));
	}
	
	
	public static void main(String[] args) {
		int menu = 0;
		do{
			printMenu();
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				register();
				break;
			case 2:
				login();
				break;
			case 3:
				findById();
				break;
			case 4:
				findByName();
				break;
			case 5:
				findAll();
				break;
			case 9:
				System.out.println("���α׷� ����");
				break;
			default:
				System.out.println("�߸��� �Է�");
			}
		}while(menu != 9);
	}
}
