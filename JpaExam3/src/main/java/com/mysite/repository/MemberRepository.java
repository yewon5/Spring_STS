package com.mysite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.entity.member.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{
	

}
