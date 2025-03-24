package com.happygym;

import java.util.Scanner;

public class GymExe {
	static Scanner scn = new Scanner(System.in);
	static MemberJdbc dao = new MemberJdbc();

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

		Member Member = new Member();

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


//로그인 출력 메소드
	private static void handellogin() {

		while (true) {
			scn.nextLine();
			
			System.out.println("id >>");

			String id = scn.nextLine();

			System.out.println("pw >>");

			String pw = scn.nextLine();

			Member mem = Mlogin(id, pw);
			
			Teacher teacher = Tlogin(id,pw);
			
			if (mem != null) {

				System.out.println(mem.getMem_name() + "회원님, 환영합니다.");
				entrollStudentMenu();
				break;

			} else if(teacher != null) {
				System.out.println(teacher.getT_name()+"강사님, 환영합니다.");
				entrollTeacherMenu();
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

			break;

		case 3: // 회원등록

			enrollMember();

			break;

		default:

			System.out.println("잘못된 선택입니다.");

		}

	}

	public static void entrollStudentMenu() { //회원 메뉴판
		System.out.println("1.수강신청 2.과목목록 3.장바구니 4.신청내역 5.Q&A");
		System.out.println("===============================");
		System.out.println("선택>>");
		int menu=scn.nextInt();
		switch(menu){
		case 1:
			System.out.println("희망하는 수강과목 id 입력>>");
			int id= scn.nextInt();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		default:
			System.out.println("잘못된 선택입니다.");
		}

	}
	
	public static void entrollTeacherMenu() { //강사 메뉴
		System.out.println("1.수강과목등록 2.수업 조회 3.Q&A");
		System.out.println("===============================");
		System.out.println("선택>>");
		int menu=scn.nextInt();
	}

	// 강사모드

	public static void TeacherMenu() {
		System.out.println("1.강사등록  2.로그인");
		System.out.println("===============================");
		System.out.println("선택>>");
		int menu= scn.nextInt();
		if(menu == 2) {
			
			handellogin();
			
		} else {
			scn.nextLine();
			String t_id="";
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
			String hiredate=scn.nextLine();
			
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

	// 관리자 모드
	public static void AdminerMenu() {
		handellogin();
	}

	public static void main(String[] args) { //메인메소드

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
