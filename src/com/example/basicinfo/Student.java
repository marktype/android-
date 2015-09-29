package com.example.basicinfo;

import java.io.Serializable;

import com.example.layout.R.string;

public class Student implements Serializable{

	private String name;
	private String number;
	private String sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
