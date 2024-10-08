package com.mysite.persistance;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.domain.Board;

@Repository
public class BoardDaoImpl implements BoardDao {
	@Autowired
	private SqlSession sqlSession;
	
	private static final String NAMESPACE="com.mysite.boardMapper";
	@Override
	public List<Board> getList() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".getList"); // BoardDaoImpl(게시판 목록 가져오기) → BoardServiceImpl → BoardController
	}

	@Override
	public int write(Board board) throws Exception {
	//여기서는 mybatis mapper의 insert를 불러옴.
	//insert태그가 mapper에 하나만 만들어지는건 아님. 그래서 id도 호출해줘야함.
	//"namespace.ID값"으로 불러오기.
	return sqlSession.insert(NAMESPACE + ".write", board); // board-mapper.xml
	}

	@Override
	public Board read(int bNo) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".read", bNo);
	}

	@Override
	public int update(Board board) throws Exception {
		return 0;
	}

	@Override
	public int delete(int bNo) throws Exception {
		return 0;
	}
}
