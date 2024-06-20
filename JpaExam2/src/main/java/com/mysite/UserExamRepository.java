package com.mysite;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//해당하는 테이블명으로 만드는게 관례적
@Repository
public interface UserExamRepository extends JpaRepository<UserExam, Long>{ //<테이블, 기본키 타입> 자바에서는 클래스 이름이지만 DB로 넘어가면 테이블됨,

}
