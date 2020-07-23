package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;


@Controller
@RequestMapping("/board")
public class BoardContoller {

	@Autowired
	private BoardService boardService;

	//리스트
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(@RequestParam(value="keyword", required=false, defaultValue="") String keyword, 
					   Model model) {
		System.out.println("boardContoller/list");
		
		List<BoardVo> boardList = boardService.list(keyword);
		model.addAttribute("boardList", boardList);
		return "board/list";
	}

	//게시판 글읽기 --> PathVariable 로 사용한 예제
	@RequestMapping(value="/read/{no}", method={RequestMethod.GET, RequestMethod.POST})
	public String read(@PathVariable("no") int no, Model model) {
		System.out.println("boardContoller/read");
		
		BoardVo boardVo = boardService.read(no);
		model.addAttribute("boardVo", boardVo);
		
		return "board/read";
	}

	//게시판 글쓰기 폼
	@RequestMapping(value="/writeForm", method={RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("boardContoller/writeForm");
		
		return "board/writeForm";
	}

	//게시판 글쓰기
	@RequestMapping(value="/write", method={RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		System.out.println("boardContoller/write");
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		boardVo.setUserNo(authUser.getNo());
		boardService.write(boardVo);

		return "redirect:/board/list";
	}

	//게시판 글삭제
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute BoardVo boardVo, HttpSession session) {
		System.out.println("boardContoller/delete");
		
		//로그인한 사용자의 글만 삭제하도록 세션의 userNo도 입력(쿼리문에서 검사)
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		boardVo.setUserNo(authUser.getNo());
		boardService.remove(boardVo);
		
		return "redirect:/board/list";
	}

	//게시판 글수정 폼
	@RequestMapping(value="/modifyForm", method={RequestMethod.GET, RequestMethod.POST})
	public String modifyform(@RequestParam int no, Model model) {
		System.out.println("boardContoller/modifyform");
		
		BoardVo boardVo = boardService.read(no);
		model.addAttribute("boardVo", boardVo);
		return "board/modifyForm";
	}

	//게시판 글수정
	@RequestMapping(value="/modify", method={RequestMethod.GET, RequestMethod.POST})
	public String modifyform(@ModelAttribute BoardVo boardVo, HttpSession session, Model model) {
		System.out.println("boardContoller/modify");
		
		//로그인한 사용자의 글만 수정하도록 세션의 userNo도 입력(쿼리문에서 검사)
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		boardVo.setUserNo(authUser.getNo());
		boardService.modify(boardVo);
		
		return "redirect:/board/list";
	}
	
	
	//게시판 페이징 리스트
	@RequestMapping(value="/list2", method={RequestMethod.GET, RequestMethod.POST})
	public String list2(@RequestParam(value="crtPage", required=false, defaultValue="1") int crtPage,   
			            Model model) {
		System.out.println("boardContoller/list2");
		System.out.println(crtPage);
		
		Map<String, Object> pMap = boardService.list2(crtPage);
		model.addAttribute("pMap", pMap);
		
		System.out.println(pMap.toString());
		return "board/list";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
