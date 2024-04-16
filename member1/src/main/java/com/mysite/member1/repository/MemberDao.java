package com.mysite.member1.repository;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.mysite.member1.model.Member;

//DB와 연동하는 클래스 (일단 간단하게 Map에 메모리를 저장하는 방식으로 진행)
public class MemberDao {
	//회원정보를 저장하는 DTO 패턴
	private static Map<String, Member> mapDB = new HashMap<String, Member>();
	private static long nextId = 0; //중복되지 않게 단 1개만 생성되도록
	
	//회원 정보 입력
	public void insert(Member member) {
		member.setId(++nextId);
		mapDB.put(member.getEmail(), member); //(key, value) put은 저장
		System.out.println("mapDB : " + mapDB);
	}
	
	//같은 이메일 주소가 있는지 확인
	public Member selectByEmail(String email) {
		return mapDB.get(email); //get은 조회
	}
	
	//전체 회원정보 조회
	public Collection<Member> selectAll() {
		return mapDB.values();
	}
	
	//패스워드 수정
	public void update(Member member) {
		mapDB.put(member.getEmail(), member);
		System.out.println("mapDB 수정 : " + mapDB);
	}
}
