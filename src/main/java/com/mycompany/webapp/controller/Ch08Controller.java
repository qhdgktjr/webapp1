package com.mycompany.webapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch07Board;
import com.mycompany.webapp.dto.Ch08Board;

@Controller
@RequestMapping("/ch08")
public class Ch08Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch08Controller.class);

	@GetMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch08/content";
	}

	@GetMapping("/method1")
	public String method1(HttpSession session) {
		// "이름"으로 값을 저장
		session.setAttribute("name", "스프링");
		session.setAttribute("age", 26);
		session.setAttribute("job", "인공지능 개발자");
		return "ch08/el";
	}

	@GetMapping("/method2")
	public String method2(HttpSession session) {
		Ch08Board board = new Ch08Board();
		board.setNo(1);
		board.setTitle("너무추워요");
		board.setContent("과제하면 덜 추워요");
		board.setWriter("감자바");
		board.setDate(new Date());
		session.setAttribute("board1", board);

		return "ch08/el";
	}

	@GetMapping("/method3")
	public String method3(HttpSession session) {
		List<Ch08Board> list = new ArrayList<>();
		for(int i = 1; i<=10; i++) {
			Ch08Board board = new Ch08Board();
			board.setNo(i);
			board.setTitle("너무추워요" + i);
			board.setContent("과제하면 덜 추워요" + i);
			board.setWriter("감자바" + i);
			board.setDate(new Date());
					//포문 만큼 리스트 추가
			list.add(board);
		}
		session.setAttribute("boardList", list);

		return "ch08/el";
	}
	
	@PostMapping("/login")
	public String login(String uid, String upassword, HttpSession session) {
		if(uid.equals("aaa") && upassword.equals("123")) {
			session.setAttribute("loginStatus", uid);
		}
		return "redirect:/ch08/content";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
//					/*개별적으로 데이터를 삭제 저장값을 제거 */
		/* session.removeAttribute("loginStatus"); */
		/*세션의 모든 데이터를 삭제  */
		session.invalidate();
		return "redirect:/ch08/content";
	}
	
	@PostMapping("/boardWrite")
	public String boardWrite(Ch08Board board, HttpSession session) {
		String uid = (String)session.getAttribute("loginStatus");
		//로그인이 되어있다면
		if(uid != null) {
			board.setWriter(uid);
			logger.info("title : " + board.getTitle());
			logger.info("content : " + board.getContent());
			logger.info("writer : " + board.getWriter());
			logger.info("게시물을 성공적으로 저장함");
		}else {
			logger.info("로그인이 되어있지 않습니다.");
		}
		return "redirect:/ch08/content";
		
	}

}
