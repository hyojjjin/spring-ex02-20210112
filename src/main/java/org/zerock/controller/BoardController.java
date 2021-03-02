package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@RequestMapping("/board/*")
@Log4j
public class BoardController {
	
	private BoardService service;
	
	/*
	public BoardController(BoardService service) {
		super();
		this.service = service;
	}
	*/

	// 211 page 표
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	@GetMapping("/list") 
	// handler 메소드의 return type이 void인 경우
	//   요청경로가 view(jsp)의 이름이 됨 
	//   이 메소드는 (/board/list) -> /board/list.jsp
//	public void list(Model model) {
//		log.info("******************** list *******************");
//		List<BoardVO> list = service.getList();
//		model.addAttribute("list", list);
//	}
	
	
	@GetMapping("/list")
	public void list(@ModelAttribute("cri") Criteria cri, Model model) {
		List<BoardVO> list = service.getList(cri);
		
		int total = 500;
		PageDTO dto = new PageDTO(cri, 500);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", dto);
	}
	
	@GetMapping("/register")
	public void register(@ModelAttribute("cri") Criteria cri) {
		
	}
	
	
//	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		
		/*
		BoardVO board = new BoardVO();
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		board.setWriter(request.getParameter("writer"));
		*/
		
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno());
		rttr.addFlashAttribute("message", board.getBno() + "번 글이 등록되었습니다.");
		
//		return "board/list";
		return "redirect:/board/list";
	}
	
	// 표: /board/read, 코드: /board/get
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		//기본타입 객체가 아니라면 스프링에서 자동으로 model에 넣어줌. 넣어줄땐 타입의 소문자로 들어감
		//@ModelAttribute("cri")은 모델에 들어갈 이름을 설정해준다.
		/** 예전 코드 (스프링 없이) 
		String boardNum = request.getParameter("num");
		int num = Integer.parseInt(boardNum);
		
		BoardVO vo = service.get((long) num);
		
		request.setAttribute("board", vo);
		
		request.getRequestDispatcher(".jsp").forward();
		*/
		
		log.info("get method - bno: " + bno);
		log.info(cri);
		BoardVO vo = service.get(bno);
		model.addAttribute("board", vo);
//		model.addAttribute("cri", cri);
	}
	
	/*
	 @GetMapping("/modify") public void modify(Long bno, Model model) {
	 BoardVO vo = service.get(bno);
	 model.addAttribute("board", vo); }
	 */
	
	//이전에 만들었던 get과 같은 일을 함.
	
	
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
										//rttr.addFlashAttribute메소드를 사용하기 위해 받음. 근데 어디서 누가 주는거징?
		/** 스프링 없이
		BoardVO board = new BoardVO();
		board.setBno(request.getParameter("bno"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		*/
		
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
			//redirectAttribute에 넣어줌, 성공 시 모달창 있는 화면으로 넘어가기 위함.
			rttr.addFlashAttribute("message", board.getBno() + "번 글이 수정되었습니다.");
		}
		
		log.info(cri);
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		//redirect할때 파라미터로 같이 넘어감
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify2")
	public String modify2(BoardVO board, RedirectAttributes rttr) {
		/** 스프링 없이
		BoardVO board = new BoardVO();
		board.setBno(request.getParameter("bno"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		*/
		
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
			rttr.addAttribute("bno", board.getBno());
			rttr.addAttribute("a", "a");
			rttr.addFlashAttribute("b", "b");
		}
		return "redirect:/board/get";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr) {
		//같은 이름이면 @RequestParam("bno" 생략가능)
		if(service.remove(bno)) {
				rttr.addFlashAttribute("result", "success");
				rttr.addFlashAttribute("message", bno + "번 글이 삭제되었습니다.");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		//redirect할때 파라미터로 같이 넘어감
		return "redirect:/board/list";
	}
}

// servlet/jsp
// ControllerUsingURI(Servlet) ....properties
//   /list.do -> ListHandler 