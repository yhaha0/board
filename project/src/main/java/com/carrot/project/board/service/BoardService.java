package com.carrot.project.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrot.project.board.dao.BoardDao;
import com.carrot.project.board.domain.Board;

@Service
public class BoardService {
	@Autowired private BoardDao bdao;
	
	public int insert(Board vo) {
		return bdao.insert(vo);
	}

	public Board selectOne(int board_id) {
		return bdao.selectOne(board_id);
	}
	
	public List<Board> getList(){
		return bdao.getList();
	}
	
	public int delete(int board_id) {
		return bdao.delete(board_id);
	}
	
}
