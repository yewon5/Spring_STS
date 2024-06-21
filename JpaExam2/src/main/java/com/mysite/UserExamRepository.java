package com.mysite;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//해당하는 테이블명으로 만드는게 관례적
@Repository
public interface UserExamRepository extends JpaRepository<UserExam, Long>{ //<테이블, 기본키 타입> 자바에서는 클래스 이름이지만 DB로 넘어가면 테이블됨,
	// 성과 이름을 조희를 한다. 단, userId로 내림차순 정렬하고 결과는 5개만 가져온다.
    List<UserExam> findByLastnameAndFirstnameOrderByUserIdDesc(String lastName, String firstName);
	// 클래스는 변수라서 소문자로 작성하는데 메서드는 첫글자 대문자로 작성한다. 매개변수 이름은 상관 없지만 순서는 지켜줘야 한다.
	
    // 이름을 조회하는데 대소문자 무시하고 Like쿼리를 이용하여 해당하는 데이터의 갯수를 가져온다.
    Integer countByFirstnameIgnoreCaseLike(String firstName);
    //결과값을 List로 받는게 아니라 갯수로 받으니까 Integer
    
    // 입력 받은 날짜보다 startDate가 작거나 같은 데이터가 있다면, true를 없다면 false를 리턴
    Boolean existsByStartDateLessThanEqual(LocalDateTime dt);
    
}

//이름이 같은 사람 select LastName, FirstName From UserExam WHERE LastName=? AND FirstName=?
//정렬 order by userId desc limit 5


/*
SELECT LastName, FirstName 
FROM (
    SELECT LastName, FirstName, ROWNUM AS rnum
    FROM (
        SELECT LastName, FirstName
        FROM UserExam
        WHERE LastName = ? AND FirstName = ?
        ORDER BY userId DESC
    )
    WHERE ROWNUM <= 5
)
WHERE rnum <= 5;
*/