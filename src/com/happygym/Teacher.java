package com.happygym;

public class Teacher extends Member{
	
	private String t_id;

	private String t_pw;

	private String t_name;

	private int t_age;

	private String t_Pnum;
	private String hire_date;

	// 기본생성자

	public Teacher() {
	}
	public Teacher(String t_id,String t_pw, String t_name) {
		this.t_id=t_id;
		this.t_pw=t_pw;
		this.t_name=t_name;
	}

	public Teacher(String t_id, String t_pw, String t_name, int t_age, String t_Pnum,String hiredate) {

		this.t_id = t_id;

		this.t_pw = t_pw;

		this.t_name = t_name;

		this.t_age = t_age;

		this.t_Pnum= t_Pnum;
		this.hire_date=hiredate;

	}
	
	//메소드 교수가 과목등록하는 메소드
	public void addCourse() {
		
	}

	// getter,setter

	@Override
	public String toString() {
		return "Teacher [t_id=" + t_id + ", t_pw=" + t_pw + ", t_name=" + t_name + ", t_age=" + t_age + ", t_Pnum="
				+ t_Pnum + ", hire_date=" + hire_date + "]";
	}
	public String getT_id() {

		return t_id;

	}

	public void setT_id(String t_id) {

		this.t_id = t_id;

	}

	public String getT_pw() {

		return t_pw;

	}

	public void setT_pw(String t_pw) {

		this.t_pw = t_pw;

	}

	public String getT_name() {

		return t_name;

	}

	public void setT_name(String t_name) {

		this.t_name = t_name;

	}

	public int getT_age() {

		return t_age;

	}

	public void setT_age(int t_age) {

		this.t_age = t_age;

	}


	public String getT_Pnum() {
		return t_Pnum;
	}
	public void setT_Pnum(String t_Pnum) {
		this.t_Pnum = t_Pnum;
	}
	public String getHire_date() {
		return hire_date;
	}
	public void setHire_date(String hire_date) {
		this.hire_date = hire_date;
	}
	
}
