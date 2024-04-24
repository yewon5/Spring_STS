package com.mysite.persistance;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mysite.domain.Board;

public interface BoardDao {
	public List<Board> getList() throws Exception; //전체 데이터 조회
	public int write(Board board) throws Exception; //글 저장, 몇 개가 저장됐는지 리턴받기위해 int 정수형으로 선언
	public Board read(int bNo) throws Exception;
	public int update(Board board) throws Exception;
	public int delete(int bNo) throws Exception;
}
