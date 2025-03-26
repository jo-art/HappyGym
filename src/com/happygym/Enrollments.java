package com.happygym;

import java.sql.Timestamp;

public class Enrollments {
	private String  enrollmentid; //수강아이디
	private String memid; // 회원 아이디
	private int courseId; // 과목아이디
	private String status; //상태()
	private Timestamp enrollmentDate;//수강신청 등록날짜
	
	private Member member; //학생 정보
	private Course course; //과목 정보
	
	
	
	

	@Override
	public String toString() {
		return "Enrollments [enrollmentid=" + enrollmentid + ", memid=" + memid + ", courseId=" + courseId + ", status="
				+ status + ", enrollmentDate=" + enrollmentDate + "]";
	}
	//출력 메소드
	public String showEnrollList() {
	    return "ID: " + enrollmentid + ", 과정 ID: " + courseId + ", 상태: " + status + ", 신청일: " + enrollmentDate;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	//기본생성자
	public Enrollments() {
		
	}
	
	public Enrollments(String enrollmentid,Member member,Course course,String status) {
		this.enrollmentid= enrollmentid;
		this.member=member;
		this.course=course;
		this.status=status;
	}
	public Enrollments(String enrollmentid,String memid,int  courseid,String status, Timestamp enrollmentdate) {
		this.enrollmentid= enrollmentid;
		this.memid=memid;
		this.courseId= courseid;
		this.status=status;
		this.enrollmentDate=new Timestamp(System.currentTimeMillis()); //현재시간으로 설정
	}
	public String getMemid() {
		return memid;
	}

	public void setMemid(String memid) {
		this.memid = memid;
	}
	public String getEnrollmentid() {
		return enrollmentid;
	}

	public void setEnrollment_id(String enrollmentid) {
		this.enrollmentid = enrollmentid;
	}

	public Member getMembmer() {
		return member;
	}

	public void setMembmer(Member member) {
		this.member = member;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(Timestamp enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	
}
