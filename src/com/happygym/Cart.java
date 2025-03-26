package com.happygym;

import java.sql.Timestamp;

public class Cart {
	private int cartid;
	private String memId;
	private int courseId;
	private Timestamp addeddate;
	public Cart() {
		
	}
	public Cart(int cartid, String memId,int courseId,Timestamp addeddate) {
		this.cartid =cartid;
		this.memId=memId;
		this.courseId=courseId;
		this.addeddate = addeddate;
	}
	public int getCartid() {
		return cartid;
	}
	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public Timestamp getAddeddate() {
		return addeddate;
	}
	public void setAddeddate(Timestamp addeddate) {
		this.addeddate = addeddate;
	}
	
}
