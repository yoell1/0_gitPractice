package com.kh.mybatis.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.mybatis.model.dto.MemberDTO;

/*
 *  01_spring 프로젝트에서 MemberDAO를 대체하는 MyBatis의 Mapper 인터페이스!
 *  
 *  @Mapper : 이 인터페이스는 MyBatis로써 스프링이 어플리케이션 시작 시
 *            인터페이스의 구현체를 자동으로 만들어서 Bean으로 등록함.
 *            (개발자가 직접 SQL 실행 코드를 작성하지 않아도 됨!)
 */
@Mapper
public interface MemberMapper {
	// 전체 회원 목록 조회(SELECT)
	List<MemberDTO> findAll();
	
	// 회원 등록 (데이터 추가/INSERT)
	int insert(MemberDTO member);

	// 특정 회원 상세 조회 (SELECT) - 추가 필요
	// 수정 페이지로 진입할 때 특정 회원의 기존 정보를 불러오는 용도
    MemberDTO findById(Long id);
   
    // 회원 수정 (데이터 수정/UPDATE)
	int update(MemberDTO member);
	
	// 회원 삭제 (데이터 삭제/DELETE)
	int delete(Long id);
	
	
	
	
}
