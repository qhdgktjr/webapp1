package com.mycompany.webapp.service;


import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch14BoardDao;
import com.mycompany.webapp.dto.Ch14Board;
import com.mycompany.webapp.dto.Ch14Pager;


@Service
public class Ch14BoardService {
	private static final Logger logger = LoggerFactory.getLogger(Ch14BoardService.class);
		
	
	@Resource
	private Ch14BoardDao boardDao;
	
	public List<Ch14Board> getBoardList(){
		List<Ch14Board> list = boardDao.selectAll();
		return list;
	}
	public void saveBoard(Ch14Board board) {
		boardDao.insert(board);
	}
	
	public List<Ch14Board> getBoardList(Ch14Pager pager){
		List<Ch14Board> list = boardDao.selectByPage(pager);
		return list;
	}
	
	
	public int getTotalRows() {
		int totalRows = boardDao.countAll();
		return totalRows;
	}
	public Ch14Board getBoard(int bno) {
		Ch14Board board = boardDao.selectByPk(bno);
		return board;
	}
	public void updateBoard(Ch14Board board) {
		boardDao.update(board);
	}
	public void deleteBoard(int bno) {
		boardDao.delete(bno);
		
	}
	public void addHitcount(int bno) {
		boardDao.updateHitcount(bno);
		
	}

	
}
