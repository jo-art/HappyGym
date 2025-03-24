package com.happygym;

public class Member {
	private String mem_id;

	private String mem_pw;

	private String mem_name;

	private int mem_age;
	
	private String mem_pnum;

	private String mem_address;

	private String mem_height;

	private String mem_weight;

	// 기본생성자

	public Member() {

	}
	

	public Member(String m_id, String m_pw) {

		this.mem_id = m_id;

		this.mem_pw = m_pw;

		

	}
	
	public Member(String m_id, String m_pw ,String m_name) {

		this.mem_id = m_id;

		this.mem_pw = m_pw;
		this.mem_name= m_name;
		

	}
	

	public Member(String m_id, String m_pw, String m_name, int m_age, String m_pnum,String m_add, String m_height, String m_weight) {

		this.mem_id = m_id;

		this.mem_pw = m_pw;

		this.mem_name = m_name;

		this.mem_age = m_age;
		this.setMem_pnum(m_pnum);

		this.mem_address = m_add;

		this.mem_height = m_height;

		this.mem_weight = m_weight;

	}

	// 메소드
	//학생이 선택하는 메소드 

	// getter,setter

	public String getMem_id() {

		return mem_id;

	}

	public void setMem_id(String mem_id) {

		this.mem_id = mem_id;

	}

	public String getMem_pw() {

		return mem_pw;

	}

	public void setMem_pw(String mem_pw) {

		this.mem_pw = mem_pw;

	}

	public String getMem_name() {

		return mem_name;

	}

	public void setMem_name(String mem_name) {

		this.mem_name = mem_name;

	}

	public int getMem_age() {

		return mem_age;

	}

	public void setMem_age(int mem_age) {

		this.mem_age = mem_age;

	}

	public String getMem_address() {

		return mem_address;

	}

	public void setMem_address(String mem_address) {

		this.mem_address = mem_address;

	}

	public String getMem_height() {

		return mem_height;

	}

	public void setMem_height(String mem_height) {

		this.mem_height = mem_height;

	}

	public String getMem_weight() {

		return mem_weight;

	}

	public void setMem_weight(String mem_weight) {

		this.mem_weight = mem_weight;

	}


	public String getMem_pnum() {
		return mem_pnum;
	}


	public void setMem_pnum(String mem_pnum) {
		this.mem_pnum = mem_pnum;
	}

}
