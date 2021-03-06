package com.epam.jmp.classloader.test;

public class MutableInteger {

	private int value;

	public MutableInteger() {
		this.value = 0;
	}

	public MutableInteger(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}
	
}
