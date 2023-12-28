package com.bezkoder.spring.datajpa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "salary")
	private int salary;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		salary = salary;
	}

	public Employee(long id, String name, int salary) {
		this.id = id;
		name = name;
		salary = salary;
	}
	public Employee() {};
	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", Name='" + name + '\'' +
				", Salary=" + salary +
				'}';
	}
}


