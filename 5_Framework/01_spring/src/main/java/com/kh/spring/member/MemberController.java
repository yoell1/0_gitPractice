package com.kh.spring.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.util.DBUtil;

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
     * 회원 등록 URL : [POST] /member/insert 
     * Parameter : age (나이) , email (이메일), name (이름) => MemberDTO로 한번에 받을 수 있음
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

	 /* 1. 수정 폼 화면 이동 (DB 조회 없이 hidden 데이터를 토스하여 화면만 열기) */
    @PostMapping("/updateForm") 
    public String updateForm(MemberDTO member, Model model) {
        // list.jsp에서 보낸 데이터가 member 객체에 자동으로 담깁니다.
        model.addAttribute("member", member);
        
        return "member/updateForm"; // views/member/updateForm.jsp 화면 오픈!
    }

    /* 2. 실제 DB 데이터 수정 처리 (updateForm.jsp에서 [수정 완료] 누르면 실행) */
    @PostMapping("/update") 
    public String update(MemberDTO member) {
        // 서비스단을 거쳐 실제 Oracle DB 데이터 변경 수행
        service.updateMember(member);
        
        // 수정 완료 후 회원 목록 조회 URL로 화면을 새로고침(리다이렉트)
        return "redirect:/member/list";
    }
}