package com.my.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.my.controller.CartController;
import com.my.controller.CustomerController;
import com.my.controller.OrderController;
import com.my.session.Session;
import com.my.session.SessionSet;
import com.my.vo.Customer;
import com.my.vo.Product;

public class MainView {
	private static CustomerController customerController = CustomerController.getInstance();
	private static OrderController orderController = OrderController.getInstance();
	private static CartController cartController = CartController.getInstance();
	private static Scanner sc = new Scanner(System.in);

	public static void login() {
		System.out.println("==로그인 작업==");
		System.out.print("ID : ");
		String id = sc.nextLine();
		System.out.print("Password : ");
		String pwd = sc.nextLine();
		customerController.login(id, pwd);

		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		if (session != null) {// 로그인 성공 상태
			//관리자 모드로 로그인 시 수행
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
						customerController.logout(id);
						return;
					default:
						System.out.println("잘못된 입력");

					}
				}
			}//관리자 모드 끝 
			else {//사용자 모드로 로그인 시 수행
				while (true) {
					MenuView.printUserMenu(id);
					switch (sc.nextLine()) {
					case "1": // 내정보 보기
						customerController.findById(id);
						MenuView.printUserSubMenu();
						switch (sc.nextLine()) {
						case "1":
							modify(session.getSessionId());
							break;
						case "2":
							withdraw(id);
							return;
						case "9":
							return;
						default:
						}
						break;
					case "2":
						showOrderInfo(id);
						break;
						
					case "4":
						viewCart(id);
						break;
					case "5":
						putCart(id);
						break;
					case "9": // 로그아웃
//				ss.remove(session);
						customerController.logout(id);
						return;
					default:

					}
				}
			}
		}
	}

	
	
	/**
	 * 회원 등록 메소드
	 */
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
		customerController.register(new Customer(id, pwd, name, gender, null, 0));
	}

	/**
	 * 관리자모드 전용 id로 회원 검색
	 */
	public static void findById() {
		System.out.print("==아이디 검색==");
		System.out.print("ID : ");
		String id = sc.nextLine();
		customerController.findById(id);
	}

	/**
	 * 관리자모드 전용 이름으로 회원 검색 
	 */
	public static void findByName() {
		System.out.print("==이름 검색==");
		System.out.print("word : ");
		String word = sc.nextLine();
		customerController.findByName(word);
	}

	/**
	 * 전체 고객 검색
	 */
	public static void findAll() {
		customerController.findAll();
	}

	/**
	 * 사용자 모드 정보 수정
	 * @param id
	 */
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
		customerController.modify(c);
	}

	
	/**
	 * 회원 탈퇴 메소드
	 * @param id
	 */
	private static void withdraw(String id) {
		System.out.println("==탈퇴하기==");
		customerController.withdraw(id);

	}
	
	/**
	 * 해당 고객의 주문내역 구하기
	 * @param id
	 */
	public static void showOrderInfo(String id) {
		MenuView.printOrderInfoMenu();
		
		switch(sc.nextLine()) {
		case "1" : 
			orderController.orderFindById(id);
			break;
//		case "2" : 
//			System.out.println("주문 번호를 입력하세요");
//			orderController.orderFindByNo(new Integer(sc.nextLine()));
//			break;
		default:
			System.out.println("잘못된 번호");
		}
	}
	
	public static void viewCart(String id) {
//	
//		//장바구니
//		//상품번호, 상품상세정보, 수량
//		Map<Product, Integer> cart = new HashMap<>();
//		Product p1 = new Product();
//		p1.setProd_no("1001");
//		p1.setProd_name("아메리카노");
//		p1.setProd_price(1000);
//		int quantity = 3;
//		Integer oldQuantity = cart.get(p1);
//		if(oldQuantity != null) {
//			quantity += oldQuantity;
//		}
//		cart.put(p1, quantity);
//		
//		//////////////////////
//		
//		Product p2 = new Product();
//		p2.setProd_no("1002");
//		p2.setProd_name("ICE아메리카노");
//		p2.setProd_price(1500);
//		int quantity2 = 1;
//		oldQuantity = cart.get(p2);
//		if(oldQuantity != null) {
//			quantity2 += oldQuantity;
//		}
//		cart.put(p2, quantity2);
//		
//		//////////////////////
//		
//		Product p3 = new Product();
//		p3.setProd_no("1001");
//		p3.setProd_name("아메리카노");
//		p3.setProd_price(1000);
//		int quantity3 = 1;
//		
//		//장바구니에 이미 아메리카노 상품이 있다면 수량을 누적하고
//		//아메리카노 상품이 없다면 상품의 수량을 새롭게 추가한다.
//		oldQuantity = cart.get(p3);
//		if(oldQuantity != null) {
//			quantity3 += oldQuantity;
//		}
//		cart.put(p3, quantity3);
//		
//		Session session = SessionSet.getInstance().get(id);
//		session.setAttribute("cart", cart);

		cartController.viewCart(id);
	}
	
	
	public static void putCart(String id) {
		System.out.println("==장바구니 담기 작업==");
		System.out.print("상품번호 : ");
		String prod_no = sc.nextLine();
		System.out.print("수량 : ");
		int quantity = Integer.parseInt(sc.nextLine());
		CartController cartController = CartController.getInstance();
		
		cartController.putCart(id, prod_no, quantity);
		
	}
	
	public static void main(String[] args) {
		String menu = null;
		do {
			SessionSet ss = SessionSet.getInstance();
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
