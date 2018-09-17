package com.xsh;

public class Header {
	
	/**名字 */
	private String name;
	/**年龄*/
	private int age;
	/**地址*/
	private String address;
	/** 成绩*/
	private int score;
	/** 绩点*/
	private double gpa;
	
	public Header() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	@Override
	public String toString() {
		return "Header [name=" + name + ", age=" + age + ", address=" + address + ", score=" + score + ", gpa=" + gpa
				+ "]";
	}
}
