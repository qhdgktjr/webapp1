package com.mycompany.webapp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.dto.Ch14Board;
import com.mycompany.webapp.dto.Ch14Pager;

@Repository
public class Ch14BoardDao {
	private static final Logger logger = LoggerFactory.getLogger(Ch14BoardDao.class);
	
	@Resource
	//bean 관리 객체
	private SqlSessionTemplate sst;
	
	
	
	public List<Ch14Board> selectAll(){
		List<Ch14Board> list = sst.selectList("boards.selectAll");
		return list;
	}
	
	public int countAll() {
		int count = sst.selectOne("boards.countAll");
		return count;
	}
	
	/* 리턴타입이 반영된 행 수 를 나타난다. */
	public int insert(Ch14Board board) {
		int rows = sst.insert("boards.insert", board);
		return rows;
	}

	public List<Ch14Board> selectByPage(Ch14Pager pager) {
		List<Ch14Board> list = sst.selectList("boards.selecByPage", pager);
		return list;
	}

	public Ch14Board selectByPk(int bno) {
		Ch14Board board = sst.selectOne("boards.selectByPk", bno);
		return board;
	}

	public int update(Ch14Board board) {
		int rows = sst.update("boards.update", board);
		return rows;
	}

	public int delete(int bno) {
		int rows = sst.delete("boards.delete", bno);
		return rows;
	}

	public int updateHitcount(int bno) {
		int rows = sst.update("boards.updateHitcount", bno);
		return rows;
	}
	

	
}
