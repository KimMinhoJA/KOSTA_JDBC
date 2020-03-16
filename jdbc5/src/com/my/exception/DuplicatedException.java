package com.my.exception;

/**
 * 상품 혹은 고객의 내용이 중복되었을 때 발생하는 Exception
 * @author kosta
 */
public class DuplicatedException extends AddException{
	public DuplicatedException() {
		super();
	}
	
	public DuplicatedException(String message) {
		super(message);
	}
	
}
