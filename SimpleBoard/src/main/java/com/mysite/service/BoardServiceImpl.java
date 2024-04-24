package com.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mysite.domain.Board;
import com.mysite.persistance.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {
	//객체 생성하지 않고 주입 받도록 어노테이션 등록
	@Autowired
	private BoardDao boardDao;

	@Override
	public List<Board> getList() throws Exception {
		return null;
	}

	@Override
	public int write(Board board) throws Exception {
		return boardDao.write(board);
	}

	@Override
	public Board read(int bNo) throws Exception {
		return null;
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
