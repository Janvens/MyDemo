/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-3-15上午11:08:07</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.monodb.model;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-3-15 </p>
 * @version V1.0  
 */
public class Person{

	private String id;
	private String name;
	private int age;
	
	
	/**
	 * Zhang Wensheng 2013-3-15 上午11:08:45
	 */
	public Person() {
	}
	
	public Person(String name,int age){
		this.name = name;
		this.age = age;
	}
	
	public Person(String id, String name, int age){
		this.id = id;
		this.name = name;
		this.age = age;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person[id=" + id + ",name=" + name + ",age=" + age + "]";
	}
}
