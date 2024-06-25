package com.mysite.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.entity.member.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{
	// 회원 이름으로 검색
	Page<Member> findByNameContaining(String name, Pageable pageable);
	
	// 회원 아이디 검색
	Page<Member> findById(String id, Pageable pageable); // 따로 정의하지 않으면 기본키로 정의
	
	// 회원 전화번호 검색
	// Page<Member> findByPhoneContaining(String id, Pageable pageable);
	Page<Member> findByPhone(String id, Pageable pageable);
}