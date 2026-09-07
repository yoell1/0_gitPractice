package com.kh.bowling.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.bowling.model.BillingDTO;
import com.kh.bowling.model.MemberDTO;
import com.kh.bowling.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    // 현재 이용중 회원 목록
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", service.getActiveList());
        return "list";
    }

    // 등록 폼 화면
    @GetMapping("/insert")
    public String insertForm() {
        return "insert";
    }

    // 신규 등록 처리
    @PostMapping("/insert")
    public String insert(@RequestParam String name, @RequestParam int laneNumber,
                          @RequestParam int gameCount, @RequestParam String grade) {
        service.insertMember(new MemberDTO(name, laneNumber, gameCount, grade));
        return "redirect:/member/list";
    }

    // 정산 처리
    @PostMapping("/pay")
    public String pay(@RequestParam int bowlerId) {
        service.payMember(bowlerId);
        return "redirect:/member/list";
    }

    // 회원 물리 삭제
    @PostMapping("/delete")
    public String delete(@RequestParam int bowlerId) {
        service.deleteMember(bowlerId);
        return "redirect:/member/list";
    }

    // 전체 데이터 리셋
    @PostMapping("/init")
    public String init() {
        service.resetAll();
        return "redirect:/member/list";
    }

    // 매출 결산
    @GetMapping("/sales")
    public String sales(Model model) {
        List<BillingDTO> salesList = service.getSalesReport();
        int grandTotal = salesList.stream().mapToInt(BillingDTO::getTotalFee).sum();
        model.addAttribute("salesList", salesList);
        model.addAttribute("grandTotal", grandTotal);
        return "sales";
    }
}