package com.kh.mybatis.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.mybatis.model.dto.MemberDTO;
import com.kh.mybatis.model.mapper.MemberMapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor // final 키워드가 붙은 필드를 매개변수로 가지는 생성자(롬복 쓸 때만 가능)
public class MemberController {

	// MemberMapper 주입 (생성자 주입 방식)
	private final MemberMapper mapper;
	/*
	 * 직접 생성자를 정의하는 방법 (롬복을 사용하지 않을 때) public MemberController(MemberMapper mapper)
	 * { this.mapper = mapper; }
	 */

	/**
	 * 회원 목록 조회 URL : [GET] /member/list Param: x 응답 : 회원 목록
	 * 페이지(WEB-INF/views/member/list.jsp) 포워딩
	 */
	@GetMapping("/list")
	public String memberList(Model model) {
		// DB에서 조회된 회원 목록을 request 영역에 저장 (key: memberList)
		List<MemberDTO> list = mapper.findAll();

		model.addAttribute("memberList", list);

		return "member/list";
	}

	/**
	 * 회원 추가 URL : [POST] /member/insert Param : name(String), email(String),
	 * age(int) 응답 : 회원목록페이지로 리다이렉트
	 */

	@PostMapping("/insert")
	public String memberInsert(
//    		   @requestParam(value="name",defaultValue="xx")String name 
			@ModelAttribute MemberDTO member, HttpSession session) {
		int result = mapper.insert(member);

		// 추가 성공시 "회원 가입 성공했습니다" 메시지를 저장
		// 실패 시 "회원 가입실패했습니다" 메시지를 저장
		// ==> 리다이렉트 처리 시 재요청 되므로 "session" 영역에 저장(HttpSession)
		if (result > 0) {
			session.setAttribute("message", "회원 가입 성공했습니다.");
		} else {
			session.setAttribute("message", "회원 가입 실패했습니다.");
		}

		return "redirect:/member/list";
	}

	/**
	 * 회원 가입 페이지 URL : [GET] /MEMBER/INSERT Param : X 응답 : 회원 가입
	 * 페이지(/WEB-INF/views/member/insertForm.jsp) 포워딩
	 */
	@GetMapping("/insert")
	public String memberInsertForm() {

		return "member/insertForm";
	}

	/**회원 정보 수정 페이지 (상세 조회 및 수정 폼 이동)
	 * URL : [GET] /member/update?id=1
	 * Param : id(Long)
	 * 응답 : 수정 폼 페이지(/WEB-INF/views/member/updateForm.jsp) 포워딩
	 */
	
	@GetMapping("/update") // <-- 이 부분이 빠져있었는지 확인해주세요!
	public String memberUpdateForm(@RequestParam("id") Long id, Model model) {
		// 1. ID로 수정할 회원의 기존 정보를 DB에서 콕 집어 조회
		MemberDTO member = mapper.findById(id);
		
		// 2. 조회한 데이터를 뷰(JSP)로 전달하기 위해 Model에 담기
		model.addAttribute("member", member);
		
		// 3. 수정 폼 페이지로 이동
		return "member/updateForm";
	}

	/**
	 * 회원 정보 수정 처리 (UPDATE)
	 * URL : [POST] /member/update
	 * Param : MemberDTO (id, name, email, age 등)
	 * 응답 : 회원 목록 페이지로 리다이렉트
	 */
	@PostMapping("/update")
	public String memberUpdate(@ModelAttribute MemberDTO member, HttpSession session) {
		// 1. DB에서 UPDATE 실행 (성공 시 1 이상의 값 반환)
		int result = mapper.update(member);
		
		// 2. 결과에 따라 세션에 메시지 저장
		if (result > 0) {
			session.setAttribute("message", "회원 정보가 수정되었습니다.");
		} else {
			session.setAttribute("message", "회원 정보 수정에 실패했습니다.");
		}
		
		// 3. 목록 페이지로 리다이렉트
		return "redirect:/member/list";
	}
		
	/**
	 * 회원 정보 삭제 처리 (DELETE)
	 * URL : [GET] /member/delete?id=1
	 * Param : id(Long)
	 * 응답 : 회원 목록 페이지로 리다이렉트
	 */
	@GetMapping("/delete")
	public String memberDelete(@RequestParam("id") Long id, HttpSession session) {
	    // 1. DB에서 DELETE 실행
	    int result = mapper.delete(id);
	    
	    // 2. 결과에 따라 세션에 메시지 저장
	    if (result > 0) {
	        session.setAttribute("message", "회원 정보가 삭제되었습니다.");
	    } else {
	        session.setAttribute("message", "회원 정보 삭제에 실패했습니다.");
	    }
	    
	    // 3. 목록 페이지로 리다이렉트
	    return "redirect:/member/list";
	}
}
