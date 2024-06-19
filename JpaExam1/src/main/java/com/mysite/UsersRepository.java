package com.mysite;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{ //<테이블, 기본키 타입> 자바에서는 클래스 이름이지만 DB로 넘어가면 테이블됨,
	List<Users> findAll();
	//List<Users> findFirst2ByUsernameLikeOrderByIDDesc(String name);
}
