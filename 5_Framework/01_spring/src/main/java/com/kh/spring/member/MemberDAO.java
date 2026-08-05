package com.kh.spring.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kh.spring.util.DBUtil;

// DAO : 실제 DB에 접근하는 객체
@Repository // @Component + DB 접근 계층임을 의미함!
public class MemberDAO {
	// 회원 목록 조회
	// member 테이블 전체 목록을 조회한 결과 반환 메소드
	public List<MemberDTO> findAll() {
		List<MemberDTO> list = new ArrayList<>();

		String sql = "SELECT ID,NAME,EMAIL,AGE FROM member";

		// JDBC 실행 순서
		// 0) 드라이버 로드(환경에 따라 생략 가능)
		// 1) Connection 객체
		// 2) PreparedStatement 객체 생성
		// 3) 쿼리문 실행 후 결과 받기
		// --> 추출 및 처리 작업
		// 4) 자원 반납 (try - with -resources 구문 사용)
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rset = pstmt.executeQuery();) {

			// 조회된 결과를 추출하여 리스트에 추가
			// 조회 결과 유무 확인 : next()
			while (rset.next()) {
				// 컬럼을 기준으로 데이터를 추출 getxxx(컬럼명)
//				
//				// id 컬럼 추출
//	            rset.getInt("id");
//				// name 컬럼 추출
//				rset.getString("name");
//				// email 컬럼 추출
//			    rset.getString("email");
//				// age 컬럼 추출
//				rset.getInt("age");

				MemberDTO m = new MemberDTO(rset.getInt("id"),
						                    rset.getString("name"),
					                      	rset.getString("email"),
						                    rset.getInt("age"));
				list.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 회원 추가
	// => 전달된 회원 정보를 member 테이블에 추가하는 메소드
	public void insert(MemberDTO member) {

	}

	// 회원 삭제
	// => 전달된 회원 번호를 기준으로 멤버 테이블에서 삭제하는 메소드
	public void delete(int id) {

		String sql ="DELETE FROM MEMBER WHERE id = ?";
		// TODO: 쿼리 실행까지....

		try(
				Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)
				){
			pstmt.setInt(1,id);
			int result = pstmt.executeUpdate();
			if(result > 0 ) {
				System.out.println("회원 삭제 성공! : " + id );
			}else {
				System.out.println("회원 삭제 실패!");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	// 회원 정보 수정 => 전달된 회원 정보를 기준으로 테이블 데이터를 변경하는 메소드
	public void update(MemberDTO member) {
	    // ⚠️ 주의: 키워드(SET, WHERE)와 컬럼명 사이에 공백을 꼭 확인하세요.
	    String sql = "UPDATE member SET name = ?, email = ?, age = ? WHERE id = ?";

	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        // ? 위치 홀더에 데이터 매핑 (왼쪽부터 1번)
	        pstmt.setString(1, member.getName());
	        pstmt.setString(2, member.getEmail());
	        pstmt.setInt(3, member.getAge());
	        pstmt.setInt(4, member.getId()); // WHERE 절의 id 조건값
	        
	        // 쿼리 실행 (영향을 받은 행의 수 반환)
	        int result = pstmt.executeUpdate();
	        
	        if (result > 0) {
	            System.out.println("DB 회원 정보 수정 성공! ID : " + member.getId());
	        } else {
	            System.out.println("DB 회원 정보 수정 실패 (해당 ID 없음)");
	        }
	        
	    } catch (SQLException e) {
	        System.out.println("회원 수정 중 SQL 예외 발생!");
	        e.printStackTrace();
	    }
	}

}
