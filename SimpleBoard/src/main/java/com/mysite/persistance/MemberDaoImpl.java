package com.mysite.persistance;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.domain.Member;
import com.mysite.domain.RegisterRequest;

@Repository
public class MemberDaoImpl implements MemberDao{
	//SQL세션 주입 받기 → member-mapper.xml 만들기
	@Autowired
	private SqlSession sqlSession;
	
	private static final String NAMESPACE="com.mysite.memberMapper";
	
	@Override
	public void registerMember(RegisterRequest dto) throws Exception {
		sqlSession.insert(NAMESPACE + ".registerMember", dto);
	}

	@Override
	public Member selectMember(String email) throws Exception {

		return null;
	}

	@Override
	public Member selectWithPass(String email, String pw) throws Exception { //mybaits로 전달하려면 RegisterRequest DTO로 묶어줘야 한다.
		RegisterRequest dto = new RegisterRequest();
		dto.setEmail(email);
		dto.setPassword(pw);
		
		return sqlSession.selectOne(NAMESPACE + ".selectWithPass", dto);
	}
}
