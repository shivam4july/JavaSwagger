package io.swagger.sample.models;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Pet {
	
	private long id;
	private String user;
	private String name;
	private int age;
	private String size;
	private String petRequirement;
	private String hostFeature;
	private String petBreed;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
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
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getPetRequirement() {
		return petRequirement;
	}
	public void setPetRequirement(String petRequirement) {
		this.petRequirement = petRequirement;
	}
	public String getHostFeature() {
		return hostFeature;
	}
	public void setHostFeature(String hostFeature) {
		this.hostFeature = hostFeature;
	}
	public String getPetBreed() {
		return petBreed;
	}
	public void setPetBreed(String petBreed) {
		this.petBreed = petBreed;
	}
	
	
}
