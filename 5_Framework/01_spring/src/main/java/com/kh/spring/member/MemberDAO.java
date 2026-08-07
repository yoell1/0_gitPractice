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

		String sql = "SELECT ID,NAME,EMAIL,AGE FROM member ORDER BY ID ";

		// JDBC 실행 순서S
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

				MemberDTO m = new MemberDTO(rset.getInt("id"), rset.getString("name"), rset.getString("email"),
						rset.getInt("age"));
				list.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 회원 등록
	// => 전달된 회원 정보를 member 테이블에 추가하는 메소드
	public void insert(MemberDTO member) {
		String sql = "INSERT INTO member (id, name, email, age) VALUES (SEQ_MEMBER_ID.NEXTVAL, ?, ?, ?)";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// 컨트롤러에서 하던 ? 값 채우기를 여기서 수행합니다.
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getEmail());
			pstmt.setInt(3, member.getAge());

			// 쿼리 실행
			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("DB 새 회원 등록 성공!");
			}

		} catch (SQLException e) {
			System.out.println(" 회원 추가 중 SQL 예외 발생!");
			e.printStackTrace();
		}

	}

	// 회원 삭제
	// => 전달된 회원 번호를 기준으로 멤버 테이블에서 삭제하는 메소드
	public void delete(int id) {

		String sql = "DELETE FROM MEMBER WHERE id = ?";
		// TODO: 쿼리 실행까지....

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("회원 삭제 성공! : " + id);
			} else {
				System.out.println("회원 삭제 실패!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	// 회원 정보 수정 => 전달된 회원 정보를 기준으로 테이블 데이터를 변경하는 메소드
	public void update(MemberDTO member) {

		String sql = "UPDATE MEMBER SET name = ?, email = ?, age = ? WHERE id = ? ";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

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

	// 회원 정보 조회
	// => member 테이블에서 전달 받은 회원 번호(id)에 해당하는
	// 회원 정보를 조회한 결과 반환

	public MemberDTO findById(int id) {
		MemberDTO member = null;
		String sql = "SELECT ID, NAME, EMAIL, AGE FROM member WHERE ID = ?";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setInt(1, id);
			ResultSet rset = pstmt.executeQuery();

			if (rset.next()) {
				member = new MemberDTO();
				member.setId(rset.getInt("id"));
				member.setName(rset.getString("name"));
				member.setEmail(rset.getString("email"));
				member.setAge(rset.getInt("age"));

				System.out.println("회원정보 조회 성공! ID : " + member.getId());
			} else {
				System.out.println("회원정보 조회 실패 (일치하는 회원 없음)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 정상 조회 시 데이터가 담긴 객체 반환, 실패 시 null 반환
		return member;
	}

}
