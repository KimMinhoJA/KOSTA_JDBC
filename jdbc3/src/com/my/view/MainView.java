package com.my.view;

import java.util.Scanner;

import com.my.controller.CustomerController;
import com.my.vo.Customer;

public class MainView {
	private static CustomerController controller = CustomerController.getInstance();
	private static Scanner sc = new Scanner(System.in);

	public static void printMenu() {
		System.out.println("==고객관리==");
		System.out.println("작업구분");
		System.out.println("1. 가입, 2. 로그인, 3. 아이디로 검색, 4. 이름으로검색, 5. 전체검색, 9. 종료");

	}

	public static void login() {
		System.out.println("==로그인 작업==");
		System.out.print("ID : ");
		String id = sc.next();
		System.out.print("Password : ");
		String pwd = sc.next();
		controller.login(id, pwd);
	}
	
	public static void register() {
		System.out.print("==등록 작업==");
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
		System.out.print("==아이디 검색==");
		System.out.print("ID : ");
		String id = sc.next();
		controller.findById(id);
	}
	
	public static void findByName() {
		System.out.print("==이름 검색==");
		System.out.print("word : ");
		String word = sc.next();
		controller.findByName(word);
	}
	
	public static void findAll() {
		controller.findAll();
	}
	
	public static void update() {
		System.out.println("==수정 작업==");
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
				System.out.println("프로그램 종료");
				break;
			default:
				System.out.println("잘못된 입력");
			}
		}while(menu != 9);
	}
}
