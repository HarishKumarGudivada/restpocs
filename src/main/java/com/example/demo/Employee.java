package com.example.demo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private double salary;

	public Employee() {}
	
	public Employee(int id,String name,double sal) {
		this.id=id;
		this.name=name;
		this.salary=sal;
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
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}

	
	@Override
	public String toString() {
		return this.getId()+"--"+this.getName()+"--"+this.getSalary();
	}
	
}
