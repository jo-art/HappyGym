package com.happygym;
/*
 * 수업정보를 담는 클래스 
 */
public class Course {
	private int courseId; // 과목아이디
	private String courseName; // 과목이름
	private String teacher; // 과목 강사
	private String schedule; // 과목 일정

	
	public Course(int courseId, String courseName, String teacher, String schedule) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.schedule = schedule;

	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public Course() {

	}

}
