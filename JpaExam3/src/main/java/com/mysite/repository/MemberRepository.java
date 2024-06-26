package com.mysite.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mysite.entity.member.Member;

import jakarta.transaction.Transactional;

public interface MemberRepository extends JpaRepository<Member, Integer>{
	/******************************** 검색을 위한 쿼리 메서드 ********************************/
	// 회원 이름으로 검색
	Page<Member> findByNameContaining(String name, Pageable pageable);
	
	// 회원 아이디 검색
	Page<Member> findById(String id, Pageable pageable); // 따로 정의하지 않으면 기본키로 정의
	
	// 회원 전화번호 검색
	// Page<Member> findByPhoneContaining(String id, Pageable pageable);
	// Page<Member> findByPhone(String id, Pageable pageable);
	
	// LIKE - 특정 문자열이 포함되었는지 찾으려면? => %111%
	Page<Member> findByPhoneLike(String id, Pageable pageable);
	
	// And쿼리 : 이름과 아이디가 같은 회원을 조회
	Page<Member> findByNameAndId(String name, String id, Pageable pageable); //매개변수도 이름 아이디 순서로 받아야 함
	
	// Or쿼리 : 이름 또는 아이디에 "홍"이라는 값을 갖는 회원을 조회
	Page<Member> findByNameContainsOrIdContains(String name, String id, Pageable pageable); //'홍' 포함이라서 Contains
	
	
	/******************************** 정렬을 위한 쿼리 메서드 ********************************/
	// 회원 등록 순으로 정렬 => 조건, 정렬
	// 조건 : 회원 번호가 크거나 같은 사람들 중에서 이름 순으로 정렬 GreaterThanEqual >=, LessThanEqual <=
	Page<Member> findByNumGreaterThanEqualOrderByNameAsc(Integer num, Pageable pageable); //페이징을 안할 경우 List<Member>로 받는다.
	
	
	/************************************** @Query **************************************/
	// 회원 이름으로 검색
//	@Query("SELECT m FROM Member m WHERE m.name = ?1 and m.age>20 ORDER BY m.age ASC") //위에서는 메서드 이름이 쿼리를 책임졌지만 여기서는 어노테이션이 쿼리를 책임진다. ?1은 파라미터의 순서를 뜻함
//	public Page<Member> findByName(String name, Pageable pageable);//메서드에 영향을 받지 않음 
	
	// 이름에 "길동"이 들어가고 나이가 20살 이상이고 정렬은 이름 기준으로 오름차순 정렬하시오.
//	@Query("SELECT m FROM Member m WHERE m.name LIKE %?1% and m.age>=?2 ORDER BY m.name ASC")
//	public Page<Member> findByName(String name, int age, Pageable pageable); // 나이 값이 정해진 것이 아니라면 파라미터로 전달 받야야 한다.
	
	// 이름에 "길동"이 들어가고 나이가 20~30살이고 정렬은 이름 기준으로 오름차순 정렬하시오.
	@Query("SELECT m FROM Member m WHERE (m.name LIKE %?1%) and (m.age BETWEEN 20 AND 30) ORDER BY m.name ASC")
	public Page<Member> findByName(String name, int age, Pageable pageable);
	
	// 위의 쿼리를 메서드로 조합해서 만드시오.
	Page<Member> findByNameContainsAndAgeBetweenOrderByNameAsc(String name, int start, int end, Pageable pageable);
	
	// 기본키를 기준으로 이름과 나이를 수정하는 JPQL을 작성하시오.
	// 수정할때는 2가지 어노테이션이 반드시 있어야 함.
	@Transactional
	@Modifying 
	@Query("UPDATE Member m SET m.name=:name, m.age=:age WHERE m.num=:num") //테이블 이름 대소문자 구분
	int updateMemberQuery(@Param("name") String name, @Param("age") int age);
}