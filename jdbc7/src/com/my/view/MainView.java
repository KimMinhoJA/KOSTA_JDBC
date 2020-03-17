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
		System.out.println("==�α��� �۾�==");
		System.out.print("ID : ");
		String id = sc.nextLine();
		System.out.print("Password : ");
		String pwd = sc.nextLine();
		customerController.login(id, pwd);

		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		if (session != null) {// �α��� ���� ����
			//������ ���� �α��� �� ����
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
						System.out.println("�߸��� �Է�");

					}
				}
			}//������ ��� �� 
			else {//����� ���� �α��� �� ����
				while (true) {
					MenuView.printUserMenu(id);
					switch (sc.nextLine()) {
					case "1": // ������ ����
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
					case "9": // �α׾ƿ�
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
	 * ȸ�� ��� �޼ҵ�
	 */
	public static void register() {
		System.out.print("==��� �۾�==");
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
	 * �����ڸ�� ���� id�� ȸ�� �˻�
	 */
	public static void findById() {
		System.out.print("==���̵� �˻�==");
		System.out.print("ID : ");
		String id = sc.nextLine();
		customerController.findById(id);
	}

	/**
	 * �����ڸ�� ���� �̸����� ȸ�� �˻� 
	 */
	public static void findByName() {
		System.out.print("==�̸� �˻�==");
		System.out.print("word : ");
		String word = sc.nextLine();
		customerController.findByName(word);
	}

	/**
	 * ��ü �� �˻�
	 */
	public static void findAll() {
		customerController.findAll();
	}

	/**
	 * ����� ��� ���� ����
	 * @param id
	 */
	public static void modify(String id) {
		System.out.println("==���� �ϱ�==");
		System.out.println("���� �� �Ϸ��� enter�� ��������.");
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
	 * ȸ�� Ż�� �޼ҵ�
	 * @param id
	 */
	private static void withdraw(String id) {
		System.out.println("==Ż���ϱ�==");
		customerController.withdraw(id);

	}
	
	/**
	 * �ش� ���� �ֹ����� ���ϱ�
	 * @param id
	 */
	public static void showOrderInfo(String id) {
		MenuView.printOrderInfoMenu();
		
		switch(sc.nextLine()) {
		case "1" : 
			orderController.orderFindById(id);
			break;
//		case "2" : 
//			System.out.println("�ֹ� ��ȣ�� �Է��ϼ���");
//			orderController.orderFindByNo(new Integer(sc.nextLine()));
//			break;
		default:
			System.out.println("�߸��� ��ȣ");
		}
	}
	
	public static void viewCart(String id) {
//	
//		//��ٱ���
//		//��ǰ��ȣ, ��ǰ������, ����
//		Map<Product, Integer> cart = new HashMap<>();
//		Product p1 = new Product();
//		p1.setProd_no("1001");
//		p1.setProd_name("�Ƹ޸�ī��");
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
//		p2.setProd_name("ICE�Ƹ޸�ī��");
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
//		p3.setProd_name("�Ƹ޸�ī��");
//		p3.setProd_price(1000);
//		int quantity3 = 1;
//		
//		//��ٱ��Ͽ� �̹� �Ƹ޸�ī�� ��ǰ�� �ִٸ� ������ �����ϰ�
//		//�Ƹ޸�ī�� ��ǰ�� ���ٸ� ��ǰ�� ������ ���Ӱ� �߰��Ѵ�.
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
		System.out.println("==��ٱ��� ��� �۾�==");
		System.out.print("��ǰ��ȣ : ");
		String prod_no = sc.nextLine();
		System.out.print("���� : ");
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
				System.out.println("���α׷� ����");
				break;
			default:
				System.out.println("�߸��� �Է�");
			}
		} while (!menu.equals("9"));
	}
}
