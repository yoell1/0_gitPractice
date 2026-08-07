package com.kh.spring.member;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  컨트롤러
 */
@Controller // @Conmponent + 컨트롤러 계층의 기능이 추가된 어노테이션
			// -> 이 클래스의 메소드가 반환하는 문자열은 "View"의 이름으로 해석됨(포워드)
@RequestMapping("/member") // 클래스 레벨의 공통 URL 지정
							// -> 내부 메소드들은 매핑 URL 앞에 "/member" 가 붙음.
public class MemberController {

	// MemberService 클래스를 주입(생성자 주입방식)

	private final MemberService service;
	// @Autowired 생략가능

	public MemberController(MemberService service) {
		this.service = service;
	}

	/*
	 * 회원 목록 조회 URL : [GET] /member/list
	 */
	@GetMapping("/list")
	public String memberlist(Model model) {
		List<MemberDTO> list = service.getMemberList();

		// 조회된 결과 (list)를 request 영역에 저장 (key:memberList)
		model.addAttribute("memberlist", list);
		return "member/list"; // => /WEB-INF/views/member/list.jsp
	}

	/*
	 * 회원 등록 URL : [POST] /member/insert Parameter : age (나이) , email (이메일), name
	 * (이름) => MemberDTO로 한번에 받을 수 있음
	 */
	/*
	 * 회원 등록 URL : [POST] /member/insert Parameter : age (나이) , email (이메일), name
	 * (이름) => MemberDTO로 한번에 받을 수 있음
	 */
	@PostMapping("/insert")
	public String insert(MemberDTO member) {

		// 1. [수정] 직접 요리하던 JDBC 코드를 다 지우고, 서비스에게 데이터 저장을 위임합니다.
		service.insertMember(member);

		// 2. 저장이 끝나면 회원 목록 화면으로 리다이렉트합니다.
		return "redirect:/member/list";
	}

	/*
	 * 회원 삭제 URL : [GET] /member/delete/{id}
	 */

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id) {

		service.deleteMember(id);

		return "redirect:/member/list"; // TODO:

	}

	/**
	 * 회원 수정--> U ()UpDate URL : [POST] /member/update 요청 파라미터 : {id: 회원번호, name :
	 * 이름, email: 이메일, age: 나이} --->MemberDTO
	 */

	@PostMapping("/update")
	public String update(MemberDTO member) {
		// 서비스로 수겅 요청
		service.updateMember(member);
		return "redirect:/member/list";
	}

	/**
	 * 회원 수정 페이지 응답 [GET] /member/update/회원번호
	 */

	@GetMapping("/update/{id}")
	public String updateForm(@PathVariable("id") int id, Model model) {
		// 회원 번호를 기준으로 회원 정보를 조회
		MemberDTO member = service.getMember(id);
		// request 영역에 회원 정보 저장
		model.addAttribute("member", member);
		return "/member/updateForm";

	}
}
