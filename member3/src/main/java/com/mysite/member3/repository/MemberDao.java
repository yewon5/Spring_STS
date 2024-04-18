package com.mysite.member3.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
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
		
		/*
		jdbcTemplate.update(new PreparedStatementCreator() { //update(PreparedStatementCreator psc)
			
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
		*/
		
		//위의 코드를 람다함수(화살표함수)로 바꿔보기. 1번만 호출할 것이기 때문에 함수이름이 필요 없음
		jdbcTemplate.update(
			(Connection con) -> {
				String sql = "insert into member(id, email, password, name, registerDate) values(seq_id.nextVal, ?, ?, ?, ?)";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, member.getEmail());
				stmt.setString(2, member.getPassword());
				stmt.setString(3, member.getName());
				stmt.setTimestamp(4, new Timestamp(member.getRegisterDate().getTime()));
				
				return stmt;
			}
		);
	}
	
	
	//같은 이메일주소가 있는지 확인하는 메서드 
	public Member selectByEmail(String email) {
		String sql = "select * from member where email=?";
		/*
		Member mem = jdbcTemplate.query(sql, 
			new PreparedStatementSetter() { //Setter인 이유는 ?값을 입력받기 위해서
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, email);
				}
			}, 
			new ResultSetExtractor<Member>() {
				@Override
				public Member extractData(ResultSet rs) throws SQLException, DataAccessException {
					Member memDto = null;
					if(rs.next()) {
						memDto = new Member(rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getTimestamp("registerDate"));
						memDto.setId(rs.getLong("id"));
					}
					return memDto;
				}
			}
		);
		
		return mem;
		*/
		
		//람다식으로 변경
		Member mem = jdbcTemplate.query(sql, 
			(PreparedStatement ps) -> {
				ps.setString(1, email);
			}, 
			(ResultSet rs) -> {
				Member memDto = null;
				if(rs.next()) {
					memDto = new Member(rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getTimestamp("registerDate"));
					memDto.setId(rs.getLong("id"));
				}
				return memDto;
			}
		);
		
		return mem;
	}
	
	//전체 회원정보 조회(하지만 객체설계원칙중 하나인 단일클래스 원칙에 위배되므로 따로 만드는것이 좋음.)
	public Collection<Member> selectAll(){
		String sql = "select * from member";
		
		/*
		//<T> List<T> query(String sql, RowMapper<T> rowMapper) RowMapper라는 인터페이스를 상속 받아서 클래스를 생성. 데이터를 받아야하므로 result 변수선언해주는 것
		List<Member> result = jdbcTemplate.query(sql, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException { //sql이 DB서버로 전달하고, DB로부터 받아온 정보를 rs매개 변수에 저장함. mapRow는 DB데이터 개수만큼 반복됨
				Member memDto = new Member(rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getTimestamp("registerDate"));
				memDto.setId(rs.getLong("id"));
				return memDto; //query메서드가 memDto에 리턴하는데 누가 받느냐? List<Member> result가 받음.
			}
		}); 
		
		return result; 
		*/
		
		//람다식으로 변경
		List<Member> result = jdbcTemplate.query(sql, (ResultSet rs, int rowNum) ->  { 
				Member memDto = new Member(rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getTimestamp("registerDate"));
				memDto.setId(rs.getLong("id"));
				return memDto;
			}
		); 
		
		return result; 
	}

	
	//패스워드 수정 메서드
	public void update(Member member) {
		String sql = "update member set password=? where email=?";
		
		jdbcTemplate.update(sql, member.getPassword(), member.getEmail()); //두번째는 물음표 값

	}
}


