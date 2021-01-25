package com.mycompany.webapp.controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch14Board;
import com.mycompany.webapp.dto.Ch14Employee;
import com.mycompany.webapp.service.Ch14BoardService;
import com.mycompany.webapp.service.Ch14EmployeeService;

			//생략시 기본 클래스 이름의 첫글자소문자
@Controller("ch14Controller")
@RequestMapping("/ch14")
public class Ch14Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch14Controller.class);
	
		@GetMapping("/content")
		public String content() {
			
			return "ch14/content";
		}
		
		@Resource
		private DataSource dataSource;
		//관리 객체가 존재한다. <bean>통해서
		
		@GetMapping("/conntest")
		public String conntest(Model model) {
							//커넥션 풀을 가져오기
			try {
				//커넥션 풀에서 커넥션 대여
				Connection conn = dataSource.getConnection();
				model.addAttribute("result", "연결성공");
				//커넥션 풀에 반납
				conn.close();
			} catch (SQLException e) {
				model.addAttribute("result","연결 실패");
				e.printStackTrace();
			} finally {
				// 항상 실행
			}
			return "ch14/conntest";
		}
		
		//Java Script Object Notations 자바스크립트 객체 표기법
		@GetMapping("/jsonresponse1")
		public void jsonresponse1(HttpServletResponse response) throws Exception{
			//응답을 만들어야 한다.			json타입
			response.setContentType("application/json;charset=UTF-8");
			// http 본문행에 {} 들어가진다.
			PrintWriter pw = response.getWriter();
			/*
			pw.println("{");
						//속성 : 스칼라
			pw.println("\"name\":\"홍길동\","); 
			pw.println("\"age\":30,");
			pw.println("\"car\":{\"kind\":\"그랜저\", \"color\":\"흰색\"},");
			pw.println("\"hobby\":[\"영화\", \"여행\", \"수영\"]");
			pw.println("}");
			*/
			
			// {}를 만드는 표현
			JSONObject root = new JSONObject();
			root.put("name", "홍길동");
			root.put("age", "30");
			
			//객체를 생성하여 사용
			JSONObject car = new JSONObject();
			//car 속성의 내용을 작성한것
			car.put("kind", "그렌저");
			car.put("color", "흰색");
			root.put("car", car);
			
			//배열 사용법
			JSONArray hobby = new JSONArray();
			hobby.put("영화");
			hobby.put("여행");
			hobby.put("수영");
			root.put("hobby", hobby);
			
			String json = root.toString();
			//json으로 출력, 보내기
			pw.println(json);
			
			
			pw.flush();
			pw.close();
			
		}
		
		//배열을 응답으로
		@GetMapping("/jsonresponse2")
		public void jsonresponse2(HttpServletResponse response) throws Exception{
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			/*
			pw.println("[");
			pw.println("	{\"bno\":1, \"btitle\":\"제목1\", \"bwriter\":\"글쓴이1\"},");
			pw.println("	{\"bno\":2, \"btitle\":\"제목2\", \"bwriter\":\"글쓴이2\"},");
			pw.println("	{\"bno\":3, \"btitle\":\"제목3\", \"bwriter\":\"글쓴이3\"}");
			pw.println("]");
			*/
			
			JSONArray root = new JSONArray();
			for(int i=1; i<=3; i++) {
				// 객체를 만들어서 사용
				JSONObject board = new JSONObject();
				board.put("bno", i);
				board.put("btitle", "제목" + i);
				board.put("bwriter", "글쓴이" + i);
				root.put(board);
			}
			
			String json = root.toString();
			pw.println(json);
			
			pw.flush();
			pw.close();
			
		}
		
		
		
		//사실 Service를 주입 받아야 한다.
		@Resource
		private Ch14EmployeeService employeeService;
		/*
		@GetMapping("/employee")
		public String employee(int employee_id) {
			Ch14Employee emp = employeeService.getEmployee(employee_id);
			logger.info("employee_id : " + emp.getEmployee_id());
			logger.info("first_name : " + emp.getFirst_name());
			logger.info("last_name : " + emp.getLast_name());
			return "ch14/content";
		}
		*/
		
		@GetMapping("/employee")
		public void employee(int employee_id, HttpServletResponse response) throws Exception{
			Ch14Employee emp = employeeService.getEmployee(employee_id);
			
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			
			JSONObject root = new JSONObject();
			root.put("employee_id", emp.getEmployee_id());
			root.put("first_name", emp.getEmployee_id());
			root.put("last_name", emp.getEmployee_id());
			
			String json = root.toString();
			pw.println(json);
			
			pw.flush();
			pw.close();
		}
		
		@Resource
		private Ch14BoardService boardService;
		
		@GetMapping("/boardlist")
		public String boardlist(Model model) {
			List<Ch14Board> list = boardService.getBoardList();
			model.addAttribute("list", list);
			return "ch14/boardlist";
		}
		
		@GetMapping("/boardsave")
		public String boardsave() {
			for(int i=1; i<=100; i++) {
				Ch14Board board = new Ch14Board();
				board.setBtitle("제목" + i);
				board.setBcontent("내용" + i);
				board.setBwriter("winter");
				boardService.saveBoard(board);
			}
			return "redirect:/ch14/boardlist";
		}
		
	
		
	


}
