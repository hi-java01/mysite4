package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;


@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	//리스트
	public List<BoardVo> list(String keyword){
		System.out.println("boardService/list");
		
		return boardDao.select(keyword);
	}
	
	//글가져오기
	public BoardVo read(int no) {
		System.out.println("boardService/read");
		
		boardDao.updateHit(no);
		BoardVo boardVo = boardDao.selectBoard(no);
		
		return boardVo;
	}
	
	//글쓰기
	public int write(BoardVo boardVo) {
		System.out.println("boardService/write");
		
		return boardDao.insert(boardVo);
	}
	
	//글삭제
	public int remove(BoardVo boardVo) {
		System.out.println("boardService/remove");
		
		return boardDao.delete(boardVo);
	}

	//글수정
	public int modify(BoardVo boardVo) {
		System.out.println("boardService/modify");
		
		return boardDao.update(boardVo);
	}
	
	
	//리스트 페이징
	public Map<String, Object> list2(int crtPage){
		System.out.println("boardService/list2");
		///////////////////////////////////////////////
		// 리스트 가져오기
		//////////////////////////////////////////////
		
		//페이지당 글갯수
		int listCnt = 5;
		
		//현재페이지 계산
		crtPage = (crtPage>0) ? crtPage : (crtPage=1) ; //crtPage 0 보다 작으면 1page처리
		
		//시작글 번호 rownum
		int startRnum = (crtPage-1) * listCnt; //1page --> 0    +1 db에서 계산
				
		//끝글번호 rownum
		int endRnum = startRnum + listCnt; //1page --> 10    db에서 그대로 사용
		
		System.out.println("crtpPage:" + crtPage);
		System.out.println("startRnum:" + startRnum);
		System.out.println("endRnum:" + endRnum);
		
		List<BoardVo> list = boardDao.select2(startRnum, endRnum);
		///////////////////////////////////////////////
		// 페이지 버튼 영역
		//////////////////////////////////////////////
		
		//전체글 갯수
		int totalCount = boardDao.totalCount();
		
		//페이지당 버튼갯수
		int pageBtnCount = 5;
		
		//1 --> 1, 5
		//2 --> 1, 5
		//3 --> 1, 5
		//4 --> 1, 5
		//5 --> 1, 5
		//6 --> 6, 10
		//7 --> 6, 10
		//10 --> 6, 10
		//11 --> 11, 15
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount;

		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount-1);

		//다음 화살표 유무 next
		boolean next = false;
		if(endPageBtnNo*listCnt < totalCount) { 
			next = true;
		}else {
			endPageBtnNo = (int)Math.ceil(totalCount/(double)listCnt);
		}

		//이전 화살표 유무 prev
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		pMap.put("boardList", list);
		
		
		
		return pMap;
	}
	
	
	
	
	
	
	
	
}
