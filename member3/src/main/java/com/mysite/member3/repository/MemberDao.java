package com.mysite.member3.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.mysite.member3.model.Member;

/*
class PreparedStatementCreatorImpl implements PreparedStatementCreator{ //인터페이스를 상속 받는 클래스는 관례적으로 Impl붙인다.
	@Override
	public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		return null;
	} 
}
*/

//DB연동 데이터를 지속적으로 유지시켜주는 @Repository
@Repository
public class MemberDao {
	private JdbcTemplate jdbcTemplate; //pom.xml에 jdbc 라이브러리 추가해야함
	
	public MemberDao() {}
	@Autowired
	public MemberDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//service클래스 에서 넘어옴
	public void insert(Member member) {
		//PreparedStatementCreator ps = new PreparedStatementCreatorImpl();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "insert into member(id, email, password, name, registerDate) values(seq_id.nextVal, ?, ?, ?, ?)";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, member.getEmail());
				stmt.setString(2, member.getPassword());
				stmt.setString(3, member.getName());
				stmt.setTimestamp(4, new Timestamp(member.getRegisterDate().getTime()));
				
				return stmt;
			}
		});
	}
	
	//같은 이메일주소가 있는지 확인하는 메서드 
	public Member selectByEmail(String email) {
		return null;
	}
	
	//전체 회원정보 조회(하지만 객체설계원칙중 하나인 단일클래스 원칙에 위배되므로 따로 만드는것이 좋음.)
	public Collection<Member> selectAll(){
		return null;
	}

	
	//패스워드 수정 메서드
	public void update(Member member) {

	}
}


