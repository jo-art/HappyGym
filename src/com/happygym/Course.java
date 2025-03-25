package com.happygym;
/*
 * 수업정보를 담는 클래스 
 */
public class Course {
	private int courseId; // 과목아이디
	private String courseName; // 과목이름
	private Teacher teacher; // 과목 강사
	private String schedule; // 과목 일정
	private String time; //강좌 시간
	private String target; //이용대상
	private int capacity;//수용인원
	

	public Course(int courseId, String courseName, Teacher teacher, String schedule,String time,String target,int capacity) {
		this.courseId = courseId;
		this.courseName = courseName;
		 this.teacher = new Teacher(); // Teacher 객체 초기화
		this.schedule = schedule;
		this.time=time;
		this.target=target;
		this.capacity=capacity;

	}
	
//	@Override
//	public String toString() {
//		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", teacher=" + teacher + ", schedule="
//				+ schedule + ", time=" + time + ", target=" + target + ", capacity=" + capacity + "]";
//	}

	private void getInstance() {
		
	}
	//회원들에게 보여주는 프로그램 조회할 때 출력되는 메소드
	public String showListToM() {
		return courseId + "  "+courseName+" "+schedule+" "+schedule+time+" "+capacity +" "+teacher;
	}
	//관리자나 강사에게 보여주는 프로그램 조회시 출력되는 메소드
	//수업id       수업명     강사id     수업일정    인원     시간    대상
//	public String showListAll() {
//	    return courseId + "       " + courseName + "      " + teacher + "     " + schedule + " " + capacity + "  " + time + " " + target;
//	}
	public String showListAll() {
	    String teacherId = (teacher != null) ? teacher.getT_id() : "없음"; // 강사 ID null 체크
	    return String.format("%-5d %-15s %-10s %-15s %-10d %-15s %-10s", 
	                         courseId, courseName, teacherId, schedule, capacity, time, target);
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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
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
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", teacher=" + teacher + ", schedule="
				+ schedule + ", time=" + time + ", target=" + target + ", capacity=" + capacity + "]";
	}

}
