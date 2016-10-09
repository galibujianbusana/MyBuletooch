package myapplication.com.myapplication;

import android.R.integer;

public class Student {
	
	int id;
	String name;
	String sex;
	String address;
	int money;
	
	//  new Student(2, time, msg, reserved, 300)
	//  time ,msg ,reserved
	
	public Student(int id, String name, String sex, String address, int money) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.address = address;
		this.money = money;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", address=" + address + ", money=" + money + "]";
	}
	
	
	
}
