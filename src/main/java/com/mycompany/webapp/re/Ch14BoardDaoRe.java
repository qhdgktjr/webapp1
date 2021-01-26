package com.mycompany.webapp.re;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Ch14BoardDaoRe {
	
	@Resource
	private SqlSessionTemplate sst;
		
		public List<Ch14BoardRe> selectAll() {
			List<Ch14BoardRe> list = sst.selectList("mybatis.mapper.boards.selectAll");
			return list;
		}
		
		public int insert(Ch14BoardRe board) {
			int rows = sst.insert("mybatis.mapper.boards.insert", board);
			return rows;
		}
}
