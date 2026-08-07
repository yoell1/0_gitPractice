package com.kh.mybatis.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor    // 기본 생성자 -->매개변수가 없는 생성자.
@AllArgsConstructor   // 모든 필드를 매개변수로 가지는 생성자.
@Getter               // getter 메소드.
@Setter               // setter 메소드.
@ToString             // toString 메소드.
public class MemberDTO {
	// String name, String email, int age
	private int id;
	private String name;
	private String email;
	private int age;
} 
