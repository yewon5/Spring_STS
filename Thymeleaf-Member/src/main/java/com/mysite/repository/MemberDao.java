package com.mysite.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.domain.Member;

@Repository //객체 생성
public class MemberDao {
	//@Autowired //주입 받기
	//private SqlSessionTemplate sqlSession;
	@Autowired
	private DataMappingInterface dataInterface;
	
	//매퍼인터페이스 방식
	public List<Member> getSelectAll() {
		//return sqlSession.selectList("com.mysite.memberMapper.selectAll");
		return dataInterface.getSelectAll();
	}	
	
	public boolean insertData(Member member) {
		return dataInterface.insertData(member);
	}
	
	public int deleteData(int num) {
		return dataInterface.deleteData(num);
	}
	
	
	public int updateData(Member member) {
		return dataInterface.updateData(member);
	}
	
	
	
	
	/* SqlSessionTemplate 방식
	public boolean insertData(Member member) {
		try {
			int result = sqlSession.insert("com.mysite.memberMapper.insertData", member);
			//System.out.println("result : " + result);
			
			//insert 성공 여부 코드
			if(result > 0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception err) {
			return false;
		}		
	}
	
	public void deleteData(int num) {
		sqlSession.delete("com.mysite.memberMapper.deleteData", num);
	}
	
	public void updateData(int num) {
	    sqlSession.selectOne("com.mysite.memberMapper.updateData", num);
	}
	*/
}
