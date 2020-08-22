package aloha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import aloha.domain.Board;
import aloha.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		model.addAttribute("board", new Board());
		model.addAttribute("list", service.list());
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String  search(String title, Model model) throws Exception{
		Board board = new Board();
		board.setTitle(title);
		
		model.addAttribute("board", board);
		
		model.addAttribute("list", service.search(title));
		
		return "board/list";
		
	}
	
	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public void registerForm(Board board, Model model) throws Exception {
		
	}
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(Board board, Model model) throws Exception {
		service.register(board);
		
		model.addAttribute("msg", "등록이 완료되었습니다.");
		
		return "board/success";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam int boardNo, Model model) throws Exception {
		model.addAttribute(service.read(boardNo));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modify(int boardNo, Model model) throws Exception {
		model.addAttribute(service.read(boardNo));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(Board board, Model model) throws Exception {
		service.modify(board);
		
		model.addAttribute("msg", "수정이 완료되었습니다.");
		
		return "board/success";  
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("boardNo") int boardNo, Model model) throws Exception {
		service.remove(boardNo);
		
		model.addAttribute("msg", "삭제가 완료되었습니다.");
		
		return "board/success";
		
	}
	
	
		
	
}














