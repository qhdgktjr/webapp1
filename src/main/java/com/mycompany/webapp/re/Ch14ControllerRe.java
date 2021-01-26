package com.mycompany.webapp.re;

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

@Controller("ch14ControllerRe")
@RequestMapping("/ch14re")
public class Ch14ControllerRe {
	private static final Logger logger = LoggerFactory.getLogger(Ch14ControllerRe.class);
	
	@GetMapping("/content")
	public String content() {
		logger.info("확인");
		return "ch14re/content";
	}
	
	
	@Resource
	private DataSource dataSource;
	
	/*ajax로 sql연결 확인
	 	conntest1.jsp에서 html방식을 다 지우고 
	 	model객체를 사용하여 보여준다.
	*/
	
	@GetMapping("/conntest")
	public String conntest(Model model) {
		logger.info("ajax");
		try {
			Connection conn = dataSource.getConnection();
			model.addAttribute("test", "연결 확인 복습");
			//커넥션 풀에 반납 꼭 해두기
			conn.close();
		} catch (SQLException e) {
			model.addAttribute("test", "연결 안됨 복습");
			e.printStackTrace();
		}
		return "ch14re/conntest";
	}
	
	
	@GetMapping("/jsonresponse1")
	public void jsonresponse1(HttpServletResponse response) throws Exception{
		//http 응답 헤더 본문에 한글 타입 설정도 해줘야한다., json 어플리케이션/json을 설정
		response.setContentType("application/json;charset=UTF-8");
		//본문의 응답을 보내야 하므로 출력 스트림 이용
		PrintWriter pw = response.getWriter();
		
		//객체로 만들기
		JSONObject obj = new JSONObject();
		obj.put("name", "봉하석");
		obj.put("age", 32);
		
		//car라는 객체를 생성하기, 다시 넣기
		JSONObject car = new JSONObject();
		car.put("kind", "머스탱");
		car.put("color", "흰색");
		
		obj.put("car", car);
		
		//배열  객체 생성
		JSONArray hobby = new JSONArray();
		hobby.put("수영");
		hobby.put("자전거");
		hobby.put("달리기");
		obj.put("hobby", hobby);
		
		//객체를 생성 후 보내기
		String json = obj.toString();
		pw.println(json);
		
		pw.flush();
		pw.close();
		
	}
	
	
	//배열로 응답
	@GetMapping("/jsonresponse2")
	public void jsonresponse2(HttpServletResponse response) throws Exception{
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		JSONArray arr = new JSONArray();
		for(int i = 1; i<=3; i++) {
			JSONObject obj = new JSONObject();
			obj.put("bno" , i);
			obj.put("btitle" , "제목" + i);
			obj.put("bwriter" , "글쓴이" + i);
			arr.put(obj);
		}
		
		String json = arr.toString();
		pw.println(json);
		
		pw.flush();
		pw.close();
		
	}
	
	//사원 번호 응답으로 보내기
	@Resource
	private Ch14ServiceRe ch14ServiceRe;
	
	@GetMapping("/employee")
	public void employee(int employee_id, HttpServletResponse response) throws Exception{
		Ch14EmployeeRe emp = ch14ServiceRe.getEmployeeRe(employee_id);
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		JSONObject obj = new JSONObject();
		obj.put("id", emp.getEmployee_id());
		obj.put("first", emp.getEmployee_id());
		obj.put("last", emp.getEmployee_id());
		
		String json = obj.toString();
		pw.println(json);
		
		pw.flush();
		pw.close();
	}
	/*
	@Resource
	private Ch14BoardServiceRe boardServiceRe;
	
	@GetMapping("/boardlist")
	public String boardlist(Model model) {
		List<Ch14BoardRe> list = boardServiceRe.getBoardList();
		model.addAttribute("list", list);
		return "ch14/boardlist";
	}
	
	@GetMapping("/boardsave")
	public String boardsave() {
		for(int i = 1; i<=50; i++) {
			Ch14BoardRe board = new Ch14BoardRe();
			board.setBtitle("제목" + i);
			board.setBcontent("내용" + i);
			board.setBwriter("winter");
			boardServiceRe.saveboard(board);
		}
		return "redirect:/ch14re/boardlist";
	}
	*/
}
