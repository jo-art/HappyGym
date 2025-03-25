package com.happygym;

import java.sql.Timestamp;

public class Enrollments {
	private String  enrollment_id; //수강아이디
	private Member membmer; //학생 정보
	private Course course; //과목 정보
	private String status; //상태()
	private Timestamp enrollmentDate;//수강신청 등록날짜
}
