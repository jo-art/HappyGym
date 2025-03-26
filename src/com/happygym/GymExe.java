package com.happygym;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GymExe {
	static Scanner scn = new Scanner(System.in);
	static MemberJdbc dao = new MemberJdbc();
	static CourseManager Cdao = new CourseManager();
	static Course course = new Course();
	static Teacher teacher = new Teacher();

	static Member Member = new Member();
	static Cart cart = new Cart();
	static EnrollmentsJdbc Edao = new EnrollmentsJdbc();
	static CartJdbc Cartdao = new CartJdbc();
	private static String loggedInMemberId;

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
			System.out.println("회원등록 성공");
			handellogin();
		} else {
			System.out.println("회원등록실패");
		}

	}

	public static void enrollMembers() {
		scn.nextLine();
		String m_id = "";
		System.out.println("ID>>");
		m_id = scn.nextLine();

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
			System.out.println("회원등록 성공");

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
				loggedInMemberId = mem.getMem_id(); // 로그인한 회원 ID 저장
				entrollStudentMenu();
				break;

			} else if (teacher != null) {
				System.out.println(teacher.getT_name() + "강사님, 환영합니다.");
				entrollTeacherMenu();
				break;

			} else if (adminer != null) {
				System.out.println(adminer.getAdm_name() + "관리자님 환영합니다.");
				entrollAdminerMenu(scn);
				break;
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
			System.out.println("1.프로그램신청 2.프로그램조회 3.장바구니 4.신청내역 ");
			System.out.println("===============================");
			System.out.println("선택>>");
			menu = scn.nextInt();
			if (menu == 1 || menu == 3 || menu == 4) {
				System.out.println("로그인 후 사용가능합니다.");
				handellogin();
			}
			if (menu == 2) {
				detaillist();
			}
			break;

		case 3: // 회원등록

			enrollMember();

			break;

		default:

			System.out.println("잘못된 선택입니다.");

		}

	}

	public static void entrollStudentMenu() { // 회원 메뉴판

		int menu = 0;
		String ment = "";
		while (menu != 3) {
			System.out.println("1.프로그램신청  2.장바구니  3.종료");
			System.out.println("===============================");
			System.out.println("선택>>");
			menu = scn.nextInt();

			switch (menu) {
			case 1:
				list();
				scn.nextLine(); // 입력버퍼 비우기
				System.out.println("희망하는 프로그램 id 입력>>");
				System.out.println("===============================");
				int id = scn.nextInt();
				System.out.println("1.신청 2.신청취소 3.신청내역");
				System.out.println("===============================");
				System.out.println("선택>>");
				int action = scn.nextInt();
				if (action == 1) {

					Enrollments enrollments = new Enrollments();
					enrollments.setEnrollment_id(generateEnrollmentId()); // ID 생성 메서드 호출
					enrollments.setMemid(loggedInMemberId);
					enrollments.setCourseId(id); // Course 객체 설정
					enrollments.setMembmer(getCurrentMember()); // 현재 회원 정보 설정
					enrollments.setEnrollmentDate(new Timestamp(System.currentTimeMillis())); // 현재 시간 설정
					enrollments.setStatus("신청함"); // 상태 설정

					boolean result = Edao.addEnrollProgram(enrollments);
					if (result) {
						System.out.println("수강신청 완료");
						enrolllist();
					} else {
						System.out.println("수강신청 실패");
					}

				}
				if (action == 2) {
					enrolllist();
					scn.nextLine(); // 입력버퍼 비우기
					System.out.println("수강 취소할 프로그램 id 입력>>");
					System.out.println("===============================");
					id = scn.nextInt();
					Enrollments enrollments = new Enrollments();
					enrollments.setEnrollment_id(generateEnrollmentId()); // ID 생성 메서드 호출
					enrollments.setCourseId(id); // Course 객체 설정
					enrollments.setMembmer(getCurrentMember()); // 현재 회원 정보 설정
					enrollments.setEnrollmentDate(new Timestamp(System.currentTimeMillis())); // 현재 시간 설정
					enrollments.setStatus("신청취소"); // 상태 설정
					boolean result = Edao.deleteEnrollProgram(enrollments);
					if (result) {
						System.out.println("수강신청 취소 완료");

					} else {
						System.out.println("수강신청 취소 실패");
					}
				}
				if (action == 3) {
					enrolllist();
				}
				break;
			case 2:
				System.out.println("1.장바구니 2. 장바구니 추가 3장바구니 삭제");
				menu = scn.nextInt();
				// 장바구니 로직
				if (menu == 1) {
					viewtoCart();
				}
				if (menu == 2) {
					addtoCart();

				}

				if (menu == 3) {
					deleteCart();
				}
				break;
			case 3:
				System.out.println("메뉴를 종료합니다.");
				break;
			default:
				System.out.println("잘못된 선택입니다.");
			}
		}

	}

	// 강사모드

	private static void viewtoCart() {
		// CartJdbc 클래스를 통해 데이터베이스에서 장바구니 목록을 조회
		List<Cart> cartList = Cartdao.getCart(loggedInMemberId); // 로그인한 회원의 장바구니 목록 조회
		System.out.println("장바구니 목록:");

		if (cartList.isEmpty()) {
			System.out.println("장바구니가 비어 있습니다.");
			entrollStudentMenu();
		} else {
			for (Cart cart : cartList) {
				// 각 Cart 객체에서 수업 정보를 조회하여 출력
				Course course = Cdao.getCourseById(cart.getCourseId());// Course ID로 수업 정보 조회
				if (course != null) {
					System.out.println(course.showListAll()); // 수업 정보를 출력
				} else {
					System.out.println("수업 정보를 찾을 수 없습니다: " + cart.getCourseId());
				}
			}
		}
	}

	// 장바구니 출력 메소드
	private static void addtoCart() {
		// TODO Auto-generated method stub
		list(); // 장바구니 추가할 등록된 강좌 목록 출력
		scn.nextLine(); // 입력버퍼 비우기
		System.out.println("장바구니 추가할 프로그램 id 입력>>");
		System.out.println("===============================");
		int Cid = scn.nextInt(); // 프로그램 id 입력받기
		Cart cart = new Cart(); // Cart 인스턴스 생성
		cart.setCartid(generateCartId(loggedInMemberId, Cid));
		cart.setCourseId(Cid);
		cart.setMemId(loggedInMemberId);
		cart.setAddeddate(new Timestamp(System.currentTimeMillis())); // 현재 시간으로 설정
		if (Cartdao.addCart(cart)) {
			System.out.println("장바구니 추가 완료>");
			viewtoCart();
		} else {
			System.out.println("장바구니 추가 실패");
		}
	}

	private static String generateCartId(String memberId, int courseID) {
		return memberId + "_" + courseID; // 사용자 아이디와 프로그램 아이디를 합쳐 카트아이디 만들기..
	}

	// 장바구니 삭제 메소드
	private static void deleteCart() {
		viewtoCart();
		scn.nextLine(); // 입력버퍼 비우기
		System.out.println("장바구니에서 삭제할 프로그램 id 입력>>");
		System.out.println("===============================");
		int Corseid = scn.nextInt(); // 프로그램 id 입력받기
		Cart cart = new Cart(); // Cart 인스턴스 생성
		cart.setCartid(generateCartId(loggedInMemberId, Corseid));
		cart.setCourseId(Corseid);
		cart.setMemId(loggedInMemberId);
		cart.setAddeddate(new Timestamp(System.currentTimeMillis())); // 현재 시간으로 설정

		if (Cartdao.deleteCart(cart)) {
			System.out.println("프로그램 ID " + Corseid + "가 장바구니에서 삭제되었습니다.");
		} else {
			System.out.println("삭제할 프로그램 ID가 존재하지 않거나 삭제에 실패했습니다.");
		}
	}

	private static void enrolllist() {
		// 로그인한 회원의 수강신청 내역을 가져오는 메서드 호출
		List<Enrollments> list = Edao.getEnrollments(loggedInMemberId);
		int index = 1;
		System.out.println("수강신청 목록");
		if (list.isEmpty()) {
			System.out.println("신청한 내역이 없습니다.");
		} else {

			for (Enrollments enrollment : list) {
				System.out.println(index++ + " " + enrollment.showEnrollList());
			}
		}
	}

	private static String generateEnrollmentId() {
		long currentTimeMillis = System.currentTimeMillis(); // 현재 시간을 밀리초로 가져옴
		return "ENROLL_" + currentTimeMillis; // "ENROLL_" 접두사를 붙여서 ID 생성
	}

//현재 로그인한 회원 정보 가져오기.
	// 현재 로그인한 회원 정보를 가져오는 메서드
	private static Member getCurrentMember() {
		// 로그인한 회원의 ID를 전역 변수로 저장한다고 가정
		if (loggedInMemberId == null) {
			System.out.println("로그인 정보가 없습니다.");
			return null; // 로그인하지 않은 경우 null 반환
		}

		// 로그인한 회원 정보를 데이터베이스에서 조회
		Member member = dao.getMemberFromOjdbc(loggedInMemberId);
		return member; // 조회된 회원 정보 반환
	}

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
			System.out.println("1.프로그램관리 2.수업 조회  4.종료");
			System.out.println("===============================");
			System.out.println("선택>>");
			int menu = scn.nextInt();
			scn.nextLine();
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
				if (menu == 1) {
					list(); // 전체 목록 조회
				}
				if (menu == 2) {
					detaillist(); // 강사 아이디 강사명으로 조회,
				}
				// 수업조회로직
				break;

			case 4:
				System.out.println("프로그램을 종료합니다.");
				return; // 메서드 종료하여 프로그램 종료
			default:
				System.out.println("잘못된 선택입니다.");
			}
		}

	}

// 검색어 입력하면 자동으로 수강과목의 내용들 중에 하나를 찾아 출력하는 디테일리스트 
	private static void detaillist() {
		int seqNo = 1;
		scn.nextLine();
		System.out.println("검색>>>");
		String searchMent = scn.nextLine();
		String searchMentWithoutSpace = searchMent.replaceAll("\\s+", ""); // 모든 공백 제거

		if (searchMentWithoutSpace.isEmpty()) {
			System.out.println("검색어를 입력해주세요");
			return;
		}

		boolean found = false;
		try {
			List<Course> list = searchCourse(searchMentWithoutSpace);
			if (list.isEmpty()) {
				System.out.println("검색결과가 없습니다.");
				return;
			}

			System.out.println("순번   수업id       수업명     강사id      수업일정          인원         시간        대상");
			System.out.println("==================================================================================");
			for (Course course : list) {
				String output = course.showListAll();
				System.out.println(String.format("%d   %s", seqNo++, output));
				found = true;
			}
		} catch (Exception e) {
			System.out.println("검색 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	private static void list() { // 과목목록
		Course temp = null;
		int seqNo = 1;
		System.out.println("순번   수업id       수업명     강사id      수업일정          인원         시간        대상");
		System.out.println("==================================================================================");
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

	private static void memlist() {// 관리자에게 보여줄 회원목록
		List<Member> members = dao.MList();

		// 결과 출력
		for (Member member : members) {
			System.out.println(member.showMemList());
		}
	}

	private static void teacherList() { // 관리자에게 보여줄 강사목록
		List<Teacher> teachers = dao.TList();
		for (Teacher teacher : teachers) {
			System.out.println(teacher.showTeacherList());
		}
	}

	// 리스트와 리스트 키워드에서 활용할 공통 메소드
	private static List<Course> searchList(String keyword) {
		List<Course> list = Cdao.getAllList(keyword);
		return list;
	}

	private static List<Course> searchCourse(String keyword) {
		// Cdao의 selectCourses 메서드를 호출하여 강좌를 조회
		return Cdao.selectCourses(keyword);
	}

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
		course.setTid(tid);
		course.setTime(time);
		course.setSchedule(schedule);
		course.setCapacity(Integer.parseInt(capacity));
		course.setTarget(target);

		if (Cdao.updateCourse(course)) {
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
		handellogin();
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
			System.out.println("관리자 등록 성공!");
			// 관리자 등록 후의 로직
		} else {
			System.out.println("관리자 등록 실패");
		}
	}

	private static void entrollAdminerMenu(Scanner scn) {
	    boolean run = true;
	    while (run) {
	        System.out.println("1.회원관리  2.프로그램관리 3.강사관리 4.관리자등록 5.메인메뉴로 돌아가기");
	        System.out.println("===============================");
	        System.out.print("선택>> ");

	        while (true) {
	            try {
	                int menu = Integer.parseInt(scn.nextLine().trim()); // 입력받은 문자열의 앞뒤 공백 제거
	                switch (menu) {
	                    case 1: // 회원관리
	                        memlist();
	                        System.out.println("1.수정 2.삭제 3.등록");
	                        System.out.println("===============================");
	                        System.out.print("선택>> ");
	                        int action = Integer.parseInt(scn.nextLine().trim());

	                        if (action == 1) {
	                            System.out.print("수정할 회원의 id 입력>> ");
	                            String memId = scn.nextLine();

	                            System.out.print("기존 회원의 id와 pw 확인(기존값과 동일해야합니다!)");
	                            System.out.print(" id>> ");
	                            memId = scn.nextLine(); // 수정할 id를 다시 입력받는 부분
	                            System.out.print(" pw>> ");
	                            String memPW = scn.nextLine();
	                            System.out.print(" 이름>> ");
	                            String memName = scn.nextLine();
	                            System.out.print(" 나이>> ");
	                            int age = Integer.parseInt(scn.nextLine().trim()); // 나이 입력
	                            System.out.print(" 번호>> ");
	                            String memPNUM = scn.nextLine();
	                            System.out.print(" 주소>> ");
	                            String memAddr = scn.nextLine();
	                            System.out.print(" 키>> ");
	                            String height = scn.nextLine();
	                            System.out.print(" 몸무게>> ");
	                            String weight = scn.nextLine();

	                            Member member = new Member();
	                            member.setMem_id(memId);
	                            member.setMem_pw(memPW);
	                            member.setMem_name(memName);
	                            member.setMem_age(age);
	                            member.setMem_pnum(memPNUM);
	                            member.setMem_address(memAddr);
	                            member.setMem_height(height);
	                            member.setMem_weight(weight);

	                            if (dao.updateMemberInfo(member)) {
	                                System.out.println("수정완료");
	                            } else {
	                                System.out.println("수정 실패");
	                            }
	                        } else if (action == 2) {
	                            System.out.print("삭제할 회원의 id 입력>> ");
	                            String memId = scn.nextLine();
	                            Member member = new Member();
	                            member.setMem_id(memId);
	                            if (dao.deleteMemberInfo(member)) { // 삭제 메서드 호출
	                                System.out.println("삭제완료");
	                            } else {
	                                System.out.println("삭제 실패");
	                            }
	                        } else if (action == 3) {
	                            enrollMembers();
	                        } else {
	                            System.out.println("잘못된 선택입니다.");
	                        }
	                        break;
	                    case 2: // 프로그램관리
	                        System.out.println("1.등록 2.수정 3.삭제");
	                        System.out.println("===============================");
	                        System.out.print("선택>> ");
	                        menu = Integer.parseInt(scn.nextLine().trim());

	                        if (menu == 1) {
	                            registerCourse(); // 프로그램 등록 메소드 호출
	                        } else if (menu == 2) {
	                            editCourse(); // 프로그램 수정 메소드 호출
	                        } else if (menu == 3) {
	                            delteCourse(); // 프로그램 삭제 메소드 호출
	                        } else {
	                            System.out.println("잘못된 선택입니다.");
	                        }
	                        break;
	                    case 3: // 강사관리
	                        System.out.println("1.등록  2.수정   3.삭제");
	                        System.out.println("===============================");
	                        System.out.print("선택>> ");
	                        menu = Integer.parseInt(scn.nextLine().trim());

	                        if (menu == 1) {
	                            String t_id = "";
	                            while (true) {
	                                System.out.print("ID>> ");
	                                t_id = scn.nextLine();
	                                if (dao.isIdExists(t_id)) {
	                                    System.out.println("이미 존재하는 아이디 입니다. 다른 아이디 입력해주세요");
	                                } else {
	                                    System.out.println("사용가능한 아이디입니다." + t_id);
	                                    break;
	                                }
	                            }

	                            System.out.print("PW>> ");
	                            String t_pw = scn.nextLine();
	                            System.out.print("이름>> ");
	                            String t_name = scn.nextLine();
	                            System.out.print("전화번호>> ");
	                            String t_pnum = scn.nextLine();
	                            System.out.print("고용날짜(yyyy-mm-dd)>> ");
	                            String hiredate = scn.nextLine();

	                            Teacher teacher = new Teacher();
	                            teacher.setT_id(t_id);
	                            teacher.setT_pw(t_pw);
	                            teacher.setT_name(t_name);
	                            teacher.setT_Pnum(t_pnum);
	                            teacher.setHire_date(hiredate);
	                            if (dao.Teacherinsert(teacher)) {
	                                System.out.println("강사등록성공");
	                            } else {
	                                System.out.println("강사등록실패");
	                            }
	                        } else if (menu == 2) {
	                            teacherList();
	                            System.out.println("수정할 강사의 ID와 PW를 확인하세요 (기존값 입력)");
	                            System.out.print("ID와 PW를 입력하세요 (형식: ID,PW)>> ");

	                            String input = scn.nextLine();
	                            String[] credentials = input.split(",");

	                            // 입력 형식 검증
	                            if (credentials.length != 2) {
	                                System.out.println("ID와 PW를 올바른 형식으로 입력하세요. (형식: ID,PW)");
	                                return; // 또는 적절한 오류 처리
	                            }

	                            String tid = credentials[0].trim();
	                            String tpw = credentials[1].trim();

	                            // 추가적인 입력 받기
	                            System.out.print("이름>> ");
	                            String tname = scn.nextLine();
	                            System.out.print("전화번호>> ");
	                            String tpnum = scn.nextLine();
	                            System.out.print("고용날짜(yyyy-mm-dd)>> ");
	                            String hiredate = scn.nextLine();

	                            Teacher teacher = new Teacher();
	                            teacher.setT_id(tid);
	                            teacher.setT_pw(tpw);
	                            teacher.setT_name(tname);
	                            teacher.setT_Pnum(tpnum);
	                            teacher.setHire_date(hiredate);

	                            // 수정 처리
	                            if (dao.updateTeacherInfo(teacher)) {
	                                System.out.println("수정완료");
	                            } else {
	                                System.out.println("수정실패");
	                            }
	                        } else if (menu == 3) {
	                            System.out.print("삭제할 강사의 id 입력>> ");
	                            String teacherId = scn.nextLine();
	                            Teacher teacher = new Teacher();
	                            teacher.setT_id(teacherId);
	                            if (dao.deleteTeacherInfo(teacher)) { // 삭제 메서드 호출
	                                System.out.println("삭제완료");
	                            } else {
	                                System.out.println("삭제 실패");
	                            }
	                        } else {
	                            System.out.println("잘못된 선택입니다.");
	                        }
	                        break;
	                    case 4: // 관리자 등록
	                        AdminerMenu();
	                        break;
	                    case 5:
	                        run = false;
	                        break;
	                    default:
	                        System.out.println("잘못선택하셨습니다.");
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("정수/숫자를 입력해야합니다.");
	                
	            }
	        }
	    }
	}


	

	public static void main(String[] args) { // 모드 선택

		// TODO Auto-generated method stub

		boolean run = true;

		while (run) {

			System.out.println("1.수강생	2.강사	3.관리자 0.종료");

			System.out.println("===============================");

			System.out.println("선택>>");
			try {
				int role = scn.nextInt();

				switch (role) {

				case 1:// 수강생로직

					StudentMenu();

					break;

				case 2:// 강사로직

					TeacherMenu();

					break;

				case 3: // 관리자
					entrollAdminerMenu(scn);
					break;
				case 0:
					run = false;
					System.out.println("프로그램을 종료합니다.");
				default:

					System.out.println("잘못된 선택입니다.");
				}
			} catch (Exception e) {
				System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
				scn.next(); // 잘못된 입력을 처리하고 Scanner를 다음 입력으로 이동
			}

		}
		scn.close();
	}

}
