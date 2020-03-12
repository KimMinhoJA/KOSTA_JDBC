package com.my.view;

public class MenuView {
	public static void printMenu() {
		System.out.println("==고객관리==");
		System.out.println("작업구분");
		System.out.println("1. 가입, 2. 로그인, 9. 종료");

	}

	public static void printUserMenu(String id) {
		System.out.println("==" + id + "사용자 메뉴==");
		System.out.println("1. 내정보 보기, 2. 로그아웃");
	}

	public static void printUserSubMenu() {
		System.out.println("1. 수정, 2. 탈퇴, 9. 나가기");
	}

	public static void printAdminMenu() {
		System.out.println("==관리자 메뉴==");
		System.out.println("1. ID로 검색, 2. 이름으로 검색, 3. 전체 검색, 9. 관리자 메뉴 나가기");
	}
}
