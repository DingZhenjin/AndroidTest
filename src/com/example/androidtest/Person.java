package com.example.androidtest;

import java.io.Serializable;


public class Person implements Serializable {
	private String name;
	private String age;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}
	
	public Person(String name,String age) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.age=age;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getAge(){
		return this.age;
	}
}
