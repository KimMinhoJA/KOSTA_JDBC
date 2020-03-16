package com.my.view;

public class MenuView {
	/**
	 * 첫 화면 메뉴
	 */
	public static void printMenu() {
		System.out.println("==고객관리==");
		System.out.println("작업구분");
		System.out.println("1. 가입, 2. 로그인, 9. 종료");

	}

	/**
	 * 사용자 로그인시 메뉴
	 * @param id
	 */
	public static void printUserMenu(String id) {
		System.out.println("==" + id + "사용자 메뉴==");
		System.out.println("1. 내정보 보기, 2. 주문 내역 보기, 3. 로그아웃");
	}

	/**
	 * 사용자 정보 관리 메뉴
	 */
	public static void printUserSubMenu() {
		System.out.println("1. 수정, 2. 탈퇴, 9. 나가기");
	}
	
	public static void printOrderInfoMenu() {
		System.out.println("==주문 내역 조회==");
		System.out.println("1. 내 모든 주문 내역 조회"); 			//, 2. 주문 번호로 조회");
	}

	/**
	 * 관리자 로그인시 메뉴
	 */
	public static void printAdminMenu() {
		System.out.println("==관리자 메뉴==");
		System.out.println("1. ID로 검색, 2. 이름으로 검색, 3. 전체 검색, 9. 관리자 메뉴 나가기");
	}
	
	
}
