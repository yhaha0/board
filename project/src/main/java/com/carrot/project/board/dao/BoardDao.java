package com.carrot.project.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.carrot.project.board.domain.Board;

@Repository
public class BoardDao {
	@Autowired private SqlSessionTemplate sst;
	private final String NAMESPACE="mapper.boardMapper";
	
	public int insert(Board vo) {
		return sst.insert(NAMESPACE+".insert", vo);
	}
	
	public Board selectOne(int board_id) {
		return sst.selectOne(NAMESPACE+".selectOne", board_id);
	}
	
	public List<Board> getList(){
		return sst.selectList(NAMESPACE+".getList");
	}
	
	public int delete(int board_id) {
		return sst.delete(NAMESPACE+".delete", board_id);
	}

}
