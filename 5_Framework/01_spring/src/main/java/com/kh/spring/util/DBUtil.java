package com.kh.spring.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	// 오라클 DB 접속 정보
	// 접속 URL -> jdbc:oracle:thin:@호스트:포트:SID형식
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	// 사용자명
	private static final String USER = "C##JDBC";
	// 비밀번호
	private static final String PASSWORD = "JDBC";

	// Connection 객체 생성 후 반환
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
        try {
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
           
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 클래스를 찾을 수 없습니다. 라이브러리를 확인하세요.");
            e.printStackTrace();
        } catch (SQLException e) {
            
            System.out.println("DB 연결 실패! URL, ID, PW를 확인하세요.");
            e.printStackTrace();
        }
        
      
        return conn; 
    }	
}

