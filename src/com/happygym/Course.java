package com.happygym;
/*
 * 수업정보를 담는 클래스 
 */
public class Course {
	private int courseId; // 과목아이디
	private String courseName; // 과목이름
	private String teacher; // 과목 강사
	private String schedule; // 과목 일정
	private String time; //강좌 시간
	private String target; //이용대상

	public Course(int courseId, String courseName, String teacher, String schedule,String time,String target) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.teacher=teacher;
		this.schedule = schedule;
		this.time=time;
		this.target=target;

	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
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
