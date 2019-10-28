package com.firecode.springcloud_alibaba.user.api.domain;

/**
 * 部门
 * @author JIANG
 */
public class Dept {

	private String name;

	private Integer number;
	

	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dept(String name, Integer number) {
		super();
		this.name = name;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Dept [name=" + name + ", number=" + number + "]";
	}
}
