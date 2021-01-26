package com.mycompany.webapp.re;


import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class Ch14BoardServiceRe {
	private static final Logger logger = LoggerFactory.getLogger(Ch14BoardServiceRe.class);
		
	
	@Resource
	private Ch14BoardDaoRe boardDaoRe;
	
	public List<Ch14BoardRe> getBoardList(){
		List<Ch14BoardRe> list = boardDaoRe.selectAll();
		return list;
	}
	
	public void saveboard(Ch14BoardRe board) {
		boardDaoRe.insert(board);
	}
	
}
