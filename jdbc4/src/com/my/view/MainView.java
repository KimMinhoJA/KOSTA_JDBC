package com.my.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.my.controller.CustomerController;
import com.my.session.Session;
import com.my.session.SessionSet;
import com.my.vo.Customer;

public class MainView {
	private static CustomerController controller = CustomerController.getInstance();
	private static Scanner sc = new Scanner(System.in);

	public static void login() {
		System.out.println("==로그인 작업==");
		System.out.print("ID : ");
		String id = sc.nextLine();
		System.out.print("Password : ");
		String pwd = sc.nextLine();
		controller.login(id, pwd);

		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		if (session != null) {// 로그인 성공 상태
			if ("admin".equals(id)) {
				while (true) {
					MenuView.printAdminMenu();
					switch (sc.nextLine()) {
					case "1":
						findById();
						break;
					case "2":
						findByName();
						break;
					case "3":
						findAll();
						break;
					case "9":
						controller.logout(id);
						return;
					default:
						System.out.println("잘못된 입력");

					}
				}
			} else {
				while (true) {
					MenuView.printUserMenu(id);
					switch (sc.nextLine()) {
					case "1": // 내정보 보기
						controller.findById(id);
						MenuView.printUserSubMenu();
						switch (sc.nextLine()) {
						case "1":
							modify(session.getSessionId());
							break;
						case "2":
							withdraw(id);
						case "9":
							return;
						default:
						}
						break;
					case "2": // 로그아웃
//				ss.remove(session);
						controller.logout(id);
						break;
					default:

					}
				}
			}
		}
	}

	private static void withdraw(String id) {
		System.out.println("==탈퇴하기==");
		controller.withdraw(id);

	}

	public static void register() {
		System.out.print("==등록 작업==");
		System.out.print("ID : ");
		String id = sc.nextLine();
		System.out.print("Password : ");
		String pwd = sc.nextLine();
		System.out.print("name : ");
		String name = sc.nextLine();
		System.out.print("gender(M/F) : ");
		String gender = sc.nextLine();
		controller.register(new Customer(id, pwd, name, gender, null, 0));
	}

	public static void findById() {
		System.out.print("==아이디 검색==");
		System.out.print("ID : ");
		String id = sc.nextLine();
		controller.findById(id);
	}

	public static void findByName() {
		System.out.print("==이름 검색==");
		System.out.print("word : ");
		String word = sc.nextLine();
		controller.findByName(word);
	}

	public static void findAll() {
		controller.findAll();
	}

	public static void modify(String id) {
		System.out.println("==수정 하기==");
		System.out.println("수정 안 하려면 enter를 누르세요.");
		System.out.print("Password : ");
		String pwd = sc.nextLine();
		System.out.print("name : ");
		String name = sc.nextLine();
		System.out.print("gender(M/F) : ");
		String gender = sc.nextLine();
		System.out.println("regist date(yy-mm-dd) : ");
		String reg_dtS = sc.nextLine();

		Customer c = new Customer();
		c.setId(id);
		if (!"".equals(pwd))
			c.setPwd(pwd);
		if (!"".equals(name))
			c.setName(name);
		if (!"".equals(gender))
			c.setGender(gender);
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		if (!"".equals(reg_dtS)) {
			Date reg_dt;
			try {
				reg_dt = sdf.parse(reg_dtS);
				c.setReg_dt(reg_dt);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		controller.modify(c);
	}

	public static void main(String[] args) {
		String menu = null;
		do {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet());
			MenuView.printMenu();
			menu = sc.nextLine();

			switch (menu) {
			case "1":
				register();
				break;
			case "2":
				login();
				break;
			case "9":
				System.out.println("프로그램 종료");
				break;
			default:
				System.out.println("잘못된 입력");
			}
		} while (!menu.equals("9"));
	}
}
