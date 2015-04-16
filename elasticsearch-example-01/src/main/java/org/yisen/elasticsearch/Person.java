package org.yisen.elasticsearch;

public class Person implements java.io.Serializable {

	private static final long serialVersionUID = -4744903047719225035L;

	private Integer id;
	private String name;
	private String gender;
	private String introduction;

	public Person() {
		super();
	}

	public Person(Integer id, String name, String gender, String introduction) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.introduction = introduction;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}
