package com.happygym;

import java.util.List;
import java.util.Scanner;

public class GymExe {
	static Scanner scn = new Scanner(System.in);
	static MemberJdbc dao = new MemberJdbc();
	static CourseManager Cdao = new CourseManager();
	static Course course = new Course();
	static Teacher teacher = new Teacher();
	static Member Member = new Member();

	// 회원등록리스트 출력 메소드
	public static void enrollMember() {
		scn.nextLine();
		String m_id = "";
		while (true) {
			System.out.println("ID>>");
			m_id = scn.nextLine();
			if (dao.isIdExists(m_id)) {
				System.out.println("이미 존재하는 아이디 입니다. 다른 아이디 입력해주세요");
			} else {
				System.out.println("사용가능한 아이디입니다." + m_id);

				break;
			}

		}

		System.out.println("PW>>");

		String m_pw = scn.nextLine();

		System.out.println("이름>>");

		String m_name = scn.nextLine();

		System.out.println("나이>>");

		String m_age = scn.nextLine();

		System.out.println("전화번호>>");
		String m_pnum = scn.nextLine();

		System.out.println("주소>>");

		String m_addr = scn.nextLine();

		System.out.println("키>>");

		String m_height = scn.nextLine();

		System.out.println("몸무게>>");

		String m_weight = scn.nextLine();

		Member.setMem_id(m_id);
		Member.setMem_pw(m_pw);
		Member.setMem_name(m_name);
		Member.setMem_age(Integer.parseInt(m_age));
		Member.setMem_pnum(m_pnum);
		Member.setMem_address(m_addr);
		Member.setMem_height(m_height);
		Member.setMem_weight(m_weight);
		if (dao.insert(Member)) {
			handellogin();
		} else {
			System.out.println("회원등록실패");
		}

	}

//로그인 ojdbc 연결

	private static Member Mlogin(String id, String pw) {

		return dao.mlogin(id, pw);

	}

	private static Teacher Tlogin(String id, String pw) {
		return dao.tlogin(id, pw);
	}

	private static Adminer Alogin(String id, String pw) {
		return dao.Alogin(id, pw);
	}

//로그인 출력 메소드
	private static void handellogin() {

		while (true) {
			scn.nextLine();

			System.out.println("id >>");

			String id = scn.nextLine();

			System.out.println("pw >>");

			String pw = scn.nextLine();

			Member mem = Mlogin(id, pw);

			Teacher teacher = Tlogin(id, pw);

			Adminer adminer = Alogin(id, pw);

			if (mem != null) {

				System.out.println(mem.getMem_name() + "회원님, 환영합니다.");
				entrollStudentMenu();
				break;

			} else if (teacher != null) {
				System.out.println(teacher.getT_name() + "강사님, 환영합니다.");
				entrollTeacherMenu();
				break;
			} else if (adminer != null) {
				System.out.println(adminer.getAdm_name() + "관리자님 환영합니다.");
				entrollAdminerMenu();
			}

			System.out.println("로그인 실패! 다시 시도해주세요.");

		}

	}

	// 수강생모드 메뉴 출력 메소드

	public static void StudentMenu() {

		System.out.println("1.로그인   2.비회원    3.회원등록");

		System.out.println("===============================");

		System.out.println("선택>>");

		int menu = scn.nextInt();

		switch (menu) {

		case 1:// 기존회원 로그인

			handellogin();
			break;

		case 2:// 비회원 모드

			break;

		case 3: // 회원등록

			enrollMember();

			break;

		default:

			System.out.println("잘못된 선택입니다.");

		}

	}

	public static void entrollStudentMenu() { // 회원 메뉴판
		System.out.println("1.프로그램신청 2.프로그램조회 3.장바구니 4.신청내역 5.Q&A");
		System.out.println("===============================");
		System.out.println("선택>>");
		int menu = scn.nextInt();
		switch (menu) {
		case 1:
			System.out.println("희망하는 프로그램 id 입력>>");
			System.out.println("===============================");
			String id = scn.nextLine();
			System.out.println("1.등록 2.장바구니 넣기");
			System.out.println("===============================");
			System.out.println("선택>>");
			menu = scn.nextInt();
			if (menu == 1) {
				System.out.println("정말 등록하시겠습니까?(네/아니오)");
				// 수강신청 로직
			} else if (menu == 2) {
				System.out.println("해당과목을 장바구니에 넣었습니다.");
				// 장바구니 넣기 로직
			}

			break;
		case 2:
			// 강사가등록한 과목 목록 전체보기
			// 강사명 검색, 주말반/주중반으로 검색
			break;
		case 3:
			// 장바구니 목록 보여주기
			// 장바구니 삭제
			// 등록 메뉴
			break;
		case 4:
			// 수강신청 내역 목록 보여주기 , 과목
			break;
		case 5:
			break;
		default:
			System.out.println("잘못된 선택입니다.");
		}

	}

	// 강사모드

	public static void TeacherMenu() {
		System.out.println("1.강사등록  2.로그인");
		System.out.println("===============================");
		System.out.println("선택>>");
		int menu = scn.nextInt();
		if (menu == 2) {

			handellogin();

		} else {
			scn.nextLine();
			String t_id = "";
			while (true) {
				System.out.println("ID>>");
				t_id = scn.nextLine();
				if (dao.isIdExists(t_id)) {
					System.out.println("이미 존재하는 아이디 입니다. 다른 아이디 입력해주세요");
				} else {
					System.out.println("사용가능한 아이디입니다." + t_id);

					break;
				}

			}

			System.out.println("PW>>");
			String t_pw = scn.nextLine();
			System.out.println("이름>>");
			String t_name = scn.nextLine();
			System.out.println("전화번호>>");
			String t_pnum = scn.nextLine();
			System.out.println("고용날짜(yyyy-mm-dd)>>");
			String hiredate = scn.nextLine();

			Teacher teacher = new Teacher();
			teacher.setT_id(t_id);
			teacher.setT_pw(t_pw);
			teacher.setT_name(t_name);
			teacher.setT_Pnum(t_pnum);
			teacher.setHire_date(hiredate);
			if (dao.Teacherinsert(teacher)) {
				handellogin();
			} else {
				System.out.println("강사등록실패");
			}

		}

	}

	public static void entrollTeacherMenu() { // 등록된 강사의 메뉴
		while (true) {
			System.out.println("1.프로그램관리 2.수업 조회 3.Q&A 4.종료");
			System.out.println("===============================");
			System.out.println("선택>>");
			int menu = scn.nextInt();
			switch (menu) {
			case 1:
				System.out.println("1.등록 2.수정 3.삭제");
				System.out.println("===============================");
				System.out.println("선택>>");
				menu = scn.nextInt();

				if (menu == 1) {
					registerCourse(); // 프로그램 등록 메소드 호출
				}
				if (menu == 2) {
					editCourse(); // 프로그램 수정 메소드 호출
				}
				if (menu == 3) {
					delteCourse(); // 프로그램 삭제 메소드 호출
				}

				break;
			case 2:
				System.out.println("1.목록  2.상세조회");
				System.out.println("===============================");
				System.out.println("선택>>");
				menu = scn.nextInt();
				if(menu == 1) {
					list(); //전체 목록 조회
				}
				if(menu ==2) {
					detaillist(); // 강사 아이디 강사명으로 조회,
				}
				//수업조회로직
				break;
			case 3:
				//Q&A
				break;
			case 4:
				  System.out.println("프로그램을 종료합니다.");
	                return; // 메서드 종료하여 프로그램 종료
			default:
				System.out.println("잘못된 선택입니다.");
			}
		}

	}
//디테일 리스트 다시하기!///////////////////
	private static void detaillist() {
		// TODO Auto-generated method stub
		Course temp = null;
	    int seqNo = 1;
		System.out.println("검색>>");
		String searchment = scn.next();
		if(searchment.isBlank()) {
			System.out.println("검색어를 입력하세요");
		}
		if(course.getCourseName().equals(searchment)) {
			System.out.println(course.getCourseName()+" 강사님의 프로그램");
		    System.out.println("===============================================================");
		    System.out.println("순번   수업id       수업명     강사id     수업일정    인원     시간    대상");
		    System.out.println("===============================================================");
		    List<Course> list = searchList(searchment);
		    for(Course course:list) {
		    	if(course!=null) {
		    		
		    	}
		    }
		}
	}

	private static void list() {
	    Course temp = null;
	    int seqNo = 1;
	    System.out.println("순번   수업id       수업명     강사id     수업일정    인원     시간    대상");
	    System.out.println("===============================================================");
	    
	    List<Course> list = searchList(""); // 전체 리스트 조회
	    for (Course course : list) {
	        if (course != null) {
	            String output = course.showListAll();
	            System.out.println(seqNo++ + " " + output);
	        } else {
	            System.out.println(seqNo++ + " null course"); // null 처리
	        }
	    }
	}

	//리스트와 리스트 키워드에서 활용할 공통 메소드
	private static List<Course> searchList(String keyword){
		List<Course> list = Cdao.getAllList(keyword);
		return list;
	}
//	private static Course searchCourse(String ) {
//		return Cdao.selectCourseName(courseName);
//	}


	private static void delteCourse() {// 프로그램 삭제 메소드
		// TODO Auto-generated method stub
		scn.nextLine();
		String courseId = "";
		while (true) {
			System.out.println("프로그램 아이디:"); // 프로그램 아이디로 검색해서 삭제함
			courseId = scn.nextLine();
			if (!courseId.isBlank()) {
				break;
			}
			System.out.println("프로그램 아이디 반드시 입력해야합니다.");
		}
		if (Cdao.deleteCourse(courseId)) {
			System.out.println("삭제완료");
		} else {
			System.out.println("삭제실패");
		}

	}

	private static void editCourse() { // 프로그램 수정 메소드
		// TODO Auto-generated method stub
		scn.nextLine();
		System.out.println("프로그램 아이디:"); // 프로그램 아이디로 검색해서 수정함
		String courseId = scn.nextLine();
		if (courseId.isBlank()) {
			System.out.println("프로그램 아이디 반드시 입력하세요");
			return;
		}
		System.out.println("프로그램명:");
		String courseName = scn.nextLine();
		System.out.println("강사 ID:");
		String tid = scn.nextLine();
		System.out.println("시간:");
		String time = scn.nextLine();
		System.out.println("이용일:");
		String schedule = scn.nextLine();
		System.out.println("최대 수용 인원수:");
		String capacity = scn.nextLine();
		System.out.println("대상:");
		String target = scn.nextLine();

		course.setCourseId(Integer.parseInt(courseId));
		course.setCourseName(courseName);
		System.out.println("강사 ID: " + tid); // 입력받은 강사 ID 출력
		teacher.setT_id(tid);
		course.setTime(time);
		course.setSchedule(schedule);
		course.setCapacity(Integer.parseInt(capacity));
		course.setTarget(target);

		if (Cdao.updateCourse(teacher, course)) {
			System.out.println("프로그램 수정 성공");
			System.out.println("수정된 프로그램 정보");
			System.out.println("수업id       수업명     강사id     수업일정    인원     시간    대상");
			System.out.println(course.showListAll());

		} else {
			System.out.println("프로그램 수정 예외");
		}

	}

	public static void registerCourse() {// 프로그램등록 메소드
		scn.nextLine();
		System.out.println("프로그램 아이디:");
		String courseId = scn.nextLine();
		System.out.println("프로그램명:");
		String courseName = scn.nextLine();
		System.out.println("강사 ID:");
		String tid = scn.nextLine();
		System.out.println("시간:");
		String time = scn.nextLine();
		System.out.println("이용일:");
		String schedule = scn.nextLine();
		System.out.println("최대 수용 인원수:");
		String capacity = scn.nextLine();
		System.out.println("대상:");
		String target = scn.nextLine();

		// 입력 항목 확인
		if (courseId.isBlank() || courseName.isBlank() || tid.isBlank()) {
			System.out.println("항목을 반드시 입력하세요");
			return;
		}
		try {
			course.setCourseId(Integer.parseInt(courseId));
			course.setCourseName(courseName);
			System.out.println("강사 ID: " + tid); // 입력받은 강사 ID 출력
			teacher.setT_id(tid);
			course.setTime(time);
			course.setSchedule(schedule);
			course.setCapacity(Integer.parseInt(capacity));
			course.setTarget(target);
		} catch (NumberFormatException e) {
			System.err.println("입력값이 올바르지 않습니다. 숫자 값을 확인하세요.");
			return; // 메서드 종료
		}

		if (Cdao.registerCourse(teacher, course)) {
			System.out.println("프로그램 등록 성공");
			System.out.println("등록된 프로그램 정보");
			System.out.println("수업id       수업명     강사id     수업일정    인원     시간    대상");
			System.out.println(course.showListAll());
		} else {
			System.out.println("프로그램 등록 예외");
		}

	}

	// 관리자 모드
	public static void AdminerMenu() {
		scn.nextLine();
		String adminId = "";

		while (true) {
			System.out.println("ID>>");
			adminId = scn.nextLine();
			if (dao.isIdExists(adminId)) {
				System.out.println("이미 존재하는 아이디 입니다. 다른 아이디 입력해주세요");
			} else {
				System.out.println("사용가능한 아이디입니다." + adminId);

				break;
			}
		}
		System.out.println("PW>>");
		String adminPw = scn.nextLine();
		System.out.println("이름>>");
		String adminName = scn.nextLine();

		Adminer adminer = new Adminer();
		adminer.setAdm_id(adminId);
		adminer.setAdm_pw(adminPw);
		adminer.setAdm_name(adminName);
		if (dao.adminerinsert(adminer)) {
			handellogin();
		} else {
			System.out.println("관리자등록 실패");
		}

	}

	private static void entrollAdminerMenu() {
		// TODO Auto-generated method stub
		boolean run = true;
		while (run) {
			System.out.println("1.회원관리  2.프로그램관리 3.강사관리 7.종료");
			System.out.println("===============================");
			System.out.println("선택>>");
			int menu = 7;
			while (true) {
				try {
					menu = Integer.parseInt(scn.nextLine());
					break;
				} catch (NumberFormatException e) {
					System.out.println("정수/숫자를 입력해야합니다");
				}

			}
			switch (menu) {
			case 1:// 회원관리
				break;
			case 2: // 수업관리
				break;
			case 3: // 강사관리
				break;
			case 7: // 종료
				run = false;
			default:
				System.out.println("잘못선택하셨습니다.");
			}
		}
	}

	public static void main(String[] args) { // 모드 선택

		// TODO Auto-generated method stub

		boolean run = true;

		while (run) {

			System.out.println("1.수강생	2.강사	3.관리자");

			System.out.println("===============================");

			System.out.println("선택>>");

			int role = scn.nextInt();

			switch (role) {

			case 1:// 수강생로직

				StudentMenu();

				break;

			case 2:// 강사로직

				TeacherMenu();

				break;

			case 3: // 관리자
				AdminerMenu();
				break;

			default:

				System.out.println("잘못된 선택입니다.");

			}

		}

	}

}
