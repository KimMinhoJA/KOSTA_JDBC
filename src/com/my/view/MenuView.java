package com.my.view;

public class MenuView {
	/**
	 * ù ȭ�� �޴�
	 */
	public static void printMenu() {
		System.out.println("==������==");
		System.out.println("�۾�����");
		System.out.println("1. ����, 2. �α���, 9. ����");

	}

	/**
	 * ����� �α��ν� �޴�
	 * @param id
	 */
	public static void printUserMenu(String id) {
		System.out.println("==" + id + "����� �޴�==");
		System.out.println("1. ������ ����, 2. �ֹ� ���� ����, 3. �α׾ƿ�");
	}

	/**
	 * ����� ���� ���� �޴�
	 */
	public static void printUserSubMenu() {
		System.out.println("1. ����, 2. Ż��, 9. ������");
	}
	
	public static void printOrderInfoMenu() {
		System.out.println("==�ֹ� ���� ��ȸ==");
		System.out.println("1. �� ��� �ֹ� ���� ��ȸ"); 			//, 2. �ֹ� ��ȣ�� ��ȸ");
	}

	/**
	 * ������ �α��ν� �޴�
	 */
	public static void printAdminMenu() {
		System.out.println("==������ �޴�==");
		System.out.println("1. ID�� �˻�, 2. �̸����� �˻�, 3. ��ü �˻�, 9. ������ �޴� ������");
	}
	
	
}
