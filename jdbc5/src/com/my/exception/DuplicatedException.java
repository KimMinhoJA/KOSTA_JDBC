package com.my.exception;

/**
 * ��ǰ Ȥ�� ���� ������ �ߺ��Ǿ��� �� �߻��ϴ� Exception
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
