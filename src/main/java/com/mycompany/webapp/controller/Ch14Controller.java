package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Ch14Board;
import com.mycompany.webapp.dto.Ch14Employee;
import com.mycompany.webapp.dto.Ch14Member;
import com.mycompany.webapp.dto.Ch14Order;
import com.mycompany.webapp.dto.Ch14OrderItem;
import com.mycompany.webapp.dto.Ch14Pager;
import com.mycompany.webapp.service.Ch14BoardService;
import com.mycompany.webapp.service.Ch14EmployeeService;
import com.mycompany.webapp.service.Ch14MemberService;
import com.mycompany.webapp.service.Ch14OrderService;

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
							//dto 변수와 같은 이름은 넣어야 한다.
		public void employee(int employee_id, HttpServletResponse response) throws Exception{
			Ch14Employee emp = employeeService.getEmployee(employee_id);
			
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			
			JSONObject root = new JSONObject();
			root.put("employee_id", emp.getEmployee_id());
			root.put("first_name", emp.getFirst_name());
			root.put("last_name", emp.getLast_name());
			
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
		
		@GetMapping("/boardlist2")
		public String boardlist2(
				/* 페이지 번호가 안넘어 오면 기본값으로 1 		사용자가 원하는 페이지 번호*/
				@RequestParam(defaultValue="1") int pageNo, Model model) {
			int totalRows = boardService.getTotalRows();
			Ch14Pager pager = new Ch14Pager(6, 5, totalRows, pageNo);
			
			List<Ch14Board> list = boardService.getBoardList(pager);
			model.addAttribute("list", list);
			model.addAttribute("pager", pager);
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
		
		@GetMapping("/boardwrite")
		public String boardwriteForm() {
			return "ch14/boardwrite";
		}
		
	
		@PostMapping("/boardwrite")
		public String boardwrite(Ch14Board board, HttpSession session) throws Exception {
			String mid = (String) session.getAttribute("sessionMid");
			//보드에 작성자를 저장하기
			board.setBwriter(mid);
			
			MultipartFile mf = board.getBattach();
			if(!mf.isEmpty()) {
				board.setBattachoname(mf.getOriginalFilename());
				String saveName = new Date().getTime() + "-" + mf.getOriginalFilename();
				board.setBattachsname(saveName);
				board.setBattachtype(mf.getContentType());
				
				//파일저장
				File saveFile = new File("D:/MyWorkspace/uploadfiles/boards/" + saveName);
				mf.transferTo(saveFile);
			}
			
			boardService.saveBoard(board);
			return "redirect:/ch14/boardlist2";
		}
		
		
		@GetMapping("/join")
		public String joinForm() {
			return "ch14/join";
		}
		
		@Resource
		private Ch14MemberService memberService;
		
		@PostMapping("/join")
		public String join(Ch14Member member) throws Exception {
			//파일정보얻기
			MultipartFile mf = member.getMphoto();
			if(!mf.isEmpty()) {
				member.setMphotooname(mf.getOriginalFilename());
				String saveName = new Date().getTime() + "-" + mf.getOriginalFilename();
				member.setMphotosname(saveName);
				member.setMphototype(mf.getContentType());
				
				//파일저장
				File saveFile = new File("D:/MyWorkspace/uploadfiles/members/" + saveName);
				mf.transferTo(saveFile);
			}
			
			//DB에 저장
			memberService.join(member);
			return "redirect:/ch14/boardlist2";
		}
		
		
		@GetMapping("/login")
		public String loginForm() {
			return "ch14/login";
		}
		
		
		@PostMapping("/login")
		public void login(Ch14Member member, HttpServletResponse response, HttpSession session) throws Exception {
			//success, wrongMid, wrongPassword
			String result = memberService.login(member);
			if(result.equals("success")) {
				session.setAttribute("sessionMid", member.getMid());
				
			}
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw = response.getWriter();
			
			//{"result" : "success"}
			JSONObject root = new JSONObject();
			root.put("result", result);
			pw.println(root.toString());
			
			pw.flush();
			pw.close();
			
		}
		
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.invalidate();
			return "redirect:/ch14/boardlist2";
		}
		 
		@GetMapping("/mphoto")
		//헤더에서 로그인 조건문을 했기때문에 항상 로그인일때만 가능하다. 따로 로그인의 유무를 확인 할 필요가 없다.
					/* 어느회원의 사진인지 받는다 */
		public void mphoto(String mid, HttpSession session, HttpServletResponse response) throws Exception {
			if(mid == null) {
				mid = (String) session.getAttribute("sessionMid");
			}
				
			Ch14Member member = memberService.getMember(mid);
			
			String filePath = null;
			//첨부 파일이 있을때
			if(member.getMphotosname() != null) {
				
				String mphotosname = member.getMphotosname();
				filePath = "D:/MyWorkspace/uploadfiles/members/" + mphotosname;
				response.setContentType(member.getMphototype());
				
				// 아래 3줄은 파일 다운로드를 원하지 않으면 주석처리해도 된다.
				String mphotooname = member.getMphotooname();
				mphotooname = new String(mphotooname.getBytes("UTF-8"), "ISO-8859-1");
				response.setHeader("content-Disposition", "attachment; filename=\"" + mphotooname + "\"");
				
			}else {
				//첨부파일 없을때
				filePath = "D:/MyWorkspace/uploadfiles/members/defaultphoto.png";
				response.setContentType("image/png");
			}
			
		
				OutputStream os = response.getOutputStream();
				InputStream is = new FileInputStream(filePath);
			
				FileCopyUtils.copy(is, os);
				os.flush();
				os.close();
				is.close();
			

			
		}
		
		@GetMapping("/boardread")
		public String boardreadForm(int bno, Model model) {
			boardService.addHitcount(bno);
			Ch14Board board = boardService.getBoard(bno);
			model.addAttribute("board", board);
			return "ch14/boardread";
		}
		
		@GetMapping("/boardupdate")
		public String boardupdate(int bno, Model model) {
			Ch14Board board = boardService.getBoard(bno);
			model.addAttribute("board", board);
			return "ch14/boardupdate";
		}
		
		@PostMapping("/boardupdate")
		public String boardupdate(Ch14Board board) {
			boardService.updateBoard(board);
			return "redirect:/ch14/boardlist2";
			
		}
		
		@GetMapping("/boarddelete")
		public String boarddelete(int bno) {
			boardService.deleteBoard(bno);
			return "redirect:/ch14/boardlist2";
		}
		
		@GetMapping("/battach")
		//첨부가 됐을때만 진행
		public void battach(int bno, HttpServletResponse response) throws Exception {
				
			Ch14Board board = boardService.getBoard(bno);
			
				
				String battachsname = board.getBattachsname();
				String filePath = "D:/MyWorkspace/uploadfiles/boards/" + battachsname;
				response.setContentType(board.getBattachtype());
				
				String oname = board.getBattachoname();
				oname = new String(oname.getBytes("UTF-8"), "ISO-8859-1");
				response.setHeader("content-Disposition", "attachment; filename=\"" + oname + "\"");
				
			
		
				OutputStream os = response.getOutputStream();
				InputStream is = new FileInputStream(filePath);
			
				FileCopyUtils.copy(is, os);
				os.flush();
				os.close();
				is.close();
			

			
		}
		
		@Resource
		private Ch14OrderService orderService;
		
		@GetMapping("/order")
		public String order() {
			//주문 정보 얻기
			Ch14Order order = new Ch14Order();
			order.setMid("winter");
			order.setAddress("서울시 송파구 ");
			
			//주문 상품 정보 얻기 (장바구니에서 가져오기)
			
			List<Ch14OrderItem> OrderItems = new ArrayList<>();
			Ch14OrderItem oi1 = new Ch14OrderItem();
			oi1.setPid("다이아몬드");
			oi1.setAmount(100);
			OrderItems.add(oi1);
			
			Ch14OrderItem oi2 = new Ch14OrderItem();
			oi2.setPid("내 집");
			oi2.setAmount(10);
			OrderItems.add(oi2);
			
			//주문 처리
			orderService.order(order, OrderItems);
			
			
			
			return "ch14/content";
		}

	


}
