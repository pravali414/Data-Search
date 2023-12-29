package com.bezkoder.spring.datajpa.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "salary")
	private int salary;
	@Column(name = "roll")
	private String roll;
	@Column(name="date")
	private Timestamp date;

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

		this.name = name;
	}


	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Employee(long id, String name, int salary, String roll, Timestamp date) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.roll = roll;
		this.date = date;
	}

	public Employee() {
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", name='" + name + '\'' +
				", salary=" + salary +
				", roll='" + roll + '\'' +
				", date=" + date +
				'}';
	}
}




