package com.kh.spring.member;

import java.util.List;

import org.springframework.stereotype.Service;

@Service // @Conponent + 이 클래스가 비즈니스 로직 계층임을 나타냄.
public class MemberService {

	// (DI,의존성주입) MemberDAO -> 생성자 주입방식 : TODO:
	private final MemberDAO dao;

	public MemberService(MemberDAO dao) {
		this.dao = dao;
	}

	/*
	 * 1. 회원 목록 조회 로직 컨트롤러의 list() 메서드와 연결됩니다. -> DB에서 조회된 결과(List)를 반환
	 */
	public List<MemberDTO> getMemberList() {
		// [TODO] 나중에 DAO(Repository)를 연결해서 DB 데이터를 가져올 예정입니다.
		// 현재는 임시로 null을 반환하거나 비어있는 리스트를 반환해 둡니다.
		return dao.findAll();
	}

	/*
	 * 2. 회원 등록 로직 컨트롤러의 insert() 메서드와 연결됩니다. -> 회원 정보(DTO)를 전달받아서 DB에 추가
	 */
	public void insertMember(MemberDTO member) {
		// [TODO] 나중에 DAO를 통해 DB에 INSERT 쿼리를 실행할 예정입니다.
		// 매개변수로 전달받은 memberDto를 활용합니다.
		dao.insert(member);
	}

	/*
	 * 3. 회원 삭제 로직 컨트롤러의 delete() 메서드와 연결됩니다. -> 회원 번호(id)를 전달 받아서 DB에서 삭제
	 */
	public void deleteMember(int id) {
		// [TODO] 나중에 DAO를 통해 DB에 DELETE 쿼리를 실행할 예정입니다.
		// 매개변수로 전달받은 id를 활용합니다.
		dao.delete(id);
	}

	// 회원 정보 수정
	// -> 수정할 회원 정보(DTO)를 전달 받아서
	// 회원 번호를 기준으로 이름.이메일,나이를 DB에서 변경

	public void updateMember(MemberDTO member) {
		// 컨트롤러에게 넘겨받은 member 객체를 DAO의 update 메서드로 전달
		dao.update(member);
	}

	public MemberDTO getMember(int id) {

		return dao.findById(id);
	}
}
